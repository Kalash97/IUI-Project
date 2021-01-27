package pl.kielce.tu.travel_agency.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kielce.tu.travel_agency.model.dto.TripDto;
import pl.kielce.tu.travel_agency.model.entities.Trip;
import pl.kielce.tu.travel_agency.model.repositories.HotelRepo;
import pl.kielce.tu.travel_agency.model.repositories.TripRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripService extends AbstractEntityService<Trip> {

    private final TripRepo tripRepo;

    private final HotelRepo hotelRepo;

    @Autowired
    public TripService(TripRepo repo, HotelRepo hotelRepo) {
        this.tripRepo = repo;
        this.hotelRepo = hotelRepo;
    }


    @Override
    public JpaRepository<Trip, Long> getEntityRepository() {
        return tripRepo;
    }

    @Transactional
    public TripDto addTrip(TripDto tripDto) throws Exception{
        Trip trip = new Trip();
        trip.setStartingDate(tripDto.getStartingDate());
        trip.setHotels(
                tripDto.getHotels()
                .stream()
                .map(hotelDto -> hotelRepo.getOne(hotelDto.getId()))
                .collect(Collectors.toList())
        );
        trip.setDuration(tripDto.getDuration());
        tripRepo.save(trip);
        return new TripDto(trip);
    }

    @Transactional
    public TripDto editTrip(TripDto tripDto) throws Exception {
        if(!tripRepo.existsById(tripDto.getId())) {
            throw new ResourceNotFoundException("Nie znaleziono wycieczki o danym ID.");
        }
        Trip trip = tripRepo.getOne(tripDto.getId());
        trip.setDuration(tripDto.getDuration());
        trip.setStartingDate(tripDto.getStartingDate());
        trip.setHotels(
                tripDto.getHotels()
                .stream()
                .map(hotelDto -> hotelRepo.getOne(hotelDto.getId()))
                .collect(Collectors.toList())
        );
        tripRepo.save(trip);
        return new TripDto(trip);
    }

    @Transactional
    public void deleteTrip(Long id) {
        if(!tripRepo.existsById(id)) {
            throw new ResourceNotFoundException("Nie znaleziono wycieczki o danym ID.");
        }
        tripRepo.deleteById(id);
    }

    public TripDto getTripById(Long id) {
        if(!tripRepo.existsById(id)) {
            throw new ResourceNotFoundException("Nie znaleziono wycieczki o danym ID.");
        }

        return new TripDto(tripRepo.getOne(id));
    }

    public List<TripDto> getAllTrips() {
        return tripRepo
                .findAll()
                .stream()
                .map(TripDto::new)
                .collect(Collectors.toList());
    }
}
