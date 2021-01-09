package pl.kielce.tu.travel_agency.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pl.kielce.tu.travel_agency.model.entities.Customer;
import pl.kielce.tu.travel_agency.model.repositories.CustomerRepo;

@Service
public class CustomerService extends AbstractEntityService<Customer> {

    @Autowired
    private CustomerRepo repo;

    @Override
    public JpaRepository getEntityRepository() {
        return repo;
    }
}