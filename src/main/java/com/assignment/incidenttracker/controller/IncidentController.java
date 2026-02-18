package com.assignment.incidenttracker.controller;

import com.assignment.incidenttracker.dto.IncidentRequestDTO;
import com.assignment.incidenttracker.dto.IncidentResponseDTO;
import com.assignment.incidenttracker.dto.IncidentUpdateDTO;
import com.assignment.incidenttracker.enums.Severity;
import com.assignment.incidenttracker.enums.Status;
import com.assignment.incidenttracker.service.IncidentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/incidents")
@RequiredArgsConstructor
public class IncidentController {

    private final IncidentService incidentService;

    /**
     * POST /api/incidents — Create a new incident.
     */
    @PostMapping
    public ResponseEntity<IncidentResponseDTO> createIncident(
            @Valid @RequestBody IncidentRequestDTO request) {
        IncidentResponseDTO created = incidentService.createIncident(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * GET /api/incidents — List incidents with pagination, filtering, search, and sorting.
     */
    @GetMapping
    public ResponseEntity<Page<IncidentResponseDTO>> getAllIncidents(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) Severity severity,
            @RequestParam(required = false) String service,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<IncidentResponseDTO> page = incidentService.getAllIncidents(search, status, severity, service, pageable);
        return ResponseEntity.ok(page);
    }

    /**
     * GET /api/incidents/{id} — Get a specific incident by ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<IncidentResponseDTO> getIncidentById(@PathVariable Long id) {
        IncidentResponseDTO incident = incidentService.getIncidentById(id);
        return ResponseEntity.ok(incident);
    }

    /**
     * PATCH /api/incidents/{id} — Partial update of an incident.
     */
    @PatchMapping("/{id}")
    public ResponseEntity<IncidentResponseDTO> updateIncident(
            @PathVariable Long id,
            @Valid @RequestBody IncidentUpdateDTO request) {
        IncidentResponseDTO updated = incidentService.updateIncident(id, request);
        return ResponseEntity.ok(updated);
    }
}
