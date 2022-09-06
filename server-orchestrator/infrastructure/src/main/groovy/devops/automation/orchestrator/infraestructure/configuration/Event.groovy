package devops.automation.orchestrator.infraestructure.configuration

import groovy.transform.Canonical

@Canonical
record Event (
    String eventId,
    String eventDt
) {}
