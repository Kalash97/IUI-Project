package pl.kielce.tu.travel_agency.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.kielce.tu.travel_agency.model.entities.Person;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PersonWrapper implements UserDetails {

    private Person person;

    public PersonWrapper(Person person) {
        this.person = person;
    }

    @Override

    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority y = new SimpleGrantedAuthority(person.getRole().toString());
        //logger.info(y.getAuthority()+" <- FROM PERSON WRAPPER");

        return Collections.singletonList(y);
    }

    @Override
    public String getPassword() {
        return person.getPassword();
    }

    @Override
    public String getUsername() {
        return person.getEmail();
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