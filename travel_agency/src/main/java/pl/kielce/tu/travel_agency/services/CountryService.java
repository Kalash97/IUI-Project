package pl.kielce.tu.travel_agency.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pl.kielce.tu.travel_agency.model.entities.Country;
import pl.kielce.tu.travel_agency.model.repositories.CountryRepo;

@Service
public class CountryService extends AbstractEntityService<Country> {

    @Autowired
    private CountryRepo repo;

    @Override
    public JpaRepository getEntityRepository() {
        return repo;
    }
}
