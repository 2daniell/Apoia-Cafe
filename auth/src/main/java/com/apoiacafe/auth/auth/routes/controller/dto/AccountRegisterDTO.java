package com.apoiacafe.auth.auth.routes.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AccountRegisterDTO(

    @NotBlank @NotNull @Size(min = 6, max = 20, message = "Nome de usuario precisa ter entre 6 e 20 caracteres")
    String username,
    @NotBlank @NotNull @Size(min = 6, max = 20, message = "Senha precisa ter entre 6 e 20 caracteres")
    String password,
    @Email(message = "Email Invalido")
    String email
) {
    
}
