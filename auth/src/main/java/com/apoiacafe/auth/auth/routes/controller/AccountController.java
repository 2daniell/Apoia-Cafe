package com.apoiacafe.auth.auth.routes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apoiacafe.auth.auth.core.service.AccountService;
import com.apoiacafe.auth.auth.core.service.AuthService;
import com.apoiacafe.auth.auth.entities.Account;
import com.apoiacafe.auth.auth.routes.controller.dto.AccountRegisterDTO;
import com.apoiacafe.auth.auth.routes.controller.dto.AccountResponseDTO;
import com.apoiacafe.auth.auth.routes.controller.dto.AccountSigninDTO;
import com.apoiacafe.auth.auth.routes.controller.dto.TokenResponseDTO;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;
    private final AuthService authService;

    public AccountController(AccountService accountService, AuthService authService) {
        this.accountService = accountService;
        this.authService = authService;
    }

    @PostMapping("/signup") @Transactional
    public ResponseEntity<Object> signup(@RequestBody @Valid AccountRegisterDTO accountRegisterDTO) {
        if (accountService.findByUsername(accountRegisterDTO.username()) != null || accountService.findByEmail(accountRegisterDTO.email()) != null) 
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        Account account = new Account(accountRegisterDTO.username(), 
        new BCryptPasswordEncoder().encode(accountRegisterDTO.password()), accountRegisterDTO.email());
        accountService.save(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(new AccountResponseDTO(account.getUsername(), account.getEmail(), account.getCreatedAt()));
    }

    @PostMapping("/signin")
    public ResponseEntity<Object> signin(@RequestBody @Valid AccountSigninDTO accountSigninDTO) {
        if (accountService.findByEmail(accountSigninDTO.email()) == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        var account = accountService.findByEmail(accountSigninDTO.email()); var bcrypt = new BCryptPasswordEncoder();
        if (!bcrypt.matches(accountSigninDTO.password(), account.getPassword()))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        var authenticationToken = new UsernamePasswordAuthenticationToken(account.getUserId(), null, account.getAuthorities());
        var token = authService.authenticate(authenticationToken);
        return ResponseEntity.status(HttpStatus.OK).body(new TokenResponseDTO(token, authService.getExpirationDate(token)));
    }
}
