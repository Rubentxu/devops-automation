package devops.automation.orchestrator.infraestructure.delivery.rest


import devops.automation.orchestrator.domain.security.EnumRole
import devops.automation.orchestrator.domain.security.Role
import devops.automation.orchestrator.domain.security.SecurityContext
import devops.automation.orchestrator.domain.security.ports.ISecurityContextRepository
import devops.automation.orchestrator.infraestructure.shared.*
import devops.automation.orchestrator.infraestructure.shared.jwt.JwtTokenProvider
import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

import javax.validation.Valid

@CompileStatic
@CrossOrigin(origins = "*", maxAge = 3600L)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    ReactiveAuthenticationManager authenticationManager;

    @Autowired
    ISecurityContextRepository userRepository;

    @Autowired
    Map<EnumRole, Role> roleHandlers;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    Mono<ResponseEntity<JwtResponse>> authenticateUser(@Valid @RequestBody Mono<LoginRequest> loginRequest) {
        return loginRequest
                .flatMap(login -> this.authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(login.username(), login.password()))
                        .map(this.tokenProvider::createToken)
                )
                .map((JwtResponse jwt) -> {
                    HttpHeaders httpHeaders = new HttpHeaders()
                    httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + jwt.token())
                    return new ResponseEntity<JwtResponse>(jwt, httpHeaders, HttpStatus.OK)
                })

    }

    @PostMapping("/signup")
    ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.email())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("msg.E1","Error: Email is already in use!"));
        }
        // Create new user's account
        UserApp user = new UserApp(
                email: signUpRequest.email(),
                name: signUpRequest.name(),
                lastname: signUpRequest.lastname(),
                password: encoder.encode(signUpRequest.password()))

        Set<String> strRoles = signUpRequest.role()
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role userRole = roleHandlers.get(EnumRole.USER)
            assert userRole : "Error: Role is not found. ${EnumRole.ADMIN.toString()}"
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleHandlers.get(EnumRole.ADMIN)
                        assert adminRole : "Error: Role is not found. ${EnumRole.ADMIN.toString()}"
                        roles.add(adminRole);
                        break;
                    case "mod":
                        Role modRole = roleHandlers.get(EnumRole.REVIEWER)
                        assert modRole : "Error: Role is not found. ${EnumRole.REVIEWER.toString()}"
                        roles.add(modRole);
                        break;
                    default:
                        Role userRole = roleHandlers.get(EnumRole.USER)
                        assert userRole : "Error: Role is not found. ${EnumRole.USER.toString()}"
                        roles.add(userRole);
                }
            });
        }
        user.securityContext = new SecurityContext(roles: roles, reviewers: [])
        userRepository.save(user)
        return ResponseEntity.ok(new MessageResponse("msg.U1","User registered successfully!"));
    }
}
