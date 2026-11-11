package com.universum.model;

public class OrderItem {

    private final Product product;
    private final int quantity;
    private final double unitPrice;

    public OrderItem(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Produkti nuk mund të jetë null.");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Sasia duhet të jetë më e madhe se 0.");
        }
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = product.getPrice();
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getSubtotal() {
        return unitPrice * quantity;
    }

    @Override
    public String toString() {
        return "OrderItem{product=" + product.getName() + ", quantity=" + quantity
                + ", unitPrice=" + unitPrice + "}";
    }
}