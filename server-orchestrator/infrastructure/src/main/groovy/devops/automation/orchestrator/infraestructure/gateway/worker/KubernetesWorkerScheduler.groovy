package devops.automation.orchestrator.infraestructure.gateway.worker

import devops.automation.orchestrator.domain.task.AutomatedTask
import devops.automation.orchestrator.domain.worker.ports.Worker
import devops.automation.orchestrator.domain.worker.ports.WorkerScheduler
import groovy.transform.CompileStatic
import io.kubernetes.client.Exec
import io.kubernetes.client.openapi.apis.CoreV1Api
import io.kubernetes.client.openapi.models.*
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink
import reactor.core.publisher.Mono

@CompileStatic
@Service
class KubernetesWorkerScheduler implements WorkerScheduler{

    @Override
    Mono<Worker> createWorker(String namespace) {
        CoreV1Api api = new CoreV1Api();
        V1ObjectMeta meta = new V1ObjectMeta();

        meta.name("perl-pod");
        Map<String, String> labels = new HashMap<>();
        labels.put("app", "perl-pod");
        meta.labels(labels);
        V1ContainerPort port = new V1ContainerPort();
        port.containerPort(9090);
        V1Container container = new V1Container();
        container.name("perl-container");
        container.image("perl");
        container.imagePullPolicy("IfNotPresent");
        container.ports(Arrays.asList(port))
        container.command(["sleep", "infinity"])

        V1PodSpec spec = new V1PodSpec();
        spec.containers(Arrays.asList(container));
        V1Pod podBody = new V1Pod();
        podBody.apiVersion("v1");
        podBody.kind("Pod");
        podBody.metadata(meta);
        podBody.spec(spec);

        def pod = api.createNamespacedPod(namespace, podBody, null, null, null, null)
        return Mono.just(new PodWorker(pod) as Worker)

    }

    @Override
    Flux<String> executeWorker(String namespace, AutomatedTask task) {
        Flux<String> single = Flux.create ({ emitter ->
            try {
                Exec exec = new Exec();
                boolean tty = System.console() != null;
                final Process proc = exec.exec(namespace, task.name, task.entryPoint.command().toArray() as String[], true, tty)
//                def sout = new StringBuilder(), serr = new StringBuilder()

//                OutputStream stdin = proc.getOutputStream()
                InputStream stderr = proc.getErrorStream()
                InputStream stdout = proc.getInputStream()

                BufferedReader reader = new BufferedReader (new InputStreamReader(stdout),512)
//                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin))

//                def logs= new Log()
                reader.lines().forEach  {String line->
                    emitter.next(line)
                    println ("Stdout: $line")
//                    logs.addLine(line)
//                    if(logs.isOverflowLines()) {
//                        emitter.next(logs)
//                        logs = new Log()
//                        println ("Stdout: $logs")
//                    }
                }

                proc.waitFor()
                proc.destroy()

//                emitter.next(new Log(){{ addLine("Finalizado Buffer OnComplete")}})
                emitter.next("Finalizado Buffer OnComplete")
                emitter.complete()

            } catch (IOException e) {
                println("Error Execution in Pod $e.message")
                emitter.error(e.getMessage() as Throwable);
            }
        }, FluxSink.OverflowStrategy.BUFFER,)

        Flux<String> fluxCalc = Flux.just(-1, 0, 1)

                .map(i -> "10 / " + i + " = " + (10 / i))

                .onErrorReturn(ArithmeticException.class, "Division by 0 not allowed");


        fluxCalc.subscribe(value -> System.out.println("Next: " + value),

                error -> System.err.println("Error: " + error));
        return single
    }
}
