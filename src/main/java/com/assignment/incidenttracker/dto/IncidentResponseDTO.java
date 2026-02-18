package com.assignment.incidenttracker.dto;

import com.assignment.incidenttracker.enums.Severity;
import com.assignment.incidenttracker.enums.Status;

import java.time.LocalDateTime;

public record IncidentResponseDTO(
        Long id,
        String title,
        String service,
        Severity severity,
        Status status,
        String owner,
        String summary,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
