package devops.automation.orchestrator.infraestructure.po

import devops.automation.orchestrator.domain.security.User
import groovy.transform.Immutable

@Immutable
class UserApp extends User {
    String password
}
