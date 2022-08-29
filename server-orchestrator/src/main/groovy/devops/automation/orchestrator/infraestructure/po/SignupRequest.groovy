package devops.automation.orchestrator.infraestructure.po

import groovy.transform.Canonical
import groovy.transform.Immutable

import javax.validation.constraints.NotBlank

@Immutable
public class SignupRequest {
    @NotBlank
    String name

    @NotBlank
    String lastname

    @NotBlank
    String email

    @NotBlank
    String password

    Set<String> role

}