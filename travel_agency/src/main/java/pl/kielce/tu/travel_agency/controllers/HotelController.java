package pl.kielce.tu.travel_agency.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kielce.tu.travel_agency.dto.HotelDto;
import pl.kielce.tu.travel_agency.dto.HotelDto;

import java.util.List;

@RestController
public class HotelController {
    @GetMapping("mvc/hotel/all-hotels")
    public ResponseEntity<List<HotelDto>> getAllHotels() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("mvc/hotel/id/{id}")
    public ResponseEntity<HotelDto> getHotelById(@PathVariable String id) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("mvc/hotel/add")
    public ResponseEntity<HotelDto> addHotel(@RequestBody HotelDto hotel) {
        return ResponseEntity.ok().build();
    }

    @PutMapping("mvc/hotel/edit")
    public ResponseEntity<HotelDto> editHotel(@RequestBody HotelDto hotel) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("mvc/hotel/delete/{id}")
    public ResponseEntity<?> deleteHotel(@PathVariable String id) {
        return ResponseEntity.ok().build();
    }
}
