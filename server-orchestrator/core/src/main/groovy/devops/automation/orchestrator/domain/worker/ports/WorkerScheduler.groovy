package devops.automation.orchestrator.domain.worker.ports

import devops.automation.orchestrator.domain.task.AutomatedTask
import org.reactivestreams.Publisher

interface WorkerScheduler {

    Publisher<Worker> createWorker(String namespace)

    Publisher<String> executeWorker(String namespace, AutomatedTask task)

}
