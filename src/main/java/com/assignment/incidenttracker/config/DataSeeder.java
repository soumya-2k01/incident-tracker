package com.assignment.incidenttracker.config;

import com.assignment.incidenttracker.entity.Incident;
import com.assignment.incidenttracker.enums.Severity;
import com.assignment.incidenttracker.enums.Status;
import com.assignment.incidenttracker.repository.IncidentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataSeeder implements CommandLineRunner {

    private final IncidentRepository incidentRepository;

    private static final String[] SERVICES = {
            "payment-service", "auth-service", "user-service", "order-service",
            "notification-service", "inventory-service", "search-service",
            "analytics-service", "billing-service", "gateway-service",
            "email-service", "messaging-service", "reporting-service",
            "cache-service", "config-service"
    };

    private static final String[] ISSUE_PREFIXES = {
            "High latency in", "Connection timeout on", "Memory leak detected in",
            "CPU spike on", "Disk space critical for", "Database connection pool exhausted in",
            "SSL certificate expiring for", "Rate limiting triggered on",
            "Health check failing for", "Deployment rollback needed for",
            "Data inconsistency in", "API response errors from",
            "Queue backlog growing in", "Cache invalidation failure in",
            "Service degradation in", "Unhandled exception in",
            "Network partition affecting", "Load balancer misconfiguration on",
            "Log ingestion delay in", "Authentication failures on"
    };

    private static final String[] OWNERS = {
            "alice.johnson", "bob.smith", "charlie.davis", "diana.wilson",
            "ethan.brown", "fiona.garcia", "george.martinez", "hannah.anderson",
            "ivan.thomas", "julia.taylor", null, null
    };

    private static final String[] SUMMARIES = {
            "Investigating root cause. Initial analysis points to a recent deployment.",
            "Monitoring after applying a temporary fix. Will follow up with a permanent solution.",
            "Escalated to the infrastructure team for further diagnosis.",
            "Rollback completed. Service is recovering. Post-mortem scheduled.",
            "Identified misconfigured environment variable as the root cause.",
            "Auto-scaling rules adjusted. Observing stabilization.",
            "Third-party dependency is experiencing issues. Contacted vendor support.",
            "Implemented circuit breaker pattern to prevent cascading failures.",
            "Database query optimization applied. Response times normalizing.",
            "Redundant instance spun up to handle traffic while primary is repaired.",
            null, null
    };

    @Override
    public void run(String... args) {
        if (incidentRepository.count() > 0) {
            log.info("Database already seeded — skipping.");
            return;
        }

        Random random = new Random(42);
        List<Incident> incidents = new ArrayList<>();

        Severity[] severities = Severity.values();
        Status[] statuses = Status.values();

        for (int i = 0; i < 200; i++) {
            String service = SERVICES[random.nextInt(SERVICES.length)];
            String prefix = ISSUE_PREFIXES[random.nextInt(ISSUE_PREFIXES.length)];
            String owner = OWNERS[random.nextInt(OWNERS.length)];
            String summary = SUMMARIES[random.nextInt(SUMMARIES.length)];
            Severity severity = severities[random.nextInt(severities.length)];
            Status status = statuses[random.nextInt(statuses.length)];

            Incident incident = Incident.builder()
                    .title(prefix + " " + service)
                    .service(service)
                    .severity(severity)
                    .status(status)
                    .owner(owner)
                    .summary(summary)
                    .build();

            incidents.add(incident);
        }

        incidentRepository.saveAll(incidents);
        log.info("Seeded database with {} incidents.", incidents.size());
    }
}
