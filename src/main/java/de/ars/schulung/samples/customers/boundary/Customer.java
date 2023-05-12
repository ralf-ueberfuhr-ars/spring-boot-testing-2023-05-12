package de.ars.schulung.samples.customers.boundary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private UUID uuid;
    private String name;

}
