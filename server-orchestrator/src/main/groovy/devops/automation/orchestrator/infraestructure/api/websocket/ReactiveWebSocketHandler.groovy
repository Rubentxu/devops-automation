package devops.automation.orchestrator.infraestructure.api.websocket
//
//import com.fasterxml.jackson.core.JsonProcessingException
//import com.fasterxml.jackson.databind.ObjectMapper
//import devops.automation.orchestrator.domain.Job
//import devops.automation.orchestrator.domain.worker.usecase.ExecuteCommandInPodUseCase
//import devops.automation.orchestrator.infraestructure.service.JobService
//import groovy.transform.CompileStatic
//import io.kubernetes.client.openapi.ApiClient
//import io.kubernetes.client.openapi.Configuration
//import io.kubernetes.client.util.Config
//import org.springframework.stereotype.Component
//import org.springframework.web.reactive.socket.WebSocketHandler
//import org.springframework.web.reactive.socket.WebSocketMessage
//import org.springframework.web.reactive.socket.WebSocketSession
//import reactor.core.publisher.Flux
//import reactor.core.publisher.Mono
//
//import java.util.concurrent.CopyOnWriteArrayList
//import java.util.concurrent.atomic.AtomicBoolean
//
//@CompileStatic
//@Component("ReactiveWebSocketHandler")
//public class ReactiveWebSocketHandler implements WebSocketHandler {
//
//    private static final ObjectMapper json = new ObjectMapper();
//    final JobService service
//    final static List<Job> jobs_history = new CopyOnWriteArrayList<Job>()
//    private final ExecuteCommandInPodUseCase executeCommandInPodUseCase
//    private final AtomicBoolean newClient
//
//    ReactiveWebSocketHandler(JobService service, ExecuteCommandInPodUseCase executeCommandInPodUseCase) {
//        this.service = service
//        this.executeCommandInPodUseCase = executeCommandInPodUseCase
//        this.newClient = new AtomicBoolean()
//        println("Create ReactiveWebSocketHandler ............")
//    }
//
////    private Flux<String> eventFlux = Flux.generate(sink -> {
////        Event event = new Event(randomUUID().toString(), now().toString())
////        try {
////            sink.next(json.writeValueAsString(event));
////        } catch (JsonProcessingException e) {
////            sink.error(e);
////        }
////    });
////    return session.getHandshakeInfo().getPrincipal().flatMap((p) -> {
////        session.getAttributes().put("username", p.getName());
////        return session.send(outputMessages.filter((payload) -> this.filterUser(session, payload))
////                .map((payload) -> this.generateMessage(session, payload)));
////    }).switchIfEmpty(Mono.defer(() -> {
////        return Mono.error(new BadCredentialsException("Bad Credentials."));
////    })).then()
////
////    private Flux<String> intervalFlux = Flux.interval(Duration.ofMillis(1000L))
////            .zipWith(eventFlux, (time, event) -> event);
//
//    @Override
//    public Mono<Void> handle(WebSocketSession session) {
//
//        ApiClient client = Config.defaultClient()
//        Configuration.setDefaultApiClient(client)
//        println "Into execute controller ${session.getHandshakeInfo()}"
//
//        println(jobs_history)
//        Flux<WebSocketMessage> flux = session.receive()
//                .map(it -> it.getPayloadAsText())
//                .doOnNext(message -> println("Server -> received from client id=[${session.getId()}]: [${message}]"))
//                .flatMap(it -> executeCommand(it))
//                .map( it-> toJSON(it))
//                .map( log-> session.textMessage(log))
//
//        return session.send(flux)
//
//    }
//
//
//    private Flux<SocketResponse> executeCommand(String message) {
//        try {
//            println("Job $message")
//            Job job = json.readValue(message, Job.class)
//            this.jobs_history.add(job)
//            println("Job $job")
//            return executeCommandInPodUseCase.execute('default', null)
//            .map(it-> new SocketResponse(errorMessage: "", isError: false, data: it) )
//        } catch(Exception ex) {
//            println("Log Error in Executed command ${ex.message}")
//            return Flux.just(new SocketResponse(errorMessage: "Log Error in Executed command ${ex.message}" as String, isError: false, data: ""))
////            throw new RuntimeException("Error in Executed command ${ex.message}");
//        }
//    }
//
//    private String toJSON(SocketResponse message) {
//        try {
//            return json.writeValueAsString(message);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}