package pl.kielce.tu.travel_agency.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kielce.tu.travel_agency.model.dto.PersonDto;
import pl.kielce.tu.travel_agency.model.entities.Person;
import pl.kielce.tu.travel_agency.security.SecurityUtils;
import pl.kielce.tu.travel_agency.services.PersonService;

import java.util.List;

@RestController
@RequestMapping("/mvc/user")
public class PersonController {

    private final PersonService personService;

    private final SecurityUtils utils;

    @Autowired
    public PersonController(PersonService personService,
                            SecurityUtils utils) {
        this.personService = personService;
        this.utils = utils;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<PersonDto> getCustomerById(@PathVariable String id) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add")
    public ResponseEntity<PersonDto> registerCustomer(@RequestBody PersonDto customer) {
        return ResponseEntity.ok().build();
    }

    @PutMapping("/edit")
    public ResponseEntity<PersonDto> editCustomer(@RequestBody PersonDto customer) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable String id) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/info-about-me")
    public ResponseEntity<PersonDto> currentUser() throws Exception {
        return ResponseEntity.ok(new PersonDto(utils.getCurrentPerson()));
    }

    @GetMapping("/find")
    public ResponseEntity<List<PersonDto>> findByFirstnameAndLastname(@RequestParam String firstname, @RequestParam String lastname) {
        return ResponseEntity.ok(personService.findByFirstnameAndLastname(firstname, lastname));
    }
}
