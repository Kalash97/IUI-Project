package pl.kielce.tu.travel_agency.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name="TRIP")
public class Trip implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Temporal(TemporalType.DATE)
    private Date startingDate;

    private int duration;

    @OneToMany(mappedBy = "trip")
    private List<Ticket> tickets;

    @ManyToMany
    private List<Hotel> hotels;
}
