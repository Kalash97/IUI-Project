package pl.kielce.tu.travel_agency.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.kielce.tu.travel_agency.model.entities.Hotel;
import pl.kielce.tu.travel_agency.model.repositories.HotelRepo;

public class HotelService extends AbstractEntityService<Hotel> {

    @Autowired
    private HotelRepo repo;

    @Override
    public JpaRepository getEntityRepository() {
        return repo;
    }
}
