package pl.kielce.tu.travel_agency.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kielce.tu.travel_agency.dto.CityDto;
import pl.kielce.tu.travel_agency.dto.CityDto;

import java.util.List;

@RestController
public class CityController {
    @GetMapping("mvc/city/all-citys")
    public ResponseEntity<List<CityDto>> getAllCitys() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("mvc/city/id/{id}")
    public ResponseEntity<CityDto> getCityById(@PathVariable String id) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("mvc/city/add")
    public ResponseEntity<CityDto> addCity(@RequestBody CityDto city) {
        return ResponseEntity.ok().build();
    }

    @PutMapping("mvc/city/edit")
    public ResponseEntity<CityDto> editCity(@RequestBody CityDto city) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("mvc/city/delete/{id}")
    public ResponseEntity<?> deleteCity(@PathVariable String id) {
        return ResponseEntity.ok().build();
    }
}
