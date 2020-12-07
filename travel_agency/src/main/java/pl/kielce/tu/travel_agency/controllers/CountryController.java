package pl.kielce.tu.travel_agency.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kielce.tu.travel_agency.model.dto.CountryDto;

import java.util.List;

@RestController
@RequestMapping("/mvc/country")
public class CountryController {

    @GetMapping("/all-countrys")
    public ResponseEntity<List<CountryDto>> getAllCountrys() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<CountryDto> getCountryById(@PathVariable String id) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add")
    public ResponseEntity<CountryDto> addCountry(@RequestBody CountryDto country) {
        return ResponseEntity.ok().build();
    }

    @PutMapping("/edit")
    public ResponseEntity<CountryDto> editCountry(@RequestBody CountryDto country) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCountry(@PathVariable String id) {
        return ResponseEntity.ok().build();
    }
}
