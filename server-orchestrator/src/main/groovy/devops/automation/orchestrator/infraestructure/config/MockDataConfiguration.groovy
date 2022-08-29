package devops.automation.orchestrator.infraestructure.config

import devops.automation.orchestrator.domain.ID
import devops.automation.orchestrator.domain.security.EnumRole
import devops.automation.orchestrator.domain.security.Role
import devops.automation.orchestrator.domain.security.SecurityContext
import devops.automation.orchestrator.domain.security.User
import devops.automation.orchestrator.domain.security.repositories.ISecurityContextRepository
import devops.automation.orchestrator.domain.task.AutomatedTask
import devops.automation.orchestrator.domain.task.EntryPoint
import devops.automation.orchestrator.domain.task.Tag
import devops.automation.orchestrator.domain.task.repositories.TaskRepository
import devops.automation.orchestrator.infraestructure.po.UserApp
import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.EventListener

import java.time.LocalDate

@CompileStatic
@Configuration
class MockDataConfiguration {

    @Autowired
    private ISecurityContextRepository<UserApp> repository
    @Autowired
    private TaskRepository<UserApp> taskRepository

    private static final User[] users = new User[]{
            new UserApp(id: '1', name:
                    'rubentxu',
                    email: 'rubentxu@gmail.com',
                    lastname: 'Cabrera',
                    securityContext:  new SecurityContext(roles:  [new Role(name: EnumRole.ADMIN)] as Set, reviewers: []),
                    password: '$2a$12$viL.Cta3mxYaq3WzXretJui6Z2j1VNr.2rjYrKYXgPBLkDG9uVQ/.'
            )
    }

    private static final AutomatedTask[] tasks = new AutomatedTask[]{
            new AutomatedTask(id: new ID(value:'1'), name:
                    'Listar ficheros',
                    description: 'Listar ficheros en directorio',
                    priority: 1,
                    tags: [new Tag(name:  'container'), new Tag(name:'linux')],
                    entryPoint:  new EntryPoint( command: ["sh", "-c", "find /etc -print"]),
                    created: LocalDate.now(),
                    securityContext: new SecurityContext(roles: [new Role(id: EnumRole.ADMIN.ordinal(), name: EnumRole.ADMIN)] as Set)
            ),
            new AutomatedTask(id: new ID(value:'2'), name:
                    'Copiar ficheros',
                    description: 'Copiar ficheros en directorio',
                    priority: 1,
                    tags: [new Tag(name:  'container'), new Tag(name:'linux')],
                    entryPoint:  new EntryPoint( command: ["sh", "-c", "cp /etc /tmp/etc"]),
                    created: LocalDate.now(),
                    securityContext: new SecurityContext(roles: [new Role(id: EnumRole.USER.ordinal(), name: EnumRole.USER)] as Set)
            ),
            new AutomatedTask(id: new ID(value:'3'), name:
                    'Copiar directorio',
                    description: 'Copiar directorio',
                    priority: 1,
                    tags: [new Tag(name:  'container'), new Tag(name:'linux')],
                    entryPoint:  new EntryPoint( command: ["sh", "-c", "cp /etc /tmp/etc"]),
                    created: LocalDate.now(),
                    securityContext: new SecurityContext(roles: [new Role(id: EnumRole.REVIEWER.ordinal(), name: EnumRole.REVIEWER)] as Set)
            ),
            new AutomatedTask(id: new ID(value:'4'), name:
                    'Listar ficheros Bis',
                    description: 'Listar ficheros en directorio',
                    priority: 1,
                    tags: [new Tag(name:  'container'), new Tag(name:'linux')],
                    entryPoint: new EntryPoint( command: ["sh", "-c", "find /etc -print"]),
                    created: LocalDate.now(),
                    securityContext: new SecurityContext(roles: [new Role(id: EnumRole.ADMIN.ordinal(), name: EnumRole.ADMIN)] as Set)
            ),
            new AutomatedTask(id: new ID(value:'5'), name:
                    'Copiar ficheros Bis',
                    description: 'Copiar ficheros en directorio',
                    priority: 1,
                    tags: [new Tag(name:  'container'), new Tag(name:'linux')],
                    entryPoint:  new EntryPoint( command: ["sh", "-c", "cp /etc /tmp/etc"]),
                    created: LocalDate.now(),
                    securityContext: new SecurityContext(roles: [new Role(id: EnumRole.USER.ordinal(), name: EnumRole.USER)] as Set)
            ),
            new AutomatedTask(id: new ID(value: '6'),
                    name: 'Copiar directorio Bis',
                    description: 'Copiar directorio',
                    priority: 1,
                    tags: [new Tag(name:  'container'), new Tag(name:'linux')],
                    entryPoint:  new EntryPoint( command: ["sh", "-c", "cp /etc /tmp/etc"]),
                    created: LocalDate.now(),
                    securityContext: new SecurityContext(roles: [new Role(id: EnumRole.REVIEWER.ordinal(), name: EnumRole.REVIEWER)] as Set)
            ),


    }

    @EventListener(ApplicationReadyEvent.class)
    void loadData() {
        println("Load data JObs")
        users.each { repository.save(it as UserApp) }
    }
}
