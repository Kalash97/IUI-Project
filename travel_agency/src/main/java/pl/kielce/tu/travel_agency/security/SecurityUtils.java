package pl.kielce.tu.travel_agency.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pl.kielce.tu.travel_agency.model.entities.Person;
import pl.kielce.tu.travel_agency.model.repositories.PersonRepo;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

import java.util.Optional;

import static pl.kielce.tu.travel_agency.security.jwt.JWTTokenFilter.AUTHORIZATION_HEADER;

@Service
public class SecurityUtils {

    private final PersonRepo personRepository;

    @Autowired
    public SecurityUtils(PersonRepo personRepository) {

        this.personRepository = personRepository;
    }

    public String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public Optional<String> getCurrentUserLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication())
                .map(authentication -> {
                    if (authentication.getPrincipal() instanceof UserDetails) {
                        UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
                        return springSecurityUser.getUsername();
                    } else if (authentication.getPrincipal() instanceof String) {
                        return (String) authentication.getPrincipal();
                    }
                    return null;
                });
    }

    public Person getCurrentPerson() throws Exception{
        Optional<String> currentPersonEmail = getCurrentUserLogin();

        if(!currentPersonEmail.isPresent()) {
            throw new AuthenticationException("User is not logged in.");
        }

        Optional<Person> personOptional = personRepository.findByEmail(currentPersonEmail.get());

        return personOptional.orElseThrow( () -> new AuthenticationException("User is not logged in."));
    }

    /**
     * Get the JWT of the current user.
     *
     * @return the JWT of the current user.
     */
    public Optional<String> getCurrentUserJWT() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication())
                .filter(authentication -> authentication.getCredentials() instanceof String)
                .map(authentication -> (String) authentication.getCredentials());
    }
}
