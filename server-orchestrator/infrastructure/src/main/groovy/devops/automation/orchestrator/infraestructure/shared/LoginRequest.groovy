package devops.automation.orchestrator.infraestructure.shared

import javax.validation.constraints.NotBlank

public record LoginRequest (
    @NotBlank
    String username,
    @NotBlank
    String password,
    @NotBlank
    Boolean rememberMe
){}