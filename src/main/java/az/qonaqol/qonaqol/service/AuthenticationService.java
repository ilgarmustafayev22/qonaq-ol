package az.qonaqol.qonaqol.service;

import az.qonaqol.qonaqol.jwt.JwtAuthenticationResponse;
import az.qonaqol.qonaqol.model.request.SigninRequest;
import az.qonaqol.qonaqol.model.request.SignupRequest;

public interface AuthenticationService {

    JwtAuthenticationResponse signup(SignupRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);

}
