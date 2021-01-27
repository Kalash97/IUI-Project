package pl.kielce.tu.travel_agency.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kielce.tu.travel_agency.model.dto.HotelDto;
import pl.kielce.tu.travel_agency.services.HotelService;

import java.util.List;

@RestController
@RequestMapping("/mvc/hotel")
public class HotelController {

    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/all-hotels")
    public ResponseEntity<List<HotelDto>> getAllHotels() throws Exception {
        return ResponseEntity.ok(hotelService.getAllHotels());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<HotelDto> getHotelById(@PathVariable Long id) throws Exception{
        return ResponseEntity.ok(hotelService.getHotelById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<HotelDto> addHotel(@RequestBody HotelDto hotel) throws Exception{
        return ResponseEntity.ok(hotelService.addHotel(hotel));
    }

    @PutMapping("/edit")
    public ResponseEntity<HotelDto> editHotel(@RequestBody HotelDto hotel) throws Exception{
        return ResponseEntity.ok(hotelService.addHotel(hotel));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteHotel(@PathVariable Long id) throws Exception{
        hotelService.deleteHotel(id);
        return ResponseEntity.ok().build();
    }
}
