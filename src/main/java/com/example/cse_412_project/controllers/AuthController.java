package com.example.cse_412_project.controllers;

import com.example.cse_412_project.DTO.AuthenticationResponse;
import com.example.cse_412_project.DTO.LoginRequest;
import com.example.cse_412_project.DTO.RegisterRequest;
import com.example.cse_412_project.service.impl.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final UserService authService;

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

//    @PostMapping("/refresh/token")
//    public AuthenticationResponse refreshTokens (@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
//        return authService.refreshToken(refreshTokenRequest);
//    }
//
//    @PostMapping("/logout")
//    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
//        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
//        return ResponseEntity.status(HttpStatus.OK).body("Refresh Token Deleted Successfully");
//    }
}
