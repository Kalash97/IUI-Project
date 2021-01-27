package pl.kielce.tu.travel_agency.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pl.kielce.tu.travel_agency.model.entities.Insurance;
import pl.kielce.tu.travel_agency.model.repositories.InsuranceRepo;

@Service
public class InsuranceService extends AbstractEntityService<Insurance> {


    private final InsuranceRepo repo;

    public InsuranceService(InsuranceRepo repo) {
        this.repo = repo;
    }

    @Override
    public JpaRepository<Insurance, Long> getEntityRepository() {
        return repo;
    }
}
