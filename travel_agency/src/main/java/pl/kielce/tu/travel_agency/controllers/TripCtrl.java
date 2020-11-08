package pl.kielce.tu.travel_agency.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kielce.tu.travel_agency.model.entities.Trip;
import pl.kielce.tu.travel_agency.services.TripService;

import java.util.List;

@RestController
public class TripCtrl {

    @Autowired
    private TripService tripService;

    @GetMapping("mvc/trip/all-trips")
    public List<Trip> getAllTrips() {
        return tripService.readAll();
    }

}
