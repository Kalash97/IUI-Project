package pl.kielce.tu.travel_agency.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kielce.tu.travel_agency.model.dto.PersonDto;

@RestController
public class PersonController {

    @GetMapping("mvc/user/id/{id}")
    public ResponseEntity<PersonDto> getCustomerById(@PathVariable String id) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("mvc/user/add")
    public ResponseEntity<PersonDto> registerCustomer(@RequestBody PersonDto customer) {
        return ResponseEntity.ok().build();
    }

    @PutMapping("mvc/user/edit")
    public ResponseEntity<PersonDto> editCustomer(@RequestBody PersonDto customer) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("mvc/user/delete/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable String id) {
        return ResponseEntity.ok().build();
    }
}
