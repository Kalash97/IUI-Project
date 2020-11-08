package pl.kielce.tu.travel_agency.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name="CUSTOMER")
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String password;

    private String name;

    private String lastname;

    @OneToOne
    private Insurance insurance;

    @OneToMany(mappedBy = "customer")
    private List<Ticket> tickets;
}
