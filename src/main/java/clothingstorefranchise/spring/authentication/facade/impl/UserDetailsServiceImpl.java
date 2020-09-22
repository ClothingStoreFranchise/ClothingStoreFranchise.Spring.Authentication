package clothingstorefranchise.spring.authentication.facade.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import clothingstorefranchise.spring.authentication.model.IdentifiedUser;
import clothingstorefranchise.spring.authentication.repositories.IIdentifiedUserRepository;

import javax.validation.constraints.NotNull;
import java.util.Collection;

import static org.springframework.security.core.authority.AuthorityUtils.commaSeparatedStringToAuthorityList;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailsServiceImpl implements UserDetailsService {
    private final IIdentifiedUserRepository applicationUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        log.info("Searching in the DB the user by username '{}'", username);

        IdentifiedUser user = applicationUserRepository.findByUsername(username);

        log.info("IdentifiedUser found '{}'", user);

        if (user == null)
            throw new UsernameNotFoundException(String.format("Application user '%s' not found", username));

        return new CustomUserDetails(user);
    }

    private static final class CustomUserDetails extends IdentifiedUser implements UserDetails {
		private static final long serialVersionUID = 1L;

		CustomUserDetails(@NotNull IdentifiedUser user) {
            super(user);
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return commaSeparatedStringToAuthorityList("ROLE_" + this.getRole());
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}
