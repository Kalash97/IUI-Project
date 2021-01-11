package pl.kielce.tu.travel_agency.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kielce.tu.travel_agency.model.entities.Ticket;
import pl.kielce.tu.travel_agency.model.entities.Trip;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class TripDto {
    private long id;

    private Date startingDate;

    private int duration;

    private List<TicketDto> tickets;

    private List<HotelDto> hotels;

    public TripDto(Trip trip) {
        this.id = trip.getId();
        this.startingDate = trip.getStartingDate();
        this.duration = trip.getDuration();
        this.tickets = trip
                .getTickets()
                .stream()
                .map(TicketDto::new)
                .peek(ticketDto -> ticketDto.setTrip(null))
                .collect(Collectors.toList());
    }
}
