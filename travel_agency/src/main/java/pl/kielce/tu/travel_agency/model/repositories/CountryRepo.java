package pl.kielce.tu.travel_agency.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kielce.tu.travel_agency.model.entities.Country;

import java.util.Optional;

@Repository
public interface CountryRepo extends JpaRepository<Country, Long> {
    Optional<Country> findByName(String name);
}
