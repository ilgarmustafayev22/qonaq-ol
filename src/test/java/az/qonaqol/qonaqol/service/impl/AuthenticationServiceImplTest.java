package az.qonaqol.qonaqol.service.impl;

import az.qonaqol.qonaqol.constants.TestConstants;
import az.qonaqol.qonaqol.dao.entity.UserEntity;
import az.qonaqol.qonaqol.dao.repository.UserRepository;
import az.qonaqol.qonaqol.exception.UserNotFoundException;
import az.qonaqol.qonaqol.exception.UsernameAlreadyExistsException;
import az.qonaqol.qonaqol.model.dto.AuthenticationDto;
import az.qonaqol.qonaqol.model.enums.TokenType;
import az.qonaqol.qonaqol.model.enums.UserRole;
import az.qonaqol.qonaqol.model.request.SigninRequest;
import az.qonaqol.qonaqol.model.request.SignupRequest;
import az.qonaqol.qonaqol.security.jwt.TokenPair;
import az.qonaqol.qonaqol.service.JwtService;
import az.qonaqol.qonaqol.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceImplTest {

    @Mock
    private UserRepository repository;
    @Mock
    private AuthenticationManager authManager;
    @Mock
    private UserService userService;
    @Mock
    private JwtService jwtService;
    @Mock
    private PasswordEncoder encoder;

    // We omit @Mock for authManager since you're likely already providing it elsewhere in your config

    @InjectMocks
    private AuthenticationServiceImpl authService;

    @Test
    void signup_success() {
        SignupRequest request = new SignupRequest("Ilgar Mustafayev",
                "ilgarmustafayev22@gmail.com",
                "password",
                "password");
        String encodedPassword = "encodedPassword";
        UserEntity savedUser = new UserEntity(/* ... user data */);
        //savedUser.setId(1L);
        TokenPair tokenPair = new TokenPair("accessToken", "refreshToken");

        // Mocks
        when(userService.existsByEmail(anyString())).thenReturn(false);
        when(encoder.encode(request.getPassword())).thenReturn(encodedPassword);
        when(repository.save(any(UserEntity.class))).thenReturn(savedUser);
        when(jwtService.generateToken(any(UserDetails.class), any(TokenType.class))).thenReturn("accessToken", "refreshToken");

        // Execute
        AuthenticationDto result = authService.signup(request);

        // Assertions
        // assertThat(result.getUserId()).isEqualTo(1L);
        assertThat(result.getTokenPair()).isEqualTo(tokenPair);

        verify(userService).existsByEmail(request.getEmail());
        verify(encoder).encode(request.getPassword());
        verify(repository).save(any(UserEntity.class)); // Check that an entity was saved
    }

    @Test
    void signup_existingEmail() {
        // Test data
        SignupRequest request = new SignupRequest("Test User",
                "existing@email.com",
                "password",
                "password");

        // Mocks
        when(userService.existsByEmail(request.getEmail())).thenReturn(true);

        // Execute and Assert
        assertThrows(UsernameAlreadyExistsException.class, () -> authService.signup(request));

        // Verify that no interaction with the repository occurs
        verify(repository, never()).save(any(UserEntity.class));
    }

    @Test
    void signup_mismatchedPasswords() {
        // Test data
        SignupRequest request = new SignupRequest("Test User",
                "test@email.com",
                "password",
                "differentPass");

        // Mocks (if needed - you likely don't need mocking here)
        when(userService.existsByEmail(anyString())).thenReturn(false);

        // Execute and Assert
        assertThrows(IllegalArgumentException.class, () -> authService.signup(request));

        // Verify that no interaction with the repository occurs
        verify(repository, never()).save(any(UserEntity.class));
    }

    @Test
    void signin_success() {
        String email = "test@email.com";
        String password = "password";
        UserEntity user = new UserEntity();

        TokenPair tokenPair = new TokenPair("accessToken", "refreshToken");

        // Mocks
        when(authManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(new UsernamePasswordAuthenticationToken(email, password));
        when(repository.findByEmail(email)).thenReturn(Optional.of(user));
        when(jwtService.generateToken(any(UserDetails.class), any(TokenType.class))).thenReturn("accessToken", "refreshToken");

        // Execute
        AuthenticationDto result = authService.signin(new SigninRequest(email, password));

        // Assertions
        // assertThat(result.getUserId()).isEqualTo(1L);
        assertThat(result.getTokenPair()).isEqualTo(tokenPair);
    }

    @Test
    void signin_invalidCredentials() {
        // Test data
        String email = "invalid@email.com";
        String password = "wrongpassword";

        // Mocks
        when(authManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(BadCredentialsException.class);

        // Execute and Assert
        assertThrows(BadCredentialsException.class, () -> authService.signin(new SigninRequest(email, password)));

        // Verify no interaction with the repository
        verify(repository, never()).findByEmail(anyString());
    }

    @Test
    void refreshToken_success() {
        // Test data
        String email = "test@email.com";
        UserEntity user = new UserEntity(/* ... user data */);
        user.setId(1L);
        TokenPair newTokenPair = new TokenPair("newAccessToken", "newRefreshToken");
        UserDetails userDetails = User.withUsername(email).password("").roles("USER").build();

        // Mocks
        when(repository.findByEmail(userDetails.getUsername())).thenReturn(Optional.of(user));
        when(jwtService.generateToken(any(UserDetails.class), any(TokenType.class))).thenReturn("newAccessToken", "newRefreshToken");

        // Execute
        TokenPair result = authService.refreshToken(userDetails);

        // Assertions
        assertThat(result).isEqualTo(newTokenPair);
    }

    @Test
    void refreshToken_userNotFound() {
        UserDetails userDetails = User.withUsername("unknown@email.com").password("").roles("USER").build();
        when(repository.findByEmail(userDetails.getUsername())).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> authService.refreshToken(userDetails));
    }

}