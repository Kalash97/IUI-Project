package pl.kielce.tu.travel_agency.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kielce.tu.travel_agency.model.dto.TicketDto;
import pl.kielce.tu.travel_agency.services.TicketService;

import java.util.List;

@RestController
@RequestMapping("/mvc/ticket")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/all-tickets")
    public ResponseEntity<List<TicketDto>> getAllTickets() throws Exception{
        return ResponseEntity.ok(ticketService.getAllTickets());
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
