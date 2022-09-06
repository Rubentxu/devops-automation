package devops.automation.orchestrator.infraestructure.shared

public record JwtResponse (
    String token,
    String type = "Bearer",
    Long id,
    String email,
    String name,
    String lastname,
    List<String> roles

) {}