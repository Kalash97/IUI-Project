package pl.kielce.tu.travel_agency.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pl.kielce.tu.travel_agency.exception.RegistrationException;
import pl.kielce.tu.travel_agency.model.dto.PersonDto;
import pl.kielce.tu.travel_agency.model.dto.RegistrationDto;
import pl.kielce.tu.travel_agency.model.entities.Person;
import pl.kielce.tu.travel_agency.model.entities.Role;
import pl.kielce.tu.travel_agency.model.repositories.PersonRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService extends AbstractEntityService<Person> {


    private final PersonRepo repo;

    @Autowired
    public PersonService(PersonRepo repo) {
        this.repo = repo;
    }

    public void register(RegistrationDto personDto) throws Exception{
        if(repo.findByEmail(personDto.getEmail()).isPresent()) {
            throw new RegistrationException("Osoba o danym mailu juz istnieje!");
        }


        Person person = new Person();
        person.setFirstname(personDto.getFirstname());
        person.setLastname(personDto.getLastname());
        person.setEmail(personDto.getEmail());
        person.setPassword(personDto.getPassword());
        repo.save(person);
    }

    @Override
    public JpaRepository<Person, Long> getEntityRepository() {
        return repo;
    }

    public List<PersonDto> getAllClients() {
        return getPeopleByRole(Role.CUSTOMER);
    }

    public List<PersonDto> getAllEmployees() {
        return getPeopleByRole(Role.EMPLOYEE);
    }

    private List<PersonDto> getPeopleByRole(Role role) {
        return repo
                .findByRole(role)
                .stream()
                .map(PersonDto::new)
                .collect(Collectors.toList());
    }
}
