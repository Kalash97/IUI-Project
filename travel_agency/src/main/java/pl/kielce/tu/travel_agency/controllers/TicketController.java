package pl.kielce.tu.travel_agency.controllers;

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

import java.util.List;

@RestController
@RequestMapping("/mvc/ticket")
public class TicketController {

    @GetMapping("/all-tickets")
    public ResponseEntity<List<TicketDto>> getAllTickets() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/of-user")
    public ResponseEntity<List<TicketDto>> getTiketsOfUser(@RequestParam String firstname, @RequestParam String lastname) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/my-tickets")
    public ResponseEntity<List<TicketDto>> getMyTickets() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<TicketDto> getTicketById(@PathVariable String id) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add")
    public ResponseEntity<TicketDto> addTicket(@RequestBody TicketDto ticket) {
        return ResponseEntity.ok().build();
    }

    @PutMapping("/edit")
    public ResponseEntity<TicketDto> editTicket(@RequestBody TicketDto ticket) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTicket(@PathVariable String id) {
        return ResponseEntity.ok().build();
    }
}
