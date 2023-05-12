package de.ars.schulung.samples.customers.boundary;

import lombok.Data;

import java.util.UUID;

@Data
public class Customer {

    private UUID uuid;
    private String name;

}
