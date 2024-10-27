package com.apoiacafe.auth.auth.account.type;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum AccountType {
    
    INSTITUTIONAL(List.of(new SimpleGrantedAuthority("INSTITUTIONAL_ROLE"), new SimpleGrantedAuthority("PERSONAL_ROLE"))), 
    PERSONAL(List.of(new SimpleGrantedAuthority("PERSONAL_ROLE"))), 
    ADMIN(List.of(new SimpleGrantedAuthority("ADMIN_ROLE"), 
    new SimpleGrantedAuthority("PERSONAL_ROLE"),
    new SimpleGrantedAuthority("INSTITUTIONAL_ROLE")));

    @Getter
    private final List<SimpleGrantedAuthority> authorities;

}
