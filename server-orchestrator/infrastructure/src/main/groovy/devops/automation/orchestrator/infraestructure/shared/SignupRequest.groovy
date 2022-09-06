package devops.automation.orchestrator.infraestructure.shared

import javax.validation.constraints.NotBlank

public record SignupRequest (
    @NotBlank
    String name,
    @NotBlank
    String lastname,
    @NotBlank
    String email,
    @NotBlank
    String password,
    Set<String> role
) {}