package pl.kielce.tu.travel_agency.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pl.kielce.tu.travel_agency.model.entities.Trip;
import pl.kielce.tu.travel_agency.model.repositories.TripRepo;

@Service
public class TripService extends AbstractEntityService<Trip> {

    private final TripRepo repo;

    @Autowired
    public TripService(TripRepo repo) {
        this.repo = repo;
    }


    @Override
    public JpaRepository getEntityRepository() {
        return repo;
    }
}
