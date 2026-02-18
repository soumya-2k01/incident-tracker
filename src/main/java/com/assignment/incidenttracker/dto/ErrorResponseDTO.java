package com.assignment.incidenttracker.dto;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponseDTO(
        int status,
        String error,
        String message,
        LocalDateTime timestamp,
        Map<String, String> fieldErrors
) {
    public ErrorResponseDTO(int status, String error, String message) {
        this(status, error, message, LocalDateTime.now(), null);
    }

    public ErrorResponseDTO(int status, String error, String message, Map<String, String> fieldErrors) {
        this(status, error, message, LocalDateTime.now(), fieldErrors);
    }
}
