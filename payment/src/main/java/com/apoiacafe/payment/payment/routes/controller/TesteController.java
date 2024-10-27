package com.apoiacafe.payment.payment.routes.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:3000")
@RequestMapping("/wallet")
@RestController
public class TesteController {
    
    @GetMapping("/test")
    @PreAuthorize("hasAuthority('PERSONAL_ROLE')")
    public String teste() {
        return "Oiii";
    }

}
