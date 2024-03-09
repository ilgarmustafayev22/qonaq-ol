package az.qonaqol.qonaqol.service.impl;

import az.qonaqol.qonaqol.dao.entity.UserEntity;
import az.qonaqol.qonaqol.dao.repository.UserRepository;
import az.qonaqol.qonaqol.exception.UserNotFoundException;
import az.qonaqol.qonaqol.model.dto.AuthenticationDto;
import az.qonaqol.qonaqol.model.enums.TokenType;
import az.qonaqol.qonaqol.security.jwt.TokenPair;
import az.qonaqol.qonaqol.model.enums.UserRole;
import az.qonaqol.qonaqol.model.request.SigninRequest;
import az.qonaqol.qonaqol.model.request.SignupRequest;
import az.qonaqol.qonaqol.service.AuthenticationService;
import az.qonaqol.qonaqol.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtService jwtService;
    private final PasswordEncoder encoder;
    private final UserRepository repository;
    private final AuthenticationManager authManager;

    @Override
    public AuthenticationDto signup(SignupRequest signupRequest) {
        //Register the user to repository and generate a token

        if (signupRequest.getPassword().equals(signupRequest.getConfirmPassword())) {
            var user = UserEntity.builder()
                    .fullName(signupRequest.getFullName())
                    .email(signupRequest.getEmail())
                    .password(encoder.encode(signupRequest.getPassword()))
                    .role(UserRole.USER)
                    .createdDate(LocalDateTime.now())
                    .build();

            repository.save(user);

            return AuthenticationDto.builder()
                    .userId(user.getId())
                    .tokenPair(getTokenPair(user))
                    .build();

        } else {
            throw new IllegalArgumentException("Passwords do not match");
        }

    }

    @Override
    public AuthenticationDto signin(SigninRequest signinRequest) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signinRequest.getEmail(),
                        signinRequest.getPassword()
                )
        );

        var user = repository.findByEmail(signinRequest.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found with email " + signinRequest.getEmail()));

        return AuthenticationDto.builder()
                .userId(user.getId())
                .tokenPair(getTokenPair(user))
                .build();
    }

    @Override
    public TokenPair refreshToken(UserDetails userDetails) {
        var user = repository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User not found with email " + userDetails.getUsername()));

        return getTokenPair(user);
    }

    private TokenPair getTokenPair(UserDetails user) {
        TokenPair tokenResponse = new TokenPair();
        tokenResponse.setAccessToken(jwtService.generateToken(user, TokenType.ACCESS));
        tokenResponse.setRefreshToken(jwtService.generateToken(user, TokenType.REFRESH));
        return tokenResponse;
    }

}
