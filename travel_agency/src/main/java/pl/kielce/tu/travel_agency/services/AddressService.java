package pl.kielce.tu.travel_agency.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pl.kielce.tu.travel_agency.model.entities.Address;
import pl.kielce.tu.travel_agency.model.repositories.AddressRepo;

@Service
public class AddressService extends AbstractEntityService<Address> {

    @Autowired
    private AddressRepo repo;

    @Override
    public JpaRepository getEntityRepository() {
        return repo;
    }
}
