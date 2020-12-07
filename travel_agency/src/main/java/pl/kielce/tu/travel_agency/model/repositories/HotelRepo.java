package pl.kielce.tu.travel_agency.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kielce.tu.travel_agency.model.entities.Hotel;

@Repository
public interface HotelRepo extends JpaRepository<Hotel, Long> {
}
