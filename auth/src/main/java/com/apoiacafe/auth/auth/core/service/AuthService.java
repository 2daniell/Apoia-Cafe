package com.apoiacafe.auth.auth.core.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    
    @Autowired
    private JWTService jwtService;

    public String authenticate(Authentication authentication) {
        return jwtService.generateJWT(authentication);
    }

    public boolean isAuthenticated(String token) {
        return jwtService.getExpirationDate(token).isAfter(LocalDateTime.now());
    }

    public LocalDateTime getExpirationDate(String token) {
        return jwtService.getExpirationDate(token);
    }

}
