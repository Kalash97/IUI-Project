package pl.kielce.tu.travel_agency.model.dto;

import lombok.Data;
import pl.kielce.tu.travel_agency.model.entities.Ticket;

import java.util.Date;

@Data
public class TicketDto {
    private long id;

    private double price;

    private String type;

    private TripDto trip;

    private PersonDto person;

    private String tripName;

    public TicketDto(Ticket ticket) {
        this.id = ticket.getId();
        this.price = ticket.getPrice();
        this.type = ticket.getType();
        this.trip = new TripDto(ticket.getTrip());
        this.person = new PersonDto(ticket.getPerson());
    }
}
