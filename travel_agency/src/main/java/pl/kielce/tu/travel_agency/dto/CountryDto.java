package pl.kielce.tu.travel_agency.dto;

import lombok.Data;
import pl.kielce.tu.travel_agency.model.entities.City;
import pl.kielce.tu.travel_agency.model.entities.Country;

import java.util.List;
import java.util.stream.Collectors;

@Data
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
                        .map(CityDto::new)
                        .peek(cityDto -> cityDto.setCountry(null))
                        .collect(Collectors.toList()):null;
    }
}
