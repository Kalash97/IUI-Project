package pl.kielce.tu.travel_agency.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pl.kielce.tu.travel_agency.model.entities.Person;
import pl.kielce.tu.travel_agency.model.repositories.PersonRepo;

@Service
public class PersonService extends AbstractEntityService<Person> {

    @Autowired
    private PersonRepo repo;

    @Override
    public JpaRepository getEntityRepository() {
        return repo;
    }
}
