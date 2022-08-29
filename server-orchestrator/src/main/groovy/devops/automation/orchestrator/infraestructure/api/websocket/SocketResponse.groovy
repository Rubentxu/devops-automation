package devops.automation.orchestrator.infraestructure.api.websocket

import groovy.transform.Canonical

@Canonical
class SocketResponse {
    String errorMessage
    Boolean isError
    String data
}
