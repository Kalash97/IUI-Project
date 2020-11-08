package pl.kielce.tu.travel_agency.model.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@Table(name = "TICKET")
public class Ticket implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double price;

    private String type;

    @ManyToOne
    private Trip trip;

    @ManyToOne
    private Customer customer;
}
