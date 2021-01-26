package pl.kielce.tu.travel_agency.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kielce.tu.travel_agency.model.entities.Address;

import java.util.Optional;

@Repository
public interface AddressRepo extends JpaRepository<Address, Long> {
    Optional<Address> findByCityIdAndStreetAndNumber(Long cityId, String street, int streetNumber);
}
