package com.universum;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductHierarchyTest {

    @Test
    void nenklasatJaneProdukte() {
        Product c = new Clothing(1, "Polo", "", 19.99, 10, "M", "Blu");
        Product f = new Footwear(2, "Sneakers", "", 59.99, 5, 42);
        assertTrue(c instanceof Product);
        assertTrue(f instanceof Product);
    }

    @Test
    void getSizeLabelPunonSipasNenklases() {
        Product c = new Clothing(1, "Polo", "", 19.99, 10, "M", "Blu");
        Product f = new Footwear(2, "Sneakers", "", 59.99, 5, 42);
        assertEquals("M, Blu", c.getSizeLabel());
        assertEquals("nr. 42", f.getSizeLabel());
    }

    @Test
    void madhesiaGabimGjuanGabim() {
        assertThrows(IllegalArgumentException.class,
                () -> new Clothing(1, "Polo", "", 19.99, 10, "Z", "Blu"));
    }

    @Test
    void numriKepucesGabimGjuanGabim() {
        assertThrows(IllegalArgumentException.class,
                () -> new Footwear(2, "Sneakers", "", 59.99, 5, 99));
    }

    @Test
    void porosiaZbretStokun() {
        Product c = new Clothing(1, "Polo", "", 19.99, 10, "M", "Blu");
        Customer k = new Customer(1, "Arben", "arben@email.com", "Prishtinë");
        k.getCart().addProduct(c, 3);
        k.placeOrder(1);
        assertEquals(7, c.getStock());
    }
}