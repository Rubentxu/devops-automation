package devops.automation.orchestrator.infraestructure.delivery.websocket

import groovy.transform.Canonical

@Canonical
class SocketResponse {
    String errorMessage
    Boolean isError
    String data
}
