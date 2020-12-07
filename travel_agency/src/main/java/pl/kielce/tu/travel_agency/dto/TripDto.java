package pl.kielce.tu.travel_agency.dto;

import lombok.Data;
import pl.kielce.tu.travel_agency.model.entities.Ticket;
import pl.kielce.tu.travel_agency.model.entities.Trip;

import java.util.Date;
import java.util.List;

@Data
public class TripDto {
    private Date startingDate;

    private int duration;

    public TripDto(Trip trip) {

    }
}
