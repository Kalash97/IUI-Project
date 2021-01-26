package pl.kielce.tu.travel_agency.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kielce.tu.travel_agency.model.dto.CityDto;
import pl.kielce.tu.travel_agency.services.CityService;

import java.util.List;

@RestController
@RequestMapping("/mvc/city")
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/all-cities")
    public ResponseEntity<List<CityDto>> getAllCitys() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("id/{id}")
    public ResponseEntity<CityDto> getCityById(@PathVariable String id) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add")
    public ResponseEntity<CityDto> addCity(@RequestBody CityDto city) {
        return ResponseEntity.ok().build();
    }

    @PutMapping("/edit")
    public ResponseEntity<CityDto> editCity(@RequestBody CityDto city) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCity(@PathVariable String id) {
        return ResponseEntity.ok().build();
    }
}
