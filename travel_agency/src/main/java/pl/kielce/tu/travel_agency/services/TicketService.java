package pl.kielce.tu.travel_agency.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kielce.tu.travel_agency.model.dto.TicketDto;
import pl.kielce.tu.travel_agency.model.entities.Person;
import pl.kielce.tu.travel_agency.model.entities.Ticket;
import pl.kielce.tu.travel_agency.model.repositories.PersonRepo;
import pl.kielce.tu.travel_agency.model.repositories.TicketRepo;
import pl.kielce.tu.travel_agency.model.repositories.TripRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService extends AbstractEntityService<Ticket> {


    private final TicketRepo ticketRepo;
    private final PersonRepo personRepo;
    private final TripRepo tripRepo;

    @Autowired
    public TicketService(TicketRepo repo,
                         PersonRepo personRepo,
                         TripRepo tripRepo) {
        this.ticketRepo = repo;
        this.personRepo = personRepo;
        this.tripRepo = tripRepo;
    }

    @Override
    public JpaRepository<Ticket, Long> getEntityRepository() {
        return ticketRepo;
    }

    @Transactional
    public TicketDto addTicket(TicketDto ticketDto) throws Exception{
        Ticket ticket = new Ticket();
        Person person = ticketDto.getPerson()!=null?personRepo.getOne(ticketDto.getPerson().getId()):null;
        ticket.setPerson(person);
        ticket.setTrip(ticketDto.getTrip()!=null?tripRepo.getOne(ticketDto.getTrip().getId()):null);
        ticket.setPrice(ticketDto.getPrice());
        ticket.setType(ticketDto.getType());
        ticketRepo.save(ticket);

        return new TicketDto(ticket);
    }

    @Transactional
    public TicketDto editTicket(TicketDto ticketDto) throws Exception{
        if(!ticketRepo.existsById(ticketDto.getId())) {
            throw new ResourceNotFoundException("Ticket with given ID does not exists.");
        }

        Ticket ticket = ticketRepo.getOne(ticketDto.getId());
        ticket.setType(ticketDto.getType());
        ticket.setPrice(ticketDto.getPrice());
        ticket.setTrip(ticketDto.getTrip()!=null?tripRepo.getOne(ticketDto.getTrip().getId()):null);
        ticket.setPerson(ticketDto.getPerson()!=null?personRepo.getOne(ticketDto.getPerson().getId()):null);
        ticketRepo.save(ticket);
        return new TicketDto(ticket);
    }

    @Transactional
    public void deleteTicket(Long id) throws Exception{
        if(!ticketRepo.existsById(id)) {
            throw new ResourceNotFoundException("Ticket with given ID does not exists.");
        }
        ticketRepo.deleteById(id);
    }

    public TicketDto getTicketById(Long id) throws Exception{
        if(!ticketRepo.existsById(id)) {
            throw new ResourceNotFoundException("Ticket with given ID does not exists.");
        }
        return new TicketDto(ticketRepo.getOne(id));
    }

    public List<TicketDto> getAllTickets() throws Exception {
        return ticketRepo
                .findAll()
                .stream().map(TicketDto::new)
                .collect(Collectors.toList());
    }
}
