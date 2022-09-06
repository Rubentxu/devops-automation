package devops.automation.orchestrator.infraestructure.service

import com.fasterxml.jackson.annotation.JsonIgnore
import devops.automation.orchestrator.infraestructure.shared.UserApp
import groovy.transform.Immutable
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Immutable
class UserDetailsImpl implements UserDetails {

    Long id
    String email
    @JsonIgnore
    String _password
    Collection<? extends GrantedAuthority> _authorities
    String name
    String lastname

    static UserDetailsImpl build(UserApp user) {
        List<GrantedAuthority> authorities = user.securityContext.roles
                .collect { role -> new SimpleGrantedAuthority(role.name.toString()) }

        return new UserDetailsImpl(
                id: user.getId(),
                email: user.getEmail(),
                name: user.getName(),
                lastname: user.getLastname(),
                _password: user.getPassword(),
                _authorities: authorities)
    }

    @Override
    Collection<? extends GrantedAuthority> getAuthorities() {
        return _authorities
    }

    @Override
    public String getUsername() {
        return this.email
    }

    @Override
    public String getPassword() {
        return this._password
    }

    @Override
    public boolean isAccountNonExpired() {
        return true
    }

    @Override
    public boolean isAccountNonLocked() {
        return true
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true
    }

    @Override
    public boolean isEnabled() {
        return true
    }


}