package com.assignment.incidenttracker.service;

import com.assignment.incidenttracker.dto.IncidentRequestDTO;
import com.assignment.incidenttracker.dto.IncidentResponseDTO;
import com.assignment.incidenttracker.dto.IncidentUpdateDTO;
import com.assignment.incidenttracker.entity.Incident;
import com.assignment.incidenttracker.enums.Severity;
import com.assignment.incidenttracker.enums.Status;
import com.assignment.incidenttracker.exception.IncidentNotFoundException;
import com.assignment.incidenttracker.repository.IncidentRepository;
import com.assignment.incidenttracker.specification.IncidentSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class IncidentServiceImpl implements IncidentService {

    private final IncidentRepository incidentRepository;

    @Override
    @Transactional
    public IncidentResponseDTO createIncident(IncidentRequestDTO request) {
        Incident incident = Incident.builder()
                .title(request.title())
                .service(request.service())
                .severity(request.severity())
                .status(request.status() != null ? request.status() : Status.OPEN)
                .owner(request.owner())
                .summary(request.summary())
                .build();

        Incident saved = incidentRepository.save(incident);
        return toResponseDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<IncidentResponseDTO> getAllIncidents(String search, Status status, Severity severity,
                                                     String service, Pageable pageable) {
        Specification<Incident> spec = Specification.where(IncidentSpecification.titleContains(search))
                .and(IncidentSpecification.hasStatus(status))
                .and(IncidentSpecification.hasSeverity(severity))
                .and(IncidentSpecification.hasService(service));

        return incidentRepository.findAll(spec, pageable).map(this::toResponseDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public IncidentResponseDTO getIncidentById(Long id) {
        Incident incident = incidentRepository.findById(id)
                .orElseThrow(() -> new IncidentNotFoundException(id));
        return toResponseDTO(incident);
    }

    @Override
    @Transactional
    public IncidentResponseDTO updateIncident(Long id, IncidentUpdateDTO request) {
        Incident incident = incidentRepository.findById(id)
                .orElseThrow(() -> new IncidentNotFoundException(id));

        // Partial update: only set non-null fields
        if (request.title() != null) {
            incident.setTitle(request.title());
        }
        if (request.service() != null) {
            incident.setService(request.service());
        }
        if (request.severity() != null) {
            incident.setSeverity(request.severity());
        }
        if (request.status() != null) {
            incident.setStatus(request.status());
        }
        if (request.owner() != null) {
            incident.setOwner(request.owner());
        }
        if (request.summary() != null) {
            incident.setSummary(request.summary());
        }

        Incident updated = incidentRepository.save(incident);
        return toResponseDTO(updated);
    }

    private IncidentResponseDTO toResponseDTO(Incident incident) {
        return new IncidentResponseDTO(
                incident.getId(),
                incident.getTitle(),
                incident.getService(),
                incident.getSeverity(),
                incident.getStatus(),
                incident.getOwner(),
                incident.getSummary(),
                incident.getCreatedAt(),
                incident.getUpdatedAt()
        );
    }
}
