package de.ars.schulung.samples.customers.boundary;

import de.ars.schulung.samples.customers.domain.CustomerSink;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomersController {

    private final CustomerSink sink;

    @GetMapping
    Collection<Customer> getCustomers() {
        return sink.findAll();
    }

    @PostMapping
    ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        customer.setUuid(UUID.randomUUID());
        return ResponseEntity
          .created(URI.create("gelbekatze"))
          .body(customer);

    }

}
