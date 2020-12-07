package pl.kielce.tu.travel_agency.model.dto;

import lombok.Data;
import pl.kielce.tu.travel_agency.model.entities.Address;

@Data
public class AddressDto {
    private long id;

    private String street;

    private int number;

    private CityDto city;

    public AddressDto(Address address) {
        this.id = address.getId();
        this.street = address.getStreet();
        this.number = address.getNumber();
        this.city = new CityDto(address.getCity());
    }
}
