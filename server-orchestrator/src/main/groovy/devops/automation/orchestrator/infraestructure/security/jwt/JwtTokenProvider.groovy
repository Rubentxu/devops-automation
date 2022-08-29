package devops.automation.orchestrator.infraestructure.security.jwt

import devops.automation.orchestrator.infraestructure.po.JwtResponse
import devops.automation.orchestrator.infraestructure.service.UserDetailsImpl
import groovy.transform.CompileStatic
import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct
import javax.crypto.SecretKey
import java.nio.charset.StandardCharsets
import java.util.stream.Collectors

import static java.util.stream.Collectors.joining


@CompileStatic
@Component
public class JwtTokenProvider {

    Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class)

    private static final String AUTHORITIES_KEY = "roles"

    @Autowired
    private JwtProperties jwtProperties

    private SecretKey secretKey

    @PostConstruct
    public void init() {
        var secret = Base64.getEncoder().encodeToString(this.jwtProperties.getSecretKey().getBytes())
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8))
    }

    JwtResponse createToken(Authentication authentication) {

        String username = authentication.getName()
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities()
        Claims claims = Jwts.claims().setSubject(username)
        if (!authorities.isEmpty()) {
            String claimsResult = authorities.collect {it.getAuthority() }.join(',')
            claims.put(AUTHORITIES_KEY, claimsResult)
        }

        Date now = new Date()
        Date validity = new Date(now.getTime() + this.jwtProperties.getValidityInMs())

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(this.secretKey, SignatureAlgorithm.HS256)
                .compact()

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal()
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList())

        return new JwtResponse(token: token,
                id: userDetails.getId(),
                email: userDetails.getEmail(),
                name: userDetails.getName(),
                lastname: userDetails.getLastname(),
                roles: roles)

    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(this.secretKey).build().parseClaimsJws(token).getBody()

        Object authoritiesClaim = claims.get(AUTHORITIES_KEY)

        Collection<? extends GrantedAuthority> authorities = authoritiesClaim == null ? AuthorityUtils.NO_AUTHORITIES
                : AuthorityUtils.commaSeparatedStringToAuthorityList(authoritiesClaim.toString())

        User principal = new User(claims.getSubject(), "", authorities)

        return new UsernamePasswordAuthenticationToken(principal, token, authorities)
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts
                    .parserBuilder().setSigningKey(this.secretKey).build()
                    .parseClaimsJws(token)
            //  parseClaimsJws will check expiration date. No need do here.
            logger.info("expiration date: {}", claims.getBody().getExpiration())
            return true
        } catch (JwtException | IllegalArgumentException e) {
            logger.info("Invalid JWT token: {}", e.getMessage())
            logger.trace("Invalid JWT token trace.", e)
        }
        return false
    }

}