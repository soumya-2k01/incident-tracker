package com.assignment.incidenttracker.repository;

import com.assignment.incidenttracker.entity.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long>,
        JpaSpecificationExecutor<Incident> {
}
