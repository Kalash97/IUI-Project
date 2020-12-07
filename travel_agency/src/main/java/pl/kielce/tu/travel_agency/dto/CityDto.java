package pl.kielce.tu.travel_agency.dto;

import lombok.Data;
import pl.kielce.tu.travel_agency.model.entities.City;
import pl.kielce.tu.travel_agency.model.entities.Country;

@Data
public class CityDto {

    private long id;

    private String name;

    private CountryDto country;

    public CityDto(City city) {
        this.id = city.getId();
        this.name = city.getName();
        this.country = new CountryDto(city.getCountry());
    }
}
