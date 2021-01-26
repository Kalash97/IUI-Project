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
    public ResponseEntity<List<CityDto>> getAllCities() throws Exception{
        return ResponseEntity.ok(cityService.getCities());
    }

    @GetMapping("id/{id}")
    public ResponseEntity<CityDto> getCityById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(cityService.getCityById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<CityDto> addCity(@RequestBody CityDto city) throws Exception {
        return ResponseEntity.ok(cityService.addCity(city));
    }

    @PutMapping("/edit")
    public ResponseEntity<CityDto> editCity(@RequestBody CityDto city) throws Exception{
        return ResponseEntity.ok(cityService.editCity(city));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCity(@PathVariable Long id) throws Exception{
        cityService.deleteCity(id);
        return ResponseEntity.ok().build();
    }
}
