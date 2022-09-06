package devops.automation.orchestrator.infraestructure.shared.jwt

import groovy.transform.Canonical
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
@Canonical
public class JwtProperties {

    String secretKey = "rzxlszyykpbgqcflzxsqcysyhljt";

    // validity in milliseconds
    long validityInMs = 3600000; // 1h

}