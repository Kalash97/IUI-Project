package pl.kielce.tu.travel_agency.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kielce.tu.travel_agency.model.dto.AddressDto;
import pl.kielce.tu.travel_agency.model.dto.HotelDto;
import pl.kielce.tu.travel_agency.model.entities.Hotel;
import pl.kielce.tu.travel_agency.model.repositories.AddressRepo;
import pl.kielce.tu.travel_agency.model.repositories.HotelRepo;

import java.util.Optional;

@Service
public class HotelService extends AbstractEntityService<Hotel> {


    private final HotelRepo hotelRepo;

    private final AddressRepo addressRepo;

    private final AddressService addressService;

    @Autowired
    public HotelService(HotelRepo repo, AddressRepo addressRepo, AddressService addressService) {
        this.hotelRepo = repo;
        this.addressRepo = addressRepo;
        this.addressService = addressService;
    }

    @Override
    public JpaRepository<Hotel, Long> getEntityRepository() {
        return hotelRepo;
    }

    @Transactional
    public HotelDto addHotel(HotelDto hotelDto) throws Exception{
        Hotel hotel = new Hotel();
        hotel.setName(hotelDto.getName());
        writeAddress(hotelDto, hotel);
        hotelRepo.save(hotel);
        return new HotelDto(hotel);
    }



    @Transactional
    public HotelDto editHotel(HotelDto hotelDto) throws Exception{
        Optional<Hotel> hotelOptional = hotelRepo.findById(hotelDto.getId());
        if(!hotelOptional.isPresent()) {
            throw new ResourceNotFoundException("Hotel with given ID not found.");
        }
        Hotel hotel = hotelOptional.get();
        hotel.setName(hotelDto.getName());
        writeAddress(hotelDto, hotel);
        hotelRepo.save(hotel);
        return new HotelDto(hotel);
    }

    @Transactional
    public void deleteHotel(Long id) {
        if(!hotelRepo.existsById(id)) {
            throw new ResourceNotFoundException("Hotel with given ID not found.");
        }

        hotelRepo.deleteById(id);
    }

    private void writeAddress(HotelDto hotelDto, Hotel hotel) throws Exception {
        if(hotelDto.getAddress() != null) {
            AddressDto addressToSet;
            if(!addressRepo.existsById(hotelDto.getAddress().getId())) {
                addressToSet = addressService.addAddress(hotelDto.getAddress());
            }
            else {
                addressToSet = new AddressDto(addressRepo.getOne(hotelDto.getAddress().getId()));
            }
            hotel.setAddress(addressRepo.getOne(addressToSet.getId()));
        }
        else hotel.setAddress(null);
    }
}
