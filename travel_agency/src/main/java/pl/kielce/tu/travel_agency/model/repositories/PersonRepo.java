package pl.kielce.tu.travel_agency.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kielce.tu.travel_agency.model.entities.Person;
import pl.kielce.tu.travel_agency.model.entities.Role;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepo extends JpaRepository<Person, Long> {

    Optional<Person> findByEmail(String s);

    List<Person> findByRole(Role role);

    List<Person> findByFirstnameContainingAndLastnameContaining(String firstname, String lastname);
}
