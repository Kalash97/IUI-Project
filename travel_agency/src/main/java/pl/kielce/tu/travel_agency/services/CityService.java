package pl.kielce.tu.travel_agency.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.kielce.tu.travel_agency.model.entities.City;
import pl.kielce.tu.travel_agency.model.repositories.CityRepo;

public class CityService extends AbstractEntityService<City> {

    @Autowired
    private CityRepo repo;

    @Override
    public JpaRepository getEntityRepository() {
        return repo;
    }
}
