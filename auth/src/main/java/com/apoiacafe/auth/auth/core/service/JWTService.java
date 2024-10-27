package com.apoiacafe.auth.auth.core.service;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class JWTService {
    
    @Value("${jwt.private.key}")
    private String privateKey;

    public String generateJWT(Authentication authentication) {
        try {
            var algorithm = Algorithm.HMAC256(privateKey);
            List<String> claims = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
            return JWT.create()
            .withIssuer("apoiacafe")
            .withSubject(authentication.getPrincipal().toString())
            .withIssuedAt(Instant.now())
            .withExpiresAt(LocalDateTime.now().plusHours(1L).toInstant(ZoneOffset.of("-03:00")))
            .withClaim("roles", claims)
            .sign(algorithm);
        } catch(Exception e) {
            throw new RuntimeException("Não foi possivel gerar JWT", e);
        }
    }

    public LocalDateTime getExpirationDate(String token) {
        return JWT.require(Algorithm.HMAC256(privateKey))
        .build()
        .verify(token)
        .getExpiresAt()
        .toInstant()
        .atOffset(ZoneOffset.of("-03:00"))
        .toLocalDateTime();
    }

}
