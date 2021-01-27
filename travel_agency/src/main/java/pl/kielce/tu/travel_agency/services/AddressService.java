package pl.kielce.tu.travel_agency.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import pl.kielce.tu.travel_agency.exception.DuplicateException;
import pl.kielce.tu.travel_agency.model.dto.AddressDto;
import pl.kielce.tu.travel_agency.model.entities.Address;
import pl.kielce.tu.travel_agency.model.repositories.AddressRepo;
import pl.kielce.tu.travel_agency.model.repositories.CityRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService extends AbstractEntityService<Address> {

    private final AddressRepo addressRepo;

    private final CityRepo cityRepo;

    @Autowired
    public AddressService(AddressRepo addressRepo, CityRepo cityRepo) {
        this.addressRepo = addressRepo;
        this.cityRepo = cityRepo;
    }

    @Override
    public JpaRepository<Address, Long> getEntityRepository() {
        return addressRepo;
    }

    public AddressDto addAddress(AddressDto addressDto) throws Exception {
        if(!cityRepo.existsById(addressDto.getCityId())) {
            throw new ResourceNotFoundException("Nie znaleziono adresu o takim identyfikatorze.");
        }
        if(addressRepo.findByCityIdAndStreetAndNumber(addressDto.getCityId(), addressDto.getStreet(), addressDto.getNumber()).isPresent()) {
            throw new DuplicateException("Taki adres już istnieje!");
        }

        Address address = new Address();
        address.setStreet(addressDto.getStreet());
        address.setNumber(addressDto.getNumber());
        address.setCity(cityRepo.getOne(addressDto.getCityId()));

        addressRepo.save(address);
        return new AddressDto(address);
    }

    public AddressDto editAddress(AddressDto addressDto) {
        if(!addressRepo.existsById(addressDto.getId())) {
            throw new ResourceNotFoundException("Nie znaleziono adresu o takim identyfikatorze.");
        }

        Address address = addressRepo.getOne(addressDto.getId());
        address.setStreet(addressDto.getStreet());
        address.setNumber(addressDto.getNumber());
        address.setCity(cityRepo.getOne(addressDto.getCityId()));
        addressRepo.save(address);

        return new AddressDto(address);
    }

    public void deleteAddress(Long id) {
        if(!addressRepo.existsById(id)) {
            throw new ResourceNotFoundException("Nie znaleziono adresu o takim identyfikatorze.");
        }
        addressRepo.deleteById(id);
    }

    public AddressDto getOne(Long id) {
        if(!addressRepo.existsById(id)) {
            throw new ResourceNotFoundException("Nie znaleziono adresu o takim identyfikatorze.");
        }

        return new AddressDto(addressRepo.getOne(id));
    }

    public List<AddressDto> getAddresses() {
        return addressRepo
                .findAll()
                .stream()
                .map(AddressDto::new)
                .collect(Collectors.toList());
    }
}
