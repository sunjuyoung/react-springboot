package com.example.airbnbApi.auth.refreshToken;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Integer> {
}
