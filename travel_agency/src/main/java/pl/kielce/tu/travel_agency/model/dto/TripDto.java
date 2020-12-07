package pl.kielce.tu.travel_agency.model.dto;

import lombok.Data;
import pl.kielce.tu.travel_agency.model.entities.Trip;

import java.util.Date;

@Data
public class TripDto {
    private Date startingDate;

    private int duration;

    public TripDto(Trip trip) {

    }
}
