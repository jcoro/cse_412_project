package com.example.cse_412_project.controllers;

import com.example.cse_412_project.DTO.AuthenticationResponse;
import com.example.cse_412_project.DTO.LoginRequest;
import com.example.cse_412_project.DTO.RefreshTokenRequest;
import com.example.cse_412_project.DTO.RegisterRequest;
import com.example.cse_412_project.service.impl.AuthService;
import com.example.cse_412_project.service.impl.RefreshTokenService;
import com.example.cse_412_project.service.impl.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
        if (authService.signup(registerRequest)) {
            return new ResponseEntity<>("User Registration Successful", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("User name already exist!", HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/refresh/token")
    public AuthenticationResponse refreshTokens (@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) throws Exception {
        return authService.refreshToken(refreshTokenRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(HttpStatus.OK).body("Refresh Token Deleted Successfully");
    }
}
