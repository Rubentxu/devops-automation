package devops.automation.orchestrator.infraestructure.delivery.rest


import devops.automation.orchestrator.domain.security.User
import devops.automation.orchestrator.domain.shared.ID
import devops.automation.orchestrator.domain.task.AutomatedTask
import devops.automation.orchestrator.domain.task.usecase.TaskUseCases
import groovy.transform.CompileStatic
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.Sinks

import java.util.function.Consumer

//
@CompileStatic
@RestController
@RequestMapping('/api/tasks')
class TaskController {
    private final Sinks.Many<AutomatedTask> sink;
    private final TaskUseCases taskUseCases

    TaskController(Sinks.Many<AutomatedTask> sink, TaskUseCases taskUseCases) {
        this.sink = sink
        this.taskUseCases = taskUseCases
    }

    @PostMapping("/{id}/execute")
    void execute(@PathVariable ID id) {
        Mono<AutomatedTask> automatedTask = taskUseCases.getTask(id) as Mono
        automatedTask.subscribe( (AutomatedTask task) -> {
            sink.emitNext(task, Sinks.EmitFailureHandler.FAIL_FAST)
        }  )
    }

    @GetMapping("/{user}")
    Flux<AutomatedTask> getAllTasksByUser(@PathVariable String user ) {
        return taskUseCases.getAllByUser(new User(name: user)) as Flux
    }

    @GetMapping("/")
    Flux<AutomatedTask> getAllTasks() {
        return taskUseCases.getAll() as Flux
    }

    @PostMapping("/")
    Mono<AutomatedTask> save(@RequestBody AutomatedTask automatedTask) {
       taskUseCases.save(automatedTask) as Mono
    }

}
