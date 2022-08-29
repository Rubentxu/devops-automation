package devops.automation.orchestrator.infraestructure.api.rest

import devops.automation.orchestrator.domain.ID
import devops.automation.orchestrator.domain.security.User
import devops.automation.orchestrator.domain.task.AutomatedTask
import devops.automation.orchestrator.domain.task.usecase.TaskUseCases
import groovy.transform.CompileStatic
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.core.publisher.Sinks

import java.util.function.Consumer

//
@CompileStatic
@RestController
@RequestMapping('/api/tasks')
class TaskController {
    private final Sinks.Many<AutomatedTask> sink;
    private final TaskUseCases<Mono> taskUseCases

    TaskController(Sinks.Many<AutomatedTask> sink,TaskUseCases taskUseCases) {
        this.sink = sink
        this.taskUseCases = taskUseCases
    }

    @PostMapping("/{id}/execute")
    void execute(@PathVariable ID id) {
        Mono<AutomatedTask>  automatedTask = taskUseCases.getTask(id)
        automatedTask.subscribe( { AutomatedTask task ->
            sink.emitNext(task, Sinks.EmitFailureHandler.FAIL_FAST)
        } as Consumer )
    }

    @GetMapping("/{user}")
    Mono<List<AutomatedTask>> getAllTasksByUser(@PathVariable String user ) {
        return taskUseCases.getAllByUser(new User(name: user))
    }

    @GetMapping("/")
    Mono<List<AutomatedTask>> getAllTasks() {
        return taskUseCases.getAll()
    }

    @PostMapping("/")
    Mono<AutomatedTask> save(@RequestBody AutomatedTask automatedTask) {
       taskUseCases.save(automatedTask)
    }

}
