package pl.kielce.tu.travel_agency.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kielce.tu.travel_agency.model.entities.City;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class CityDto {

    private long id;

    private String name;

    private long countryId;
    
    private List<AddressDto> addresses;

    public CityDto(City city) {
        this.id = city.getId();
        this.name = city.getName();
        this.countryId = city.getCountry().getId();
        this.addresses = city.getAddresses()!=null?city
                .getAddresses()
                .stream()
                .peek(address -> address.setCity(null))
                .map(AddressDto::new)
                .collect(Collectors.toList()):null;
    }
}
