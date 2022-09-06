package devops.automation.orchestrator.infraestructure.persistence.repositories


import devops.automation.orchestrator.domain.security.ports.ISecurityContextRepository
import devops.automation.orchestrator.infraestructure.shared.UserApp
import groovy.transform.CompileStatic
import org.springframework.stereotype.Repository

import java.util.concurrent.ConcurrentHashMap

@CompileStatic
@Repository
class SecurityContextRepository  implements ISecurityContextRepository<UserApp> {
    private final Map<String, UserApp> store

    SecurityContextRepository() {
        this.store = new ConcurrentHashMap<>()
    }

    @Override
    Optional<UserApp> findByUsername(String username) {
        return Optional.ofNullable(store.find { key, user -> user.name == username || user.email == username }.value)
    }

    @Override
    Boolean existsByEmail(String email) {
        return  Optional.ofNullable(store.find { key, user -> user.email == email }.value).isPresent()
    }

    @Override
    void save(UserApp user) {
        store.put(user.getId(), user)
    }
}
