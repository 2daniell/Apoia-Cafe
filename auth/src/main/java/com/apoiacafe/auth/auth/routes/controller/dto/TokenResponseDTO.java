package com.apoiacafe.auth.auth.routes.controller.dto;

import java.time.LocalDateTime;

public record TokenResponseDTO(
    String token,
    LocalDateTime expiration
) {
    
}
