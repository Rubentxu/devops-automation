package devops.automation.orchestrator.infraestructure.api.websocket

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import devops.automation.orchestrator.domain.Job
import devops.automation.orchestrator.domain.task.AutomatedTask
import devops.automation.orchestrator.domain.worker.usecase.ExecuteCommandInPodUseCase
import devops.automation.orchestrator.infraestructure.service.JobService
import groovy.transform.CompileStatic
import io.kubernetes.client.openapi.ApiClient
import io.kubernetes.client.openapi.Configuration
import io.kubernetes.client.util.Config
import org.springframework.stereotype.Component
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.WebSocketMessage
import org.springframework.web.reactive.socket.WebSocketSession
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.Sinks

import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.atomic.AtomicBoolean

@CompileStatic
@Component('TaskWebSocketHandler')
public class TaskWebSocketHandler implements WebSocketHandler {

    private final Sinks.Many<AutomatedTask> sink
    private static final ObjectMapper json = new ObjectMapper();
    private final ExecuteCommandInPodUseCase executeCommandInPodUseCase

    TaskWebSocketHandler(Sinks.Many<AutomatedTask> sink, ExecuteCommandInPodUseCase executeCommandInPodUseCase) {
        this.sink = sink
        this.executeCommandInPodUseCase = executeCommandInPodUseCase
        println("Create ReactiveWebSocketHandler ............")
    }


    @Override
    public Mono<Void> handle(WebSocketSession session) {
        ApiClient client = Config.defaultClient()
        Configuration.setDefaultApiClient(client)
        println "Into execute controller ${session.getHandshakeInfo()}"
        println("Server -> received from client id=[${session.getId()}]: isOpen=[${session.isOpen()}]")

        Flux<WebSocketMessage> flux = sink.asFlux()
                 .flatMap(it -> executeCommand(it))
                 .map( it-> toJSON(it))
                 .map( log-> session.textMessage(log))

        return session.send(flux)

    }


    private Flux<SocketResponse> executeCommand(AutomatedTask task) {
        try {
            println("Task $task")
            return executeCommandInPodUseCase.execute('default', task)
            .map(it-> new SocketResponse(errorMessage: "", isError: false, data: it) )
        } catch(Exception ex) {
            println("Log Error in Executed command ${ex.message}")
            return Flux.just(new SocketResponse(errorMessage: "Log Error in Executed command ${ex.message}" as String, isError: false, data: ""))
//            throw new RuntimeException("Error in Executed command ${ex.message}");
        }
    }

    private String toJSON(SocketResponse message) {
        try {
            return json.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}