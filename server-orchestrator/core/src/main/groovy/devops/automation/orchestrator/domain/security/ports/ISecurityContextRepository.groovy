package devops.automation.orchestrator.domain.security.ports

import devops.automation.orchestrator.domain.security.User

interface ISecurityContextRepository<T extends User> {

    Optional<T> findByUsername(String username)

    Boolean existsByEmail(String email)

    void save(T user)
}
