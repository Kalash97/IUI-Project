package pl.kielce.tu.travel_agency.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kielce.tu.travel_agency.model.dto.TripDto;
import pl.kielce.tu.travel_agency.services.TripService;

import java.util.List;

@RestController
@RequestMapping("/mvc/trip")
public class TripCtrl {

    private final TripService tripService;

    @Autowired
    public TripCtrl(TripService tripService) {
        this.tripService = tripService;
    }

//    @GetMapping("mvc/trip/all-trips")
//    public ResponseEntity<List<TripDto>> getAllTrips() {
//        return ResponseEntity.ok(tripService.readAll()
//                .stream()
//                .map(TripDto::new)
//                .collect(Collectors.toList())
//        );
//    }

    @GetMapping("/all-trips")
    public ResponseEntity<List<TripDto>> getAllTrips() {
        return ResponseEntity.ok(tripService.getAllTrips());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<TripDto> getTripById(@PathVariable Long id) {
        return ResponseEntity.ok(tripService.getTripById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<TripDto> addTrip(@RequestBody TripDto trip) throws Exception {
        return ResponseEntity.ok(tripService.addTrip(trip));
    }

    @PutMapping("/edit")
    public ResponseEntity<TripDto> editTrip(@RequestBody TripDto trip) throws Exception {
        return ResponseEntity.ok(tripService.editTrip(trip));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTrip(@PathVariable Long id) {
        tripService.deleteTrip(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/available-trips")
    public ResponseEntity<?> getAvailableTrips() {
        return ResponseEntity.ok().build();
    }
}
