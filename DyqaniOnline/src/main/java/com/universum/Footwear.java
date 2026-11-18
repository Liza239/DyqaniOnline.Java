package com.universum;

public class Footwear extends Product {
    private int shoeSize;

    public Footwear(int id, String name, String description, double price, int stock,
                    int shoeSize) {
        super(id, name, description, price, stock);
        setShoeSize(shoeSize);
    }

    public int getShoeSize() { return shoeSize; }

    public void setShoeSize(int shoeSize) {
        if (shoeSize < 20 || shoeSize > 50) {
            throw new IllegalArgumentException(
                    "Numri i këpucës duhet të jetë mes 20 dhe 50.");
        }
        this.shoeSize = shoeSize;
    }

    @Override
    public String getSizeLabel() {
        return "nr. " + shoeSize;
    }
}