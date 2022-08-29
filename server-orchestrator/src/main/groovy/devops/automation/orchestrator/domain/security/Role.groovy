package devops.automation.orchestrator.domain.security

import groovy.transform.Canonical
import groovy.transform.Immutable

@Immutable
class Role {
    private Long id
    private EnumRole name
}
