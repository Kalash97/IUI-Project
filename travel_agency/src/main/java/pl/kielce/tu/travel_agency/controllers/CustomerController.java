package pl.kielce.tu.travel_agency.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kielce.tu.travel_agency.dto.CustomerDto;

@RestController
public class CustomerController {

    @GetMapping("mvc/customer/id/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable String id) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("mvc/customer/add")
    public ResponseEntity<CustomerDto> registerCustomer(@RequestBody CustomerDto customer) {
        return ResponseEntity.ok().build();
    }

    @PutMapping("mvc/customer/edit")
    public ResponseEntity<CustomerDto> editCustomer(@RequestBody CustomerDto customer) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("mvc/customer/delete/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable String id) {
        return ResponseEntity.ok().build();
    }
}
