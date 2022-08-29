package devops.automation.orchestrator.infraestructure.service

import devops.automation.orchestrator.domain.security.repositories.ISecurityContextRepository
import devops.automation.orchestrator.infraestructure.po.UserApp
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class UserDetailsServiceImpl implements ReactiveUserDetailsService {

    @Autowired
    ISecurityContextRepository<UserApp> securityRepository

    @Override
    Mono<UserDetails> findByUsername(String username) {
        UserApp user = securityRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Email Not Found"))
        return Mono.just(UserDetailsImpl.build(user))
    }
}