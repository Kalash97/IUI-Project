package pl.kielce.tu.travel_agency.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kielce.tu.travel_agency.model.dto.CountryDto;
import pl.kielce.tu.travel_agency.services.CountryService;

import java.util.List;

@RestController
@RequestMapping("/mvc/country")
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/all-countries")
    public ResponseEntity<List<CountryDto>> getAllCountries() {
        return ResponseEntity.ok(countryService.getCountries());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<CountryDto> getCountryById(@PathVariable Long id) {

        return ResponseEntity.ok(countryService.getCountryById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<CountryDto> addCountry(@RequestBody CountryDto country) throws Exception {
        return ResponseEntity.ok(countryService.addCountry(country));
    }

    @PutMapping("/edit")
    public ResponseEntity<CountryDto> editCountry(@RequestBody CountryDto country) throws Exception {
        return ResponseEntity.ok(countryService.editCountry(country));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCountry(@PathVariable Long id) {
        countryService.deleteCountry(id);
        return ResponseEntity.ok().build();
    }
}
