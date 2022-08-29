package devops.automation.orchestrator.domain.task.usecase

import devops.automation.orchestrator.domain.ID
import devops.automation.orchestrator.domain.security.SecurityContext
import devops.automation.orchestrator.domain.security.User
import devops.automation.orchestrator.domain.task.AutomatedTask
import devops.automation.orchestrator.domain.task.repositories.TaskRepository
import groovy.transform.CompileStatic
import org.reactivestreams.FlowAdapters
import reactor.adapter.JdkFlowAdapter
import reactor.core.publisher.Flux

import java.time.Duration
import java.util.concurrent.Flow

@CompileStatic
class TaskUseCases<T extends Flow.Publisher> {
    TaskRepository<T> repository

    TaskUseCases(TaskRepository repository) {
        this.repository = repository
    }

//    T<AutomatedTask> getTask(SecurityContext context, ID id) {
//        // TODO filtrar resultados teniendo en cuenta el contexto de seguridad
//        return repository.getByID(id).
//    }
//
//    T<List<AutomatedTask>> getAllByUser(User user) {
//        // TODO filtrar resultados teniendo en cuenta el contexto de seguridad
//        return repository.get(user)
//    }
//
//    T<List<AutomatedTask>> getAll() {
//        // TODO filtrar resultados teniendo en cuenta el contexto de seguridad
//        return repository.getAll()
//    }
//
//    T<AutomatedTask> save(AutomatedTask task) {
//        return repository.save(task)
//    }
}

//@CompileStatic
//public class IntegrationApp {
//
//    public static void main(String[] args) {
//        var reactorPublisher = reactorPublisher();
//        var akkaStreamsProcessor = akkaStreamsProcessor();
//
//        reactorPublisher.subscribe(akkaStreamsProcessor);
//
//        Flowable
//                .fromPublisher(FlowAdapters.toProcessor(akkaStreamsProcessor))
//                .subscribe(System.out::println);
//    }
//
//    private static Flow.Publisher<Long> reactorPublisher() {
//        var numberFlux = Flux.interval(Duration.ofSeconds(1));
//        return JdkFlowAdapter.publisherToFlowPublisher(numberFlux);
//        return JdkFlowAdapter.flowPublisherToFlux(numberFlux);
//        FlowAdapters.toPublisher(Flow)
//    }
//
//    private static Flow.Processor<Long, Long> akkaStreamsProcessor() {
//        var negatingFlow = Flow.of(Long.class).map(i -> -i);
//        return FlowAdapters.Flow.toProcessor(negatingFlow).run(materializer);
//
//    }
//
//    private static ActorSystem actorSystem = ActorSystem.create();
//    private static ActorMaterializer materializer = ActorMaterializer.create(actorSystem);
//}