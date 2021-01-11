package pl.kielce.tu.travel_agency.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonDto {
    private long id;

    private String email;

    private String name;

    private String lastname;
}
