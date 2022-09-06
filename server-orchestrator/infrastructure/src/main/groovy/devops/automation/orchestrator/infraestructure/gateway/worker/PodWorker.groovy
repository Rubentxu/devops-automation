package devops.automation.orchestrator.infraestructure.gateway.worker

import devops.automation.orchestrator.domain.worker.ports.Worker
import groovy.transform.Immutable
import io.kubernetes.client.openapi.models.V1Pod

@Immutable(knownImmutableClasses = [V1Pod])
class PodWorker implements Worker {
    V1Pod pod
}
