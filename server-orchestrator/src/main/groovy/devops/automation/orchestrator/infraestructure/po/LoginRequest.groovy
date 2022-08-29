package devops.automation.orchestrator.infraestructure.po

import groovy.transform.Canonical
import groovy.transform.Immutable

import javax.validation.constraints.NotBlank

@Immutable
public class LoginRequest {

    @NotBlank
    String username

    @NotBlank
    String password

    @NotBlank
    Boolean rememberMe

}