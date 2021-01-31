package pl.kielce.tu.travel_agency.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import pl.kielce.tu.travel_agency.exception.ForbiddenException;
import pl.kielce.tu.travel_agency.model.dto.TicketDto;
import pl.kielce.tu.travel_agency.model.entities.Person;
import pl.kielce.tu.travel_agency.model.entities.Ticket;
import pl.kielce.tu.travel_agency.model.repositories.PersonRepo;
import pl.kielce.tu.travel_agency.model.repositories.TicketRepo;
import pl.kielce.tu.travel_agency.model.repositories.TripRepo;
import pl.kielce.tu.travel_agency.security.SecurityUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService extends AbstractEntityService<Ticket> {


    private final TicketRepo ticketRepo;
    private final PersonRepo personRepo;
    private final TripRepo tripRepo;
    private final SecurityUtils utils;

    @Autowired
    public TicketService(TicketRepo repo,
                         PersonRepo personRepo,
                         TripRepo tripRepo,
                         SecurityUtils utils) {
        this.ticketRepo = repo;
        this.personRepo = personRepo;
        this.tripRepo = tripRepo;
        this.utils = utils;
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

    public List<TicketDto> getTicketsOfUserId(Long id) {
        List<TicketDto> tickets = ticketRepo.findByPersonId(id)
                .stream()
                .map(TicketDto::new)
                .peek(ticketDto -> ticketDto.setTripName(ticketDto.getTrip().getName()))
                .collect(Collectors.toList());
        return tickets;
    }

    public List<TicketDto> getTicketsByFirstnameAndLastname(String firstname, String lastname) {
        return personRepo
                .findByFirstnameContainingAndLastnameContaining(firstname, lastname)
                .stream()
                .flatMap(person -> person.getTickets().stream())
                .map(TicketDto::new)
                .collect(Collectors.toList());

    }

    public void cancelTicket(Long ticketId) throws Exception{
        if(!ticketRepo.existsById(ticketId)) {
            throw new ResourceNotFoundException("Couldn't find ticket with given ID");
        }
        Ticket ticket = ticketRepo.getOne(ticketId);
        if(ticket.getPerson().getId() != utils.getCurrentPerson().getId()) {
            throw new ForbiddenException("Ticket doesn't belong to a given user!");
        }
        deleteTicket(ticketId);
    }

    public void reserveTicket(Long tripId) throws Exception{
        if(!tripRepo.existsById(tripId)) {
            throw new ResourceNotFoundException("Nie znaleziono wycieczki o podanym ID");
        }
        Ticket ticket = new Ticket();
        ticket.setTrip(tripRepo.getOne(tripId));
        ticket.setPerson(utils.getCurrentPerson());
        ticket.setPrice(0);
        ticket.setType("Normal");
        ticketRepo.save(ticket);
    }


}
