package pl.kielce.tu.travel_agency.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kielce.tu.travel_agency.model.entities.Person;

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

    public PersonDto(Person person) {
        this.id = person.getId();
        this.email = person.getEmail();
        this.firstname = person.getFirstname();
        this.lastname = person.getLastname();
        this.insurance = new InsuranceDto(person.getInsurance());
        this.tickets = person.getTickets()!=null?
                person
                        .getTickets()
                        .stream()
                        .peek(ticket -> ticket.setPerson(null))
                        .map(TicketDto::new)
                        .collect(Collectors.toList()):null;
    }
}
