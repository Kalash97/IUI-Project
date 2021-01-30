package pl.kielce.tu.travel_agency.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kielce.tu.travel_agency.model.entities.Person;
import pl.kielce.tu.travel_agency.model.entities.Role;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class PersonDto {
    private long id;

    private String email;

    private String firstname;

    private String lastname;

    private InsuranceDto insurance;

    private List<TicketDto> tickets;

    private Role role;

    public PersonDto(Person person) {
        this.id = person.getId();
        this.email = person.getEmail();
        this.firstname = person.getFirstname();
        this.lastname = person.getLastname();
        this.insurance =person.getInsurance()!=null? new InsuranceDto(person.getInsurance()):null;
        this.tickets = person.getTickets()!=null?
                person
                        .getTickets()
                        .stream()
                        .peek(ticket -> ticket.setPerson(null))
                        .map(TicketDto::new)
                        .collect(Collectors.toList()):null;
        this.role = person.getRole();
    }
}
