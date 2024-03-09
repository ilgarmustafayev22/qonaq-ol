package az.qonaqol.qonaqol.controller;

import az.qonaqol.qonaqol.model.dto.AuthenticationDto;
import az.qonaqol.qonaqol.security.jwt.TokenPair;
import az.qonaqol.qonaqol.model.request.SigninRequest;
import az.qonaqol.qonaqol.model.request.SignupRequest;
import az.qonaqol.qonaqol.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationDto> signup(@Valid @RequestBody SignupRequest signupRequest) {
        return ResponseEntity.ok(authenticationService.signup(signupRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthenticationDto> signin(@Valid @RequestBody SigninRequest signinRequest) {
        return ResponseEntity.ok(authenticationService.signin(signinRequest));
    }

    @Operation(summary = "Use refresh token to get new access token")
    @PostMapping("/refresh")
    public ResponseEntity<TokenPair> refreshToken(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(authenticationService.refreshToken(userDetails));
    }

}
