package devops.automation.orchestrator.domain.task.repositories

import devops.automation.orchestrator.domain.ID
import devops.automation.orchestrator.domain.security.Role
import devops.automation.orchestrator.domain.task.AutomatedTask
import devops.automation.orchestrator.domain.task.Tag

import java.util.concurrent.Flow

interface TaskRepository<T extends Flow.Publisher> {

    Flow.Publisher<AutomatedTask> getByID(ID id)

    Flow.Publisher<AutomatedTask> getAllByRoles(List<Role> roles)

    Flow.Publisher<AutomatedTask> save(AutomatedTask task)

    Flow.Publisher<List<AutomatedTask>> findByTags(List<Tag> tags )

}
