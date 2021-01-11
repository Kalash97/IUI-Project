package pl.kielce.tu.travel_agency.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kielce.tu.travel_agency.model.entities.Ticket;

@Data
@NoArgsConstructor
public class TicketDto {
    private long id;

    private double price;

    private String type;

    private TripDto trip;

    private PersonDto customer;

    public TicketDto(Ticket ticket) {

    }
}
