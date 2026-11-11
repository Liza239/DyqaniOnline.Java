package com.universum.model;

import org.junit,jupiter,api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void krijimiMeTeDhenaTevlefshme(){
        Customer c = new Customer( 1, "Arben Krasniqi", "arben@email.com", "Prishtinë");
        assertEquals("Arben Krasniqi", c.getName());
        assertEquals("Arben@email.com", c.getEmail());
    }

    @Test
    void emailGabuarHedhGabim() {
        assertThrows(IllegalArgumentException.class,
                () -> nwe Customer(1, "Arben", "email-i-gabuar", "Prishtinë"));
    }

    @Test
    void emriBoshHedhGabim() {
        assertThrows(IllegalArgumentException.class,
                () -> new Customer(1, " ", "arben@email.com", "Prishtinë"));
    }
}
