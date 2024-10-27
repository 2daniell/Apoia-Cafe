package com.apoiacafe.auth.auth.core.service;

import org.springframework.stereotype.Service;

import com.apoiacafe.auth.auth.core.generic.GenericService;
import com.apoiacafe.auth.auth.core.repository.AccountRepository;
import com.apoiacafe.auth.auth.entities.Account;

@Service
public class AccountService extends GenericService<Account, Long> {

    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Account findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public Account findByEmail(String email) {
        return repository.findByEmail(email);
    }

}
