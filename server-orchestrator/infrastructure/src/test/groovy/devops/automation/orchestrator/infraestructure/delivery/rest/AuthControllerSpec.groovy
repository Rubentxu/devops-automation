package devops.automation.orchestrator.infraestructure.delivery.rest

import devops.automation.orchestrator.infraestructure.shared.LoginRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.ResponseEntity
import org.springframework.test.web.reactive.server.WebTestClient
import spock.lang.Shared
import spock.lang.Specification

@SpringBootTest
//@ExtendWith(SpringExtension.class)
//@WebFluxTest(AuthController.class)
class AuthControllerSpec extends Specification {
    @Autowired
    private WebTestClient webClient;

    void setup() {
    }


    def 'Should call authenticateUser and response is Ok'() {
        when:
        LoginRequest loginRequest = new LoginRequest(username: "Juan", password: "patata", false)

        then:
        webClient
                .post().uri("/authenticateUser")
                .bodyValue(loginRequest)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(ResponseEntity.class)
    }
}
