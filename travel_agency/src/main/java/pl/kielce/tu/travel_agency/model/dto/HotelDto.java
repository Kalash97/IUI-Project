package pl.kielce.tu.travel_agency.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kielce.tu.travel_agency.model.entities.Hotel;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class HotelDto {
    private long id;

    private String name;

    private AddressDto address;

    private List<TripDto> trips;

    public HotelDto(Hotel hotel) {
        this.id = hotel.getId();
        this.name = hotel.getName();
        this.address = new AddressDto(hotel.getAddress());
        this.trips = hotel.getTrips()
                .stream()
                .map(TripDto::new)
                .peek(tripDto -> tripDto.setHotels(null))
                .collect(Collectors.toList());
    }
}
