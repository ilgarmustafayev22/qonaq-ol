package az.qonaqol.qonaqol.service;

import az.qonaqol.qonaqol.model.dto.AuthenticationDto;
import az.qonaqol.qonaqol.security.jwt.TokenPair;
import az.qonaqol.qonaqol.model.request.SigninRequest;
import az.qonaqol.qonaqol.model.request.SignupRequest;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {

    AuthenticationDto signup(SignupRequest request);

    AuthenticationDto signin(SigninRequest request);

    TokenPair refreshToken(UserDetails userDetails);

}
