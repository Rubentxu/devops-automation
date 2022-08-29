package devops.automation.orchestrator.domain.security

import groovy.transform.Canonical

@Canonical
class SecurityContext {
    Set<Role> roles
    List<User> reviewers
}
