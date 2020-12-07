package pl.kielce.tu.travel_agency.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kielce.tu.travel_agency.dto.TicketDto;

import java.util.List;

@RestController
public class TicketController {
    @GetMapping("mvc/ticket/all-tickets")
    public ResponseEntity<List<TicketDto>> getAllTickets() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("mvc/ticket/id/{id}")
    public ResponseEntity<TicketDto> getTicketById(@PathVariable String id) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("mvc/ticket/add")
    public ResponseEntity<TicketDto> addTicket(@RequestBody TicketDto ticket) {
        return ResponseEntity.ok().build();
    }

    @PutMapping("mvc/ticket/edit")
    public ResponseEntity<TicketDto> editTicket(@RequestBody TicketDto ticket) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("mvc/ticket/delete/{id}")
    public ResponseEntity<?> deleteTicket(@PathVariable String id) {
        return ResponseEntity.ok().build();
    }
}
