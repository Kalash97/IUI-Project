package pl.kielce.tu.travel_agency.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.kielce.tu.travel_agency.model.entities.Insurance;
import pl.kielce.tu.travel_agency.model.repositories.InsuranceRepo;

public class InsuranceService extends AbstractEntityService<Insurance> {

    @Autowired
    private InsuranceRepo repo;

    @Override
    public JpaRepository getEntityRepository() {
        return repo;
    }
}
