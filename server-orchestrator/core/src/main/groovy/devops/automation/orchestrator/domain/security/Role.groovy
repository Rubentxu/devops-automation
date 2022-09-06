package devops.automation.orchestrator.domain.security

import groovy.transform.Canonical

@Canonical
record Role (
    private Long id,
    private EnumRole name
) {}
