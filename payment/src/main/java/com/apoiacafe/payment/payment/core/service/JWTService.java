package com.apoiacafe.payment.payment.core.service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class JWTService {
    
    @Value("${jwt.private.key}")
    private String privateKey;

    public List<GrantedAuthority> getRoles(String token) {
        try {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(privateKey))
                .build().verify(token);

        List<String> roles = decodedJWT.getClaim("roles").asList(String.class);
        
        return (roles.isEmpty()) ? List.of() : roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        } catch (Exception e) {
            return List.of();
        }
    }

    public boolean isValid(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(privateKey))
                    .build()
                    .verify(token)
                    .getExpiresAt()
                    .toInstant()
                    .isAfter(Instant.now());
        } catch (Exception e) {
            return false;
        }
    }


    public String getSubject(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(privateKey))
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (Exception e) {
            return null;
        }  
    }

}
