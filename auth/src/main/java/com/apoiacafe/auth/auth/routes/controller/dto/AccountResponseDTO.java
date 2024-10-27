package com.apoiacafe.auth.auth.routes.controller.dto;

import java.time.LocalDateTime;

public record AccountResponseDTO(
    String username,
    String email,
    LocalDateTime createdAt
) {
    
}
