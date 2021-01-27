package pl.kielce.tu.travel_agency.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kielce.tu.travel_agency.model.entities.Address;

@Data
@NoArgsConstructor
public class AddressDto {
    private long id;

    private String street;

    private int number;

    private long cityId;

    public AddressDto(Address address) {
        this.id = address.getId();
        this.street = address.getStreet();
        this.number = address.getNumber();
        this.cityId = address.getCity().getId();
    }
}
