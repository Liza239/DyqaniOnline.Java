package com.universum.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {

    private final Product xhakete = new Product(1, "Xhaketë dimërore", "Madhësia M", 60.0, 5);
    private final Product bluze = new Product(2, "Bluzë me mëngë të gjata", "Madhësia S", 20.0, 10);
    private final Customer customer =
            new Customer(1, "Arben Krasniqi", "arben@email.com", "Prishtinë");

    @Test
    void shporta_fillon_bosh() {
        assertTrue(new ShoppingCart().isEmpty());
    }

    @Test
    void shtimiIProduktitLlogaritTotalin() {
        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(xhakete, 2);
        assertEquals(120.0, cart.getTotal(), 0.001);
    }

    @Test
    void shtimiIDisaProdukteveLlogaritTotalin() {
        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(xhakete, 1);
        cart.addProduct(bluze, 3);
        assertEquals(120.0, cart.getTotal(), 0.001);
        assertEquals(4, cart.getTotalItems());
    }

    @Test
    void shtimiIPerseritIProduktitPercaktonSasine() {
        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(bluze, 2);
        cart.addProduct(bluze, 3);
        assertEquals(5, cart.getTotalItems());
    }

    @Test
    void shtimiMeShumeSeStokuHedhGabim() {
        ShoppingCart cart = new ShoppingCart();
        assertThrows(IllegalStateException.class, () -> cart.addProduct(xhakete, 10));
    }

    @Test
    void sasiaZeroHedhGabim() {
        ShoppingCart cart = new ShoppingCart();
        assertThrows(IllegalArgumentException.class, () -> cart.addProduct(xhakete, 0));
    }

    @Test
    void produktiNullHedhGabim() {
        ShoppingCart cart = new ShoppingCart();
        assertThrows(IllegalArgumentException.class, () -> cart.addProduct(null, 1));
    }

    @Test
    void heqjaEProduktitBenShportenBosh() {
        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(bluze, 2);
        cart.removeProduct(bluze);
        assertTrue(cart.isEmpty());
    }

    @Test
    void perditesimiISasisNdryshonTotalin() {
        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(bluze, 1);
        cart.updateQuantity(bluze, 4);
        assertEquals(80.0, cart.getTotal(), 0.001);
    }

    @Test
    void checkoutKrijonPorosineDheUlStokun() {
        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(xhakete, 2);
        Order order = cart.checkout(1, customer);

        assertEquals(120.0, order.getTotal(), 0.001);
        assertEquals(3, xhakete.getStock());
        assertTrue(cart.isEmpty());
    }

    @Test
    void checkoutMeShporteBoshHedhGabim() {
        ShoppingCart cart = new ShoppingCart();
        assertThrows(IllegalStateException.class, () -> cart.checkout(1, customer));
    }
}
