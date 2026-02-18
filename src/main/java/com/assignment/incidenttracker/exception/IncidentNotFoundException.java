package com.assignment.incidenttracker.exception;

public class IncidentNotFoundException extends RuntimeException {

    public IncidentNotFoundException(Long id) {
        super("Incident not found with id: " + id);
    }
}
