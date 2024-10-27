package com.apoiacafe.auth.auth.entities;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.apoiacafe.auth.auth.account.type.AccountType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@NoArgsConstructor
public class Account implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Setter @Column(unique = true)
    private String username;
    @Setter @Column(unique = true)
    private String email;
    @Setter
    private String passwordHash;
    private LocalDateTime createdAt;
    @Setter
    private boolean isNoNLocked;
    @Enumerated(EnumType.STRING)
    public AccountType accountType;
    private boolean isEnabled;

    public Account(String username, String passwordHash, String email) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.createdAt = LocalDateTime.now();
        this.email = email;
        this.isNoNLocked = true;
        this.isEnabled = false;
        this.accountType = AccountType.PERSONAL;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return accountType.getAuthorities();

    }
    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public boolean isAccountNonLocked() {
        return isNoNLocked;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
