package devops.automation.orchestrator.infraestructure.security.jwt

import org.springframework.http.HttpHeaders
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.util.MultiValueMap
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono
import spock.lang.Specification

class JwtAuthenticationFilterSpec extends Specification {


    def "test filter"() {
        given:
        JwtTokenProvider tokenProvider = Mock(JwtTokenProvider.class)
        ServerWebExchange exchange = Mock(ServerWebExchange.class)
        WebFilterChain chain = Mock(WebFilterChain.class)
        ServerHttpRequest request = Mock(ServerHttpRequest.class)

        chain.filter(exchange) >> Mono.just('Void')

        def filter = new JwtTokenAuthenticationFilter(tokenProvider)

        def usernamePasswordToken = new UsernamePasswordAuthenticationToken("test", "password",
                AuthorityUtils.createAuthorityList("ROLE_USER"))
        def multivalue = Mock(MultiValueMap.class)
        multivalue.getFirst(HttpHeaders.AUTHORIZATION) >> "Bearer atesttoken"
        request.getHeaders() >> new HttpHeaders(multivalue)
        exchange.getRequest() >> request
        tokenProvider.validateToken(_) >> true
        tokenProvider.getAuthentication(_) >> usernamePasswordToken

        when:
        filter.filter(exchange, chain)

        then:
        1 * chain.filter(exchange)

    }
}
