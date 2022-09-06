package devops.automation.orchestrator.domain.task

import devops.automation.orchestrator.domain.security.User

import java.time.LocalDate

class AutomatedTaskRun extends AutomatedTask {
    User user
    LocalDate executed
}