package com.example.cse_412_project.service.impl;

import com.example.cse_412_project.DTO.AuthenticationResponse;
import com.example.cse_412_project.DTO.LoginRequest;
import com.example.cse_412_project.DTO.RefreshTokenRequest;
import com.example.cse_412_project.DTO.RegisterRequest;
import com.example.cse_412_project.entities.Role;
import com.example.cse_412_project.entities.impl.AppUser;
import com.example.cse_412_project.repositories.UserRepository;
import com.example.cse_412_project.security.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.time.Instant;
import java.util.Collections;
import java.util.Optional;

@AllArgsConstructor
@Transactional
@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final RefreshTokenService refreshTokenService;

    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);

        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .expiresAt(Instant.now().plusMillis(System.currentTimeMillis() + 60000).toEpochMilli())
                .username(loginRequest.getUsername())
                .build();
    }
    public boolean signup(RegisterRequest registerRequest) {
        Optional<AppUser> existingUser = userRepository.findById(registerRequest.getUsername());
        if (existingUser.isPresent()) {
            return false;
        }
        AppUser user = new AppUser();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(registerRequest.getPassword()));
        user.setRoles(Collections.singleton(new SimpleGrantedAuthority(Role.USER)));
        // we can set this to false if we use token => after validating token, set it to true
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        userRepository.save(user);
        return true;
    }

    public AuthenticationResponse refreshToken(@Valid RefreshTokenRequest refreshTokenRequest) throws Exception {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        String token = jwtProvider.generateToken(refreshTokenRequest.getUsername());
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .username(refreshTokenRequest.getUsername())
                .expiresAt(Instant.now().plusMillis(System.currentTimeMillis() + 60000).toEpochMilli())
                .build();
    }
}
