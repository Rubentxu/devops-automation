package devops.automation.orchestrator.domain.task.ports

import devops.automation.orchestrator.domain.security.Role
import devops.automation.orchestrator.domain.shared.ID
import devops.automation.orchestrator.domain.task.AutomatedTask
import devops.automation.orchestrator.domain.task.Tag
import org.reactivestreams.Publisher

interface TaskRepository {

    Publisher<AutomatedTask> getByID(ID id)

    Publisher<AutomatedTask> getAllByRoles(List<Role> roles)

    Publisher<AutomatedTask> save(AutomatedTask task)

    Publisher<AutomatedTask> findByTags(List<Tag> tags )

}
