package com.virtadity.manease.infrastructure.web.dto.error_response;

import java.time.LocalDateTime;

public record ErrorResponseDTO (
        String error,
        String message,
        String path,
        int status,
        LocalDateTime timestamp
) {}