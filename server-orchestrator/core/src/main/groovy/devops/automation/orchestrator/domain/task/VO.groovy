package devops.automation.orchestrator.domain.task


public record Tag(
        String name,
        String value
) {}

record EntryPoint(List<String> command) {}