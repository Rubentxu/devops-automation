package devops.automation.orchestrator.domain.worker.usecase

import devops.automation.orchestrator.domain.Log
import devops.automation.orchestrator.domain.task.AutomatedTask
import groovy.transform.CompileStatic
import io.kubernetes.client.Exec
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink

import java.util.stream.Collectors

//@CompileStatic
@Service
class ExecuteCommandInPodUseCase {

    Flux<String> execute(String namespace, AutomatedTask task) {

        Flux<String> single = Flux.create ({ emitter ->
            try {
                Exec exec = new Exec();
                boolean tty = System.console() != null;
                final Process proc = exec.exec(namespace, "${task.name}", task.entryPoint, true, tty)
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

        return single
    }
}
