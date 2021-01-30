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
import pl.kielce.tu.travel_agency.security.SecurityUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripService extends AbstractEntityService<Trip> {

    private final TripRepo tripRepo;

    private final HotelRepo hotelRepo;

    private final SecurityUtils utils;

    @Autowired
    public TripService(TripRepo repo,
                       HotelRepo hotelRepo,
                       SecurityUtils utils) {
        this.tripRepo = repo;
        this.hotelRepo = hotelRepo;
        this.utils = utils;
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
                tripDto.getHotels()!=null?
                        tripDto.getHotels()
                                .stream()
                                .map(hotelDto -> hotelRepo.getOne(hotelDto.getId()))
                                .collect(Collectors.toList()):null
        );
        trip.setName(tripDto.getName());
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

    public List<TripDto> getAvailableTrips() throws Exception{
        List<TripDto> trips = getAllTrips()
                .stream()
                .filter(
                        tripDto -> tripDto.getStartingDate().after(new Date())
                )
                .collect(Collectors.toList());
//        trips
//                .stream()
//                .flatMap(tripDto -> tripDto.getTickets().stream())
//                .map(ticketDto -> ticketDto.getId())
        List<Long> personReservedTrips = utils.getCurrentPerson()
                .getTickets()
                .stream()
                .map(ticket -> ticket.getTrip().getId())
                .collect(Collectors.toList());
        return trips.stream()
                .filter(tripDto -> !personReservedTrips.contains(tripDto.getId()))
                .collect(Collectors.toList());

    }
}
