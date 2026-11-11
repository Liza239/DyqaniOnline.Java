package com.universum.model;

public class CartItem {

    private final Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Produkti nuk mund të jetë null.");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Sasia duhet të jetë më e madhe se 0.");
        }
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Sasia duhet të jetë më e madhe se 0.");
        }
        this.quantity = quantity;
    }

    public void addQuantity(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Sasia për shtim duhet të jetë më e madhe se 0.");
        }
        this.quantity += amount;
    }

    public double getSubtotal() {
        return product.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return "CartItem{product=" + product.getName() + ", quantity=" + quantity
                + ", subtotal=" + getSubtotal() + "}";
    }
}