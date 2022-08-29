package devops.automation.orchestrator.infraestructure.task.repository

import devops.automation.orchestrator.domain.ID
import devops.automation.orchestrator.domain.security.Role
import devops.automation.orchestrator.domain.security.SecurityContext
import devops.automation.orchestrator.domain.security.User
import devops.automation.orchestrator.domain.task.AutomatedTask
import devops.automation.orchestrator.domain.task.Tag
import devops.automation.orchestrator.domain.task.repositories.TaskRepository
import groovy.transform.CompileStatic
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

import java.time.LocalDate
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.Flow

@Component
@CompileStatic
class TaskMapRepository implements TaskRepository<Mono> {
    private final Map<ID, AutomatedTask> store

    TaskMapRepository() {
        this.store = new ConcurrentHashMap<>()
    }
//
//    @Override
//    Mono<AutomatedTask> getByID(ID id) {
//        return Mono.just(store.get(id))
//    }
//
//    @Override
//    Mono<List<AutomatedTask>> getAllByUser(User user) {
//        List<AutomatedTask> result =  store.findAll { id, task -> task?.securityContext?.roles.containsAll(user.roles) }
//                .collect {id, task-> task }  as List<AutomatedTask>
//        return Mono.just(result)
//    }
//
//    @Override
//    Mono<List<AutomatedTask>> getAll() {
//        List<AutomatedTask> result = store.collect {id, task-> task } as List<AutomatedTask>
//        return Mono.just(result)
//    }
//
//    @Override
//    Mono getByID(SecurityContext securityContext, ID id) {
//        AutomatedTask task = store.get(id)
//        if(task.securityContext.t == group) {
//            return Mono.just(store.get(id))
//        }
//        return Mono.empty()
//    }

//    @Override
//    Mono<AutomatedTask> getByID(ID id) {
////        List<AutomatedTask> result =  store.find { idTask, task -> task.id == idTask  }
////                .collect {idTask, task-> task }  as List<AutomatedTask>
////        return Mono.just(result)
//    }

//    @Override
//    Mono getAllByContext(SecurityContext securityContext) {
//        List<AutomatedTask> result =  store.findAll { id, task -> task?.securityContext?.roles.contains(securityContext.roles) }
//                .collect {id, task-> task }  as List<AutomatedTask>
//        return Mono.just(result)
//    }
//
//    @Override
//    Mono<AutomatedTask> save(AutomatedTask task) {
//        task.created = LocalDate.now()
//        store.put(new ID(value: task.id), task)
//        return Mono.just(task)
//    }
//
//    @Override
//    Mono findByTags(List<Tag> tags) {
//        List<AutomatedTask> result =  store.find { idTask, task ->
//            task.tags.contains(tags) == idTask && task?.securityContext?.roles.contains(securityContext.roles)
//        }.collect {idTask, task-> task }  as List<AutomatedTask>
//        return Mono.just(result)
//    }

    @Override
    Flow.Publisher<AutomatedTask> getByID(ID id) {
        return null
    }

    @Override
    Flow.Publisher<AutomatedTask> getAllByRoles(List<Role> roles) {
        return null
    }

    @Override
    Flow.Publisher<AutomatedTask> save(AutomatedTask task) {
        return null
    }

    @Override
    Flow.Publisher<List<AutomatedTask>> findByTags(List<Tag> tags) {
        return null
    }
}
