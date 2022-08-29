package devops.automation.orchestrator.infraestructure.po

import groovy.transform.Immutable

@Immutable
public class JwtResponse {
    String token
    String type = "Bearer"
    Long id
    String email
    String name
    String lastname
    List<String> roles

}