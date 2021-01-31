package pl.kielce.tu.travel_agency.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.kielce.tu.travel_agency.model.dto.TicketDto;
import pl.kielce.tu.travel_agency.security.SecurityUtils;
import pl.kielce.tu.travel_agency.services.TicketService;

import java.util.List;

@RestController
@RequestMapping("/mvc/ticket")
public class TicketController {

    private final TicketService ticketService;

    private final SecurityUtils utils;

    @Autowired
    public TicketController(TicketService ticketService, SecurityUtils utils) {
        this.ticketService = ticketService;
        this.utils = utils;
    }

    @GetMapping("/all-tickets")
    public ResponseEntity<List<TicketDto>> getAllTickets() throws Exception{
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @GetMapping("/cancel-ticket")
    public ResponseEntity<?> cancelTicket(@RequestParam Long ticketId) throws Exception{
        ticketService.cancelTicket(ticketId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/reserve-ticket")
    public ResponseEntity<?> reserveTicket(@RequestParam Long tripId) throws Exception{
        ticketService.reserveTicket(tripId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/of-user/{id}")
    public ResponseEntity<List<TicketDto>> getTicketsOfUser(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.getTicketsOfUserId(id));
    }

    @GetMapping("/of-user")
    public ResponseEntity<List<TicketDto>> findTicketByUserPersonalData(@RequestParam String firstname, @RequestParam String lastname) {
        return ResponseEntity.ok(ticketService.getTicketsByFirstnameAndLastname(firstname, lastname));
    }

    @GetMapping("/my-tickets")
    public ResponseEntity<List<TicketDto>> getMyTickets() throws Exception {
        return ResponseEntity.ok(ticketService.getTicketsOfUserId(utils.getCurrentPerson().getId()));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<TicketDto> getTicketById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(ticketService.getTicketById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<TicketDto> addTicket(@RequestBody TicketDto ticket) throws Exception {
        return ResponseEntity.ok(ticketService.addTicket(ticket));
    }

    @PutMapping("/edit")
    public ResponseEntity<TicketDto> editTicket(@RequestBody TicketDto ticket) throws Exception {
        return ResponseEntity.ok(ticketService.editTicket(ticket));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTicket(@PathVariable Long id) throws Exception {
        ticketService.deleteTicket(id);
        return ResponseEntity.ok().build();
    }
}
