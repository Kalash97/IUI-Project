package pl.kielce.tu.travel_agency.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import pl.kielce.tu.travel_agency.model.dto.AddressDto;
import pl.kielce.tu.travel_agency.model.dto.CityDto;
import pl.kielce.tu.travel_agency.model.entities.Address;
import pl.kielce.tu.travel_agency.model.entities.City;
import pl.kielce.tu.travel_agency.model.repositories.CityRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService extends AbstractEntityService<City> {

    private final CityRepo repo;

    private final ModelMapper mapper;

    @Autowired
    public CityService(CityRepo repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public JpaRepository<City, Long> getEntityRepository() {
        return repo;
    }

    public CityDto addCity(CityDto dto) {
        City city = mapper.map(dto, City.class);
        repo.save(city);

        return new CityDto(city);
    }

    public CityDto editCity(CityDto dto) throws Exception {
        if(!repo.existsById(dto.getId())) {
            throw new ResourceNotFoundException("City with given ID not found.");
        }

        City city = repo.getOne(dto.getId());
        city.setName(dto.getName());
        List<Address> addresses = dto.getAddresses()!=null?
                dto.getAddresses()
                .stream()
                .peek(addressDto -> addressDto.setCityId(city.getId()))
                .map(addressDto -> {
                    Address address = new Address();
                    address.setStreet(addressDto.getStreet());
                    address.setNumber(addressDto.getNumber());
                    return address;
                })
                .collect(Collectors.toList())
                :null;
        city.setAddresses(addresses);
        repo.save(city);
        return new CityDto(city);
    }

    public void deleteCity(Long id) throws Exception {
        if(!repo.existsById(id)) {
            throw new ResourceNotFoundException("City with given ID not found.");
        }
        repo.deleteById(id);
    }

    public CityDto getCityById(Long id) throws Exception {
        if(!repo.existsById(id)) {
            throw new ResourceNotFoundException("City with given ID not found.");
        }
        return new CityDto(repo.getOne(id));
    }

    public List<CityDto> getCities() throws Exception {
        return repo.findAll()
                .stream()
                .map(CityDto::new)
                .collect(Collectors.toList());
    }
}
