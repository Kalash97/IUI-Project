package pl.kielce.tu.travel_agency.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.kielce.tu.travel_agency.model.entities.Trip;
import pl.kielce.tu.travel_agency.model.repositories.TripRepo;

public class TripService extends AbstractEntityService<Trip> {

    @Autowired
    private TripRepo repo;

    @Override
    public JpaRepository getEntityRepository() {
        return repo;
    }
}
