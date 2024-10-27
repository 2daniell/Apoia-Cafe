package com.apoiacafe.payment.payment.routes.filter;

import java.io.IOException;
import java.security.Security;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.apoiacafe.payment.payment.core.service.JWTService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

@Component
public class SecurityRequestFilter extends OncePerRequestFilter{

    private final JWTService service;

    public SecurityRequestFilter(JWTService service) {
        this.service = service;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("authorization");
        if (header != null) {
            String token = getTokenFromHeader(header); 
            if (token != null) {
                boolean isValid = service.isValid(token);
                String tokenSubject = service.getSubject(token);
                if (tokenSubject != null && isValid) {
                    List<GrantedAuthority> roles = service.getRoles(getTokenFromHeader(header));
                    if (roles != null && !roles.isEmpty()) {
                        var authentication = new UsernamePasswordAuthenticationToken(tokenSubject, null, roles);
                        SecurityContextHolder.getContext().setAuthentication(authentication);   
                    } else {
                        response.setStatus(HttpStatus.UNAUTHORIZED.value());
                        return;
                    }
                } else {
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    return;
                }
            } else {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                return;
            }
        } else {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }
        filterChain.doFilter(request, response);
    }

    public String getTokenFromHeader(String string) {
        return (string.startsWith("Bearer ") ? string.replace("Bearer ", "") : string);
    }
    
}
