package pl.kielce.tu.travel_agency.security;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kielce.tu.travel_agency.config.ServerConfig;
import pl.kielce.tu.travel_agency.model.entities.Person;
import pl.kielce.tu.travel_agency.model.entities.Role;
import pl.kielce.tu.travel_agency.model.repositories.PersonRepo;

import javax.annotation.PostConstruct;
import java.security.Timestamp;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static Logger logger = Logger.getLogger(CustomUserDetailsService.class.getName());

    private final PersonRepo personRepository;

    private final ServerConfig serverConfig;

    @Autowired
    public CustomUserDetailsService(PersonRepo personRepository,
                                    ServerConfig serverConfig) {
        this.personRepository = personRepository;
        this.serverConfig = serverConfig;

    }

    @PostConstruct
    public void firstLaunchInitRoot() {
        if (personRepository.count() == 0) {
            if(serverConfig.getDefault_password() == null || serverConfig.getDefault_password().equals("")) {
                serverConfig.setDefault_password(RandomStringUtils.random(8, true, true));
            }

            logger.info("First launch detected\n" +
                    " - creating new user with credentials: \n" +
                    "Email: "+serverConfig.getDefault_login()+"\n" +
                    "Password: "+serverConfig.getDefault_password()+"\n" +
                    "Highly recommended to log in and change those!");
            Person person = new Person();
            person.setPassword(
                    new BCryptPasswordEncoder(11)
                            .encode(serverConfig.getDefault_password())
            );
            person.setEmail(serverConfig.getDefault_login());

            person.setRole(Role.EMPLOYEE);
            person.setFirstname("Firstname");
            person.setLastname("Lastname");
            personRepository.save(person);

        }
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Person> person = personRepository.findByEmail(s);
        if (!person.isPresent()) {
            throw new UsernameNotFoundException(s);
        }
        return new PersonWrapper(person.get());
    }
}