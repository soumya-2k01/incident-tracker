package com.assignment.incidenttracker.dto;

import com.assignment.incidenttracker.enums.Severity;
import com.assignment.incidenttracker.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record IncidentRequestDTO(

        @NotBlank(message = "Title is required")
        @Size(max = 200, message = "Title must not exceed 200 characters")
        String title,

        @NotBlank(message = "Service is required")
        @Size(max = 100, message = "Service must not exceed 100 characters")
        String service,

        @NotNull(message = "Severity is required")
        Severity severity,

        Status status,

        @Size(max = 100, message = "Owner must not exceed 100 characters")
        String owner,

        String summary
) {
}
