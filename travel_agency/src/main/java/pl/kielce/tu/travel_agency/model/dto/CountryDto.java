package pl.kielce.tu.travel_agency.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kielce.tu.travel_agency.model.entities.City;
import pl.kielce.tu.travel_agency.model.entities.Country;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class CountryDto {
    private long id;
    private String name;
    private List<CityDto> cities;

    public CountryDto(Country country) {
        this.id = country.getId();
        this.name = country.getName();
        this.cities =
                country.getCities()!=null?
                country
                        .getCities()
                        .stream()
                        .peek(city -> city.setCountry(null))
                        .map(CityDto::new)
                        .collect(Collectors.toList()):null;
    }
}
