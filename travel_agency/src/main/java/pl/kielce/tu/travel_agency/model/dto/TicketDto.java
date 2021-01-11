package pl.kielce.tu.travel_agency.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TicketDto {
    private long id;

    private double price;

    private String type;


}
