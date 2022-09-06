package devops.automation.orchestrator.domain.security

import groovy.transform.Canonical

@Canonical
record SecurityContext (
    Set<Role> roles,
    List<User> reviewers
) {}
