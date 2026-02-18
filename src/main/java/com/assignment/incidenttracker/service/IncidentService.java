package com.assignment.incidenttracker.service;

import com.assignment.incidenttracker.dto.IncidentRequestDTO;
import com.assignment.incidenttracker.dto.IncidentResponseDTO;
import com.assignment.incidenttracker.dto.IncidentUpdateDTO;
import com.assignment.incidenttracker.enums.Severity;
import com.assignment.incidenttracker.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IncidentService {

    IncidentResponseDTO createIncident(IncidentRequestDTO request);

    Page<IncidentResponseDTO> getAllIncidents(String search, Status status, Severity severity,
                                              String service, Pageable pageable);

    IncidentResponseDTO getIncidentById(Long id);

    IncidentResponseDTO updateIncident(Long id, IncidentUpdateDTO request);
}
