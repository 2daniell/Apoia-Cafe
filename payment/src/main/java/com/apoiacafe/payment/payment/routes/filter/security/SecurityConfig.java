package com.apoiacafe.payment.payment.routes.filter.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.apoiacafe.payment.payment.core.service.JWTService;
import com.apoiacafe.payment.payment.routes.filter.SecurityRequestFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    
    private final JWTService service;

    public SecurityConfig(JWTService service) {
        this.service = service;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(e -> e.disable()).sessionManagement(e -> e.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(new SecurityRequestFilter(service), UsernamePasswordAuthenticationFilter.class).
        authorizeHttpRequests(e -> e.anyRequest().authenticated()).build();
    }

}
