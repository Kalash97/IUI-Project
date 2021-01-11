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

    private CountryDto country;
    
    private List<AddressDto> addresses;

    public CityDto(City city) {
        this.id = city.getId();
        this.name = city.getName();
        this.country = new CountryDto(city.getCountry());
        this.addresses = city
                .getAddresses()
                .stream()
                .map(AddressDto::new)
                .peek(addressDto -> addressDto.setCity(null))
                .collect(Collectors.toList());
    }
}
