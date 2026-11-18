package com.universum;

public class Clothing extends Product {
    private String size;
    private String color;

    public Clothing(int id, String name, String description, double price, int stock,
                    String size, String color) {
        super(id, name, description, price, stock);
        setSize(size);
        setColor(color);
    }

    public String getSize() { return size; }
    public String getColor() { return color; }

    public void setSize(String size) {
        if (size == null || !size.matches("XS|S|M|L|XL|XXL")) {
            throw new IllegalArgumentException(
                    "Madhësia duhet të jetë XS, S, M, L, XL ose XXL.");
        }
        this.size = size;
    }

    public void setColor(String color) {
        if (color == null || color.isBlank()) {
            throw new IllegalArgumentException("Ngjyra nuk mund të jetë bosh.");
        }
        this.color = color.trim();
    }

    @Override
    public String getSizeLabel() {
        return size + ", " + color;
    }
}