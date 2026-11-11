package com.universum.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void krijimiMeTeDhenaTeVlefshme() {
        Product p = new Product(1, "Xhaketë dimërore", "Xhaketë e ngrohtë, madhësia M", 59.99, 10);
        assertEquals("Xhaketë dimërore", p.getName());
        assertEquals(59.99, p.getPrice());
        assertEquals(10, p.getStock());
        assertTrue(p.isAvailable());
    }

    @Test
    void cmimiNegativHedhGabim() {
        assertThrows(IllegalArgumentException.class,
                () -> new Product(1, "Bluzë", "", -5, 10));
    }

    @Test
    void cmimiZeroHedhGabim() {
        assertThrows(IllegalArgumentException.class,
                () -> new Product(1, "Bluzë", "", 0, 10));
    }

    @Test
    void emriBosHedhGabim() {
        assertThrows(IllegalArgumentException.class,
                () -> new Product(1, " ", "", 20, 10));
    }

    @Test
    void idJoValideHedhGabim() {
        assertThrows(IllegalArgumentException.class,
                () -> new Product(0, "Pantallona", "", 35.0, 10));
    }

    @Test
    void stokuNegativHedhGabim() {
        assertThrows(IllegalArgumentException.class,
                () -> new Product(1, "Këmishë", "", 25.0, -1));
    }

    @Test
    void produktiPaStokNukEshteIDisponueshem() {
        Product p = new Product(1, "Këpucë sportive", "", 49.99, 0);
        assertFalse(p.isAvailable());
    }

    @Test
    void zbritjaMeShumeSeStokuHedhGabim() {
        Product p = new Product(1, "Xhinse", "", 39.99, 3);
        assertThrows(IllegalStateException.class, () -> p.decreaseStock(5));
    }

    @Test
    void zbritjaERegulltPerditesonStokun() {
        Product p = new Product(1, "Xhinse", "", 39.99, 10);
        p.decreaseStock(4);
        assertEquals(6, p.getStock());
    }

    @Test
    void shtimiIStokutPerditesonStokun() {
        Product p = new Product(1, "Xhinse", "", 39.99, 10);
        p.increaseStock(5);
        assertEquals(15, p.getStock());
    }
}
