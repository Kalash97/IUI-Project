package pl.kielce.tu.travel_agency.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import pl.kielce.tu.travel_agency.exception.DuplicateException;
import pl.kielce.tu.travel_agency.model.dto.CountryDto;
import pl.kielce.tu.travel_agency.model.entities.Country;
import pl.kielce.tu.travel_agency.model.repositories.CountryRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService extends AbstractEntityService<Country> {

    private final CountryRepo repo;

    private final ModelMapper mapper;

    @Autowired
    public CountryService(CountryRepo repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public JpaRepository<Country, Long> getEntityRepository() {
        return repo;
    }

    public CountryDto addCountry(CountryDto countryDto) throws Exception {
        if(repo.findByName(countryDto.getName()).isPresent()) {
            throw new DuplicateException("Kraj "+ countryDto.getName() + " już istnieje.");
        }

        Country country = mapper.map(countryDto, Country.class);
        //country.setName(countryDto.getName());
        repo.save(country);
        return new CountryDto(country);
    }

    public CountryDto editCountry(CountryDto dto) throws Exception{
        if(!repo.existsById(dto.getId())) {
            throw new ResourceNotFoundException("Country with given ID not found.");
        }

        Country country = repo.getOne(dto.getId());
        country.setName(dto.getName());
        repo.save(country);

        return new CountryDto(country);
    }

    public void deleteCountry(Long id) {
        if(!repo.existsById(id)) {
            throw new ResourceNotFoundException("Country with given ID not found.");
        }

        repo.deleteById(id);
    }

    public CountryDto getCountryById(Long id) {
        if(!repo.existsById(id)) {
            throw new ResourceNotFoundException("Country with given ID not found.");
        }

        return new CountryDto(repo.getOne(id));
    }

    public List<CountryDto> getCountries() {
        return repo.findAll()
                .stream().map(CountryDto::new)
                .collect(Collectors.toList());
    }
}
