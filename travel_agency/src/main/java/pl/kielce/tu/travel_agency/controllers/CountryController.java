package pl.kielce.tu.travel_agency.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kielce.tu.travel_agency.dto.CountryDto;

import java.util.List;

@RestController
public class CountryController {
    @GetMapping("mvc/country/all-countrys")
    public ResponseEntity<List<CountryDto>> getAllCountrys() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("mvc/country/id/{id}")
    public ResponseEntity<CountryDto> getCountryById(@PathVariable String id) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("mvc/country/add")
    public ResponseEntity<CountryDto> addCountry(@RequestBody CountryDto country) {
        return ResponseEntity.ok().build();
    }

    @PutMapping("mvc/country/edit")
    public ResponseEntity<CountryDto> editCountry(@RequestBody CountryDto country) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("mvc/country/delete/{id}")
    public ResponseEntity<?> deleteCountry(@PathVariable String id) {
        return ResponseEntity.ok().build();
    }
}
