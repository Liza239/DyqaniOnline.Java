package com.universum.model;

import java.util.Objects;

public class Product {

    private final int id;
    private String name;
    private String description;
    private double price;
    private int stock;

    public Product(int id, String name, String description, double price, int stock) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID duhet të jetë më e madhe se 0.");
        }
        this.id = id;
        setName(name);
        setDescription(description);
        setPrice(price);
        setStock(stock);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Emri i produktit nuk mund të jetë bosh.");
        }
        this.name = name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = (description == null) ? "" : description.trim();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Çmimi duhet të jetë më i madh se 0.");
        }
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("Stoku nuk mund të jetë negativ.");
        }
        this.stock = stock;
    }

    public boolean isAvailable() {
        return stock > 0;
    }

    public void increaseStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Sasia duhet të jetë më e madhe se 0.");
        }
        stock += quantity;
    }

    public void decreaseStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Sasia duhet të jetë më e madhe se 0.");
        }
        if (quantity > stock) {
            throw new IllegalStateException("Nuk ka stok të mjaftueshëm.");
        }
        stock -= quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Product{id=" + id + ", name='" + name + "', price=" + price + ", stock=" + stock + "}";
    }
}
