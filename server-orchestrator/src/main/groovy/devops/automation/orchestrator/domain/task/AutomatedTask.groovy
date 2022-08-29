package devops.automation.orchestrator.domain.task

import devops.automation.orchestrator.domain.ID
import devops.automation.orchestrator.domain.security.SecurityContext

import java.time.LocalDate

class AutomatedTask {
    ID id
    String name
    String description
    Integer priority
    List<String> developerTeam
    List<Tag> tags
    EntryPoint entryPoint
    LocalDate created
    SecurityContext securityContext
}

