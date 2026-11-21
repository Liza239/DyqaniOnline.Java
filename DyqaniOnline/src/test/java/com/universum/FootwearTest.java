package com.universum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FootwearTest {

    private Footwear atlete;

    @BeforeEach
    void setUp() {
        atlete = new Footwear(2, "Sneakers", "Atlete sportive", 59.99, 5, 42);
    }

    @Test
    void konstruktoriVendosFushatSakte() {
        assertEquals(2, atlete.getId());
        assertEquals("Sneakers", atlete.getName());
        assertEquals(59.99, atlete.getPrice());
        assertEquals(5, atlete.getStock());
        assertEquals(42, atlete.getShoeSize());
    }

    @Test
    void footwearEshteProduct() {
        assertTrue(atlete instanceof Product);
    }

    @Test
    void getSizeLabelKthenNumrinECepuces() {
        assertEquals("nr. 42", atlete.getSizeLabel());
    }

    @Test
    void setShoeSizePranonVleraNeKufij() {
        atlete.setShoeSize(20);
        assertEquals(20, atlete.getShoeSize());
        atlete.setShoeSize(50);
        assertEquals(50, atlete.getShoeSize());
    }

    @Test
    void setShoeSizeRefuzonVleraJashteKufijve() {
        assertThrows(IllegalArgumentException.class, () -> atlete.setShoeSize(19));
        assertThrows(IllegalArgumentException.class, () -> atlete.setShoeSize(51));
        assertThrows(IllegalArgumentException.class, () -> atlete.setShoeSize(0));
        assertThrows(IllegalArgumentException.class, () -> atlete.setShoeSize(-42));
    }

    @Test
    void konstruktoriRefuzonNumerTePavlefshem() {
        assertThrows(IllegalArgumentException.class,
                () -> new Footwear(3, "Boots", "", 79.99, 2, 99));
    }

    @Test
    void stokuZvogelohetDheRritet() {
        atlete.decreaseStock(2);
        assertEquals(3, atlete.getStock());
        atlete.increaseStock(4);
        assertEquals(7, atlete.getStock());
    }

    @Test
    void decreaseStockRefuzonSasiMbiStok() {
        assertThrows(IllegalStateException.class, () -> atlete.decreaseStock(100));
    }

    @Test
    void hasStockKontrollonSasine() {
        assertTrue(atlete.hasStock(5));
        assertFalse(atlete.hasStock(6));
        assertFalse(atlete.hasStock(0));
    }

    @Test
    void toStringPerfshinEmrinEKlasesDheNumrin() {
        String tekst = atlete.toString();
        assertTrue(tekst.contains("Footwear"));
        assertTrue(tekst.contains("Sneakers"));
        assertTrue(tekst.contains("nr. 42"));
    }
}