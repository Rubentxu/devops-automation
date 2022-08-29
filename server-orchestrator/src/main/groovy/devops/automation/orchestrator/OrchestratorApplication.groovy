package devops.automation.orchestrator

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan

@SpringBootApplication
@ConfigurationPropertiesScan
class OrchestratorApplication {

	static void main(String[] args) {
		SpringApplication.run(OrchestratorApplication, args)
	}

}
