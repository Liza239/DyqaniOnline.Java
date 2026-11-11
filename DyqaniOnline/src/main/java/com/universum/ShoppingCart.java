package com.universum.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShoppingCart {

    private final List<CartItem> items = new ArrayList<>();

    public void addProduct(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Produkti nuk mund të jetë null.");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Sasia duhet të jetë më e madhe se 0.");
        }

        CartItem existing = find(product);
        int alreadyInCart = (existing == null) ? 0 : existing.getQuantity();

        if (!product.hasStock(alreadyInCart + quantity)) {
            throw new IllegalStateException(
                    "Stok i pamjaftueshëm për: " + product.getName());
        }

        if (existing != null) {
            existing.addQuantity(quantity);
        } else {
            items.add(new CartItem(product, quantity));
        }
    }

    public void removeProduct(Product product) {
        items.removeIf(i -> i.getProduct().equals(product));
    }

    public double getTotal() {
        return items.stream().mapToDouble(CartItem::getSubtotal).sum();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clear() {
        items.clear();
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    private CartItem find(Product product) {
        for (CartItem item : items) {
            if (item.getProduct().equals(product)) {
                return item;
            }
        }
        return null;
    }
}