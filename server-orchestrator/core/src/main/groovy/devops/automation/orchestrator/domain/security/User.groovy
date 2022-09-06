package devops.automation.orchestrator.domain.security

import groovy.transform.Canonical

@Canonical
class User {
    String id
    String name
    String lastname
    String email
    public Set<Role> roles
    SecurityContext securityContext
    Boolean active = true
}



