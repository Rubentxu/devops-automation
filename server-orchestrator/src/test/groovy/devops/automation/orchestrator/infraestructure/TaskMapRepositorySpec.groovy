package devops.automation.orchestrator.infraestructure

import devops.automation.orchestrator.domain.security.Role
import devops.automation.orchestrator.domain.security.SecurityContext
import devops.automation.orchestrator.domain.security.User
import devops.automation.orchestrator.domain.task.AutomatedTask
import devops.automation.orchestrator.domain.task.repositories.TaskRepository
import devops.automation.orchestrator.infraestructure.task.repository.TaskMapRepository
import spock.lang.Specification

import java.time.LocalDate

//@CompileStatic
class TaskMapRepositorySpec extends  Specification {

    def "Should get all AutomatedTask by user"() {
        given:
        List<Role> roles = [ { name: "ADMIN"} as Role]
        SecurityContext context = new SecurityContext(roles: roles, reviewers: [])
        User user = new User(name: "rubentxu", securityContext: context )
        TaskRepository repository = new TaskMapRepository()
        def task = new AutomatedTask(id:"aaa",
                name: "Listar ficheros",
                description: "Listar ficheros del contenedor",
                securityContext: new SecurityContext(roles: roles, reviewers: []),
                tags: ['Listar'],
                priority: 1,
                entryPoint : ["sh", "-c", 'find', '/root', '-print'],
                created: LocalDate.now()

        )
        repository.save(task)

        when:
        List<AutomatedTask> result = repository.getAllByUser(user)

        then:
        result.size() == 1
        result.get(0).id == 'aaa'
    }
}
