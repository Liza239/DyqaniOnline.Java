package com.universum;

public class Main {

    public static void main(String[] args) {
        System.out.println("--- Dyqani Online për veshje - Benerta, Deliza dhe Eulina ---");

        Product bluze = new Clothing(1, "Polo", "Bluzë pambuku", 19.99, 10, "M", "Blu");
        Product kepuce = new Footwear(2, "Sneakers", "Atlete sportive", 59.99, 5, 42);

        Product[] produktet = { bluze, kepuce };
        for (Product p : produktet) {
            System.out.println(p);
        }

        Customer klienti = new Customer(1, "Arben Krasniqi", "arben@email.com", "Prishtinë");
        klienti.getCart().addProduct(bluze, 2);
        klienti.getCart().addProduct(kepuce, 1);
        System.out.println("Totali i shportës: " + klienti.getCart().getTotal());

        Order porosia = klienti.placeOrder(100);
        porosia.pay();
        System.out.println(porosia);
        System.out.println("Stoku i bluzës pas porosisë: " + bluze.getStock());
    }
}