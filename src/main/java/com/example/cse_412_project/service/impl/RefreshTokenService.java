package com.example.cse_412_project.service.impl;

import com.example.cse_412_project.entities.RefreshToken;
import com.example.cse_412_project.repositories.RefreshTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class RefreshTokenService {
	
	private final RefreshTokenRepository refreshTokenRepository;
	
	public RefreshToken generateRefreshToken() {
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setToken(UUID.randomUUID().toString());
		refreshToken.setCreatedDate(Instant.now());
		
		return refreshTokenRepository.save(refreshToken);
	}
	
	public void validateRefreshToken(String token) throws Exception {
		refreshTokenRepository.findByToken(token)
			.orElseThrow(() -> new Exception("Invalid refresh token"));
	}
	public void deleteRefreshToken(String token) {
		refreshTokenRepository.deleteByToken(token);
	}
}
