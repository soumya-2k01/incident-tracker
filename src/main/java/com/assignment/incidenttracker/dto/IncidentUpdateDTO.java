package com.assignment.incidenttracker.dto;

import com.assignment.incidenttracker.enums.Severity;
import com.assignment.incidenttracker.enums.Status;
import jakarta.validation.constraints.Size;

public record IncidentUpdateDTO(

        @Size(max = 200, message = "Title must not exceed 200 characters")
        String title,

        @Size(max = 100, message = "Service must not exceed 100 characters")
        String service,

        Severity severity,

        Status status,

        @Size(max = 100, message = "Owner must not exceed 100 characters")
        String owner,

        String summary
) {
}
