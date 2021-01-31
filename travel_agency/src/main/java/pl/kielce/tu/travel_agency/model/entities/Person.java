package pl.kielce.tu.travel_agency.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name="CUSTOMER")
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    @Email
    @NotNull
    private String email;

    @Size(min=5, max = 64)
    private String password;

    @Size(min=3, max=50)
    private String firstname;

    private String lastname;

    @OneToOne
    private Insurance insurance;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Role role;

    @JsonBackReference
    @OneToMany(mappedBy = "person")
    private List<Ticket> tickets;
}

