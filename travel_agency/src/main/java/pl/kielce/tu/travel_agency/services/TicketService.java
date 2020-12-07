package pl.kielce.tu.travel_agency.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pl.kielce.tu.travel_agency.model.entities.Ticket;
import pl.kielce.tu.travel_agency.model.repositories.TicketRepo;

@Service
public class TicketService extends AbstractEntityService<Ticket> {

    @Autowired
    private TicketRepo repo;

    @Override
    public JpaRepository getEntityRepository() {
        return repo;
    }
}
