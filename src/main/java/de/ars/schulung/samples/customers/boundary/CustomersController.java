package de.ars.schulung.samples.customers.boundary;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomersController {

    @GetMapping
    Collection<Object> getCustomers() {
        return Collections.emptyList();
    }

    @PostMapping
    ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        customer.setUuid(UUID.randomUUID());
        return ResponseEntity
          .created(URI.create("gelbekatze"))
          .body(customer);

    }

}
