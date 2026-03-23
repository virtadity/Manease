package com.virtadity.manease.application.model.error_response;

import java.time.LocalDateTime;

public record ErrorResponse(
        String error,
        String message,
        String path,
        int status,
        LocalDateTime timestamp
) {
}
