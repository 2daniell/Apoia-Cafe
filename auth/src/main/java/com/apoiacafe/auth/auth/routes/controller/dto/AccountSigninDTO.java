package com.apoiacafe.auth.auth.routes.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AccountSigninDTO(

    @Email @NotNull @NotBlank
    String email,
    @NotNull @NotBlank
    String password

) {
    
}
