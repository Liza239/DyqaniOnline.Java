package com.universum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClothingTest {

    private Clothing bluza;

    @BeforeEach
    void setUp() {
        bluza = new Clothing(1, "Polo", "Bluzë pambuku", 19.99, 10, "M", "Blu");
    }

    @Test
    void konstruktoriVendosFushatSakte() {
        assertEquals(1, bluza.getId());
        assertEquals("Polo", bluza.getName());
        assertEquals(19.99, bluza.getPrice());
        assertEquals(10, bluza.getStock());
        assertEquals("M", bluza.getSize());
        assertEquals("Blu", bluza.getColor());
    }

    @Test
    void clothingEshteProduct() {
        assertTrue(bluza instanceof Product);
    }

    @Test
    void getSizeLabelKthenMadhesineDheNgjyren() {
        assertEquals("M, Blu", bluza.getSizeLabel());
    }

    @Test
    void setSizePranonMadhesiTeVlefshme() {
        for (String s : new String[]{"XS", "S", "M", "L", "XL", "XXL"}) {
            bluza.setSize(s);
            assertEquals(s, bluza.getSize());
        }
    }

    @Test
    void setSizeRefuzonMadhesiTePavlefshme() {
        assertThrows(IllegalArgumentException.class, () -> bluza.setSize("Z"));
        assertThrows(IllegalArgumentException.class, () -> bluza.setSize("m"));
        assertThrows(IllegalArgumentException.class, () -> bluza.setSize(""));
        assertThrows(IllegalArgumentException.class, () -> bluza.setSize(null));
    }

    @Test
    void setColorFshinHapesirat() {
        bluza.setColor("  E kuqe  ");
        assertEquals("E kuqe", bluza.getColor());
    }

    @Test
    void setColorRefuzonVleraBosh() {
        assertThrows(IllegalArgumentException.class, () -> bluza.setColor(null));
        assertThrows(IllegalArgumentException.class, () -> bluza.setColor(""));
        assertThrows(IllegalArgumentException.class, () -> bluza.setColor("   "));
    }

    @Test
    void konstruktoriRefuzonMadhesiTePavlefshme() {
        assertThrows(IllegalArgumentException.class,
                () -> new Clothing(2, "Jeans", "", 39.99, 5, "Z", "Zi"));
    }

    @Test
    void konstruktoriRefuzonCmimTeNegativ() {
        assertThrows(IllegalArgumentException.class,
                () -> new Clothing(2, "Jeans", "", -5, 5, "M", "Zi"));
    }

    @Test
    void toStringPerfshinEmrinEKlasesDheMadhesine() {
        String tekst = bluza.toString();
        assertTrue(tekst.contains("Clothing"));
        assertTrue(tekst.contains("Polo"));
        assertTrue(tekst.contains("M, Blu"));
    }
}