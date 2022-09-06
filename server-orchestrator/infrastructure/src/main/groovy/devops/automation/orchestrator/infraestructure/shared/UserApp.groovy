package devops.automation.orchestrator.infraestructure.shared

import devops.automation.orchestrator.domain.security.User
import groovy.transform.Immutable

@Immutable
class UserApp extends User {
    String password
}
