package pl.kielce.tu.travel_agency.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kielce.tu.travel_agency.dto.TripDto;
import pl.kielce.tu.travel_agency.model.entities.Trip;
import pl.kielce.tu.travel_agency.services.TripService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TripCtrl {

    @Autowired
    private TripService tripService;

//    @GetMapping("mvc/trip/all-trips")
//    public ResponseEntity<List<TripDto>> getAllTrips() {
//        return ResponseEntity.ok(tripService.readAll()
//                .stream()
//                .map(TripDto::new)
//                .collect(Collectors.toList())
//        );
//    }
    @GetMapping("mvc/trip/all-trips")
    public ResponseEntity<List<TripDto>> getAllTrips() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("mvc/trip/id/{id}")
    public ResponseEntity<TripDto> getTripById(@PathVariable String id) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("mvc/trip/add")
    public ResponseEntity<TripDto> addTrip(@RequestBody TripDto trip) {
        return ResponseEntity.ok().build();
    }

    @PutMapping("mvc/trip/edit")
    public ResponseEntity<TripDto> editTrip(@RequestBody TripDto trip) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("mvc/trip/delete/{id}")
    public ResponseEntity<?> deleteTrip(@PathVariable String id) {
        return ResponseEntity.ok().build();
    }
}
