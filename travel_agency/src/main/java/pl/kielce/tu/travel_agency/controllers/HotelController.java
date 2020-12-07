package pl.kielce.tu.travel_agency.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kielce.tu.travel_agency.model.dto.HotelDto;

import java.util.List;

@RestController
@RequestMapping("/mvc/hotel")
public class HotelController {

    @GetMapping("/all-hotels")
    public ResponseEntity<List<HotelDto>> getAllHotels() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<HotelDto> getHotelById(@PathVariable String id) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add")
    public ResponseEntity<HotelDto> addHotel(@RequestBody HotelDto hotel) {
        return ResponseEntity.ok().build();
    }

    @PutMapping("/edit")
    public ResponseEntity<HotelDto> editHotel(@RequestBody HotelDto hotel) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteHotel(@PathVariable String id) {
        return ResponseEntity.ok().build();
    }
}
