package pl.kielce.tu.travel_agency.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kielce.tu.travel_agency.model.entities.Ticket;

import java.util.List;

@Repository
public interface TicketRepo extends JpaRepository<Ticket, Long> {
    List<Ticket> findByPersonId(Long id);
}
