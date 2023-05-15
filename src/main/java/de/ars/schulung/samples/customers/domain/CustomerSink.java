package de.ars.schulung.samples.customers.domain;

import de.ars.schulung.samples.customers.boundary.Customer;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

@Component
public class CustomerSink {
    public Collection<Customer> findAll() {
        return Collections.emptyList();
    }
}
