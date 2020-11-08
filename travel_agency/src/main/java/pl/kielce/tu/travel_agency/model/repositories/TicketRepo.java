package pl.kielce.tu.travel_agency.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kielce.tu.travel_agency.model.entities.Ticket;

public interface TicketRepo extends JpaRepository<Ticket, Long> {
}
