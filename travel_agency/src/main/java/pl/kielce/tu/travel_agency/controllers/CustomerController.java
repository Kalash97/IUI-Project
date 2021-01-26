package pl.kielce.tu.travel_agency.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.kielce.tu.travel_agency.model.dto.CustomerDto;

import java.util.List;

@RestController
@RequestMapping("/mvc/customer")
public class CustomerController {

    @GetMapping("/find")
    public ResponseEntity<CustomerDto> getCustomer(@RequestParam String firstname, @RequestParam String lastname) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/info-about-me")
    public ResponseEntity<List<String>> getCustomer() {
        return null;
    }

    @DeleteMapping("/delete-me-and-logout")
    public ResponseEntity<?> deleteMyAccountAndEndSession(@PathVariable String id) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add")
    public ResponseEntity<CustomerDto> registerCustomer(@RequestBody CustomerDto customer) {
        return ResponseEntity.ok().build();
    }

    @PutMapping("/edit")
    public ResponseEntity<CustomerDto> editCustomer(@RequestBody CustomerDto customer) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable String id) {
        return ResponseEntity.ok().build();
    }
}
