package devops.automation.orchestrator.infraestructure.config

import devops.automation.orchestrator.domain.security.EnumRole
import devops.automation.orchestrator.domain.security.Role
import devops.automation.orchestrator.infraestructure.security.jwt.JwtTokenAuthenticationFilter
import devops.automation.orchestrator.infraestructure.security.jwt.JwtTokenProvider
import groovy.transform.CompileStatic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager
import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.authorization.AuthorizationContext
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository
import reactor.core.publisher.Mono

@Configuration
class SecurityConfig  {


    @Bean Map<EnumRole, Role> roleHandlers() {
        Map<EnumRole,Role> map = new EnumMap<>(EnumRole.class)
        map.put(EnumRole.USER,new Role(id: 1, name: 'USER'))
        map.put(EnumRole.MODERATOR,new Role(id: 2, name: 'MODERATOR'))
        map.put(EnumRole.ADMIN,new Role(id: 3, name: 'ADMIN'))
        return map
    }

    @Bean
    SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http,
                                                JwtTokenProvider tokenProvider,
                                                ReactiveAuthenticationManager reactiveAuthenticationManager) {
        final String PATH_TASKS = "/posts/**";

        return http.csrf(ServerHttpSecurity.CsrfSpec::disable)
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .authenticationManager(reactiveAuthenticationManager)
                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                .authorizeExchange(it -> it
                        .pathMatchers(HttpMethod.GET, PATH_TASKS).permitAll()
                        .pathMatchers(HttpMethod.DELETE, PATH_TASKS).hasRole("ADMIN")
                        .pathMatchers(PATH_TASKS).authenticated()
                        .pathMatchers("/me").authenticated()
                        .pathMatchers("/users/{user}/**").access(this::currentUserMatchesPath)
                        .anyExchange().permitAll()
                )
                .addFilterAt(new JwtTokenAuthenticationFilter(tokenProvider), SecurityWebFiltersOrder.HTTP_BASIC)
                .build();


    }

    private Mono<AuthorizationDecision> currentUserMatchesPath(Mono<Authentication> authentication, AuthorizationContext context) {
        return authentication
                .map { context.getVariables().get("user").equals(it.getName()) }
                .map {new AuthorizationDecision(it) }
    }


    @Bean
    ReactiveAuthenticationManager reactiveAuthenticationManager(ReactiveUserDetailsService userDetailsService,
                                                                       PasswordEncoder passwordEncoder) {
        var authenticationManager = new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService)
        authenticationManager.setPasswordEncoder(passwordEncoder)
        return authenticationManager
    }
}