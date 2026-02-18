package com.assignment.incidenttracker.specification;

import com.assignment.incidenttracker.entity.Incident;
import com.assignment.incidenttracker.enums.Severity;
import com.assignment.incidenttracker.enums.Status;
import org.springframework.data.jpa.domain.Specification;

public final class IncidentSpecification {

    private IncidentSpecification() {
        // Utility class — prevent instantiation
    }

    public static Specification<Incident> hasStatus(Status status) {
        return (root, query, cb) -> status == null ? null : cb.equal(root.get("status"), status);
    }

    public static Specification<Incident> hasSeverity(Severity severity) {
        return (root, query, cb) -> severity == null ? null : cb.equal(root.get("severity"), severity);
    }

    public static Specification<Incident> hasService(String service) {
        return (root, query, cb) -> (service == null || service.isBlank())
                ? null
                : cb.equal(root.get("service"), service);
    }

    public static Specification<Incident> titleContains(String search) {
        return (root, query, cb) -> (search == null || search.isBlank())
                ? null
                : cb.like(cb.lower(root.get("title")), "%" + search.toLowerCase() + "%");
    }
}
