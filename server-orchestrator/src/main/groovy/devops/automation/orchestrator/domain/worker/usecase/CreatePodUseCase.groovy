package devops.automation.orchestrator.domain.worker.usecase

import io.kubernetes.client.openapi.apis.CoreV1Api
import io.kubernetes.client.openapi.models.*
import org.springframework.stereotype.Service

@Service
class CreatePodUseCase {

    V1Pod execute(String namespace) {
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

        return api.createNamespacedPod(namespace, podBody, null, null, null, null);


    }
}
