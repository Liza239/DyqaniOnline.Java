package com.universum;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Customer {

    private final int id;
    private String name;
    private String email;
    private String address;
    private String phone;
    private final LocalDate registeredOn;
    private final List<Order> orders = new ArrayList<>();
    private final ShoppingCart cart = new ShoppingCart();

    public Customer(int id, String name, String email, String address) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID duhet të jetë më e madhe se 0.");
        }
        this.id = id;
        setName(name);
        setEmail(email);
        setAddress(address);
        this.registeredOn = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Emri nuk mund të jetë bosh.");
        }
        this.name = name.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !email.trim().matches("^[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+$")) {
            throw new IllegalArgumentException("Email-i nuk është në format të vlefshëm.");
        }
        this.email = email.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("Adresa nuk mund të jetë bosh.");
        }
        this.address = address.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone == null || !phone.trim().matches("^\\+?[0-9]{8,15}$")) {
            throw new IllegalArgumentException("Numri i telefonit nuk është i vlefshëm.");
        }
        this.phone = phone.trim();
    }

    public LocalDate getRegisteredOn() {
        return registeredOn;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void addOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Porosia nuk mund të jetë null.");
        }
        orders.add(order);
    }

    public Order placeOrder(int orderId) {
        if (cart.isEmpty()) {
            throw new IllegalStateException("Shporta është bosh.");
        }

        for (CartItem ci : cart.getItems()) {
            if (!ci.getProduct().hasStock(ci.getQuantity())) {
                throw new IllegalStateException(
                        "Stok i pamjaftueshëm për: " + ci.getProduct().getName());
            }
        }

        Order order = new Order(orderId, this); // shton vetveten te orders
        for (CartItem ci : cart.getItems()) {
            ci.getProduct().reduceStock(ci.getQuantity());
            order.addItem(new OrderItem(ci.getProduct(), ci.getQuantity()));
        }

        cart.clear();
        return order;
    }

    public List<Order> getOrders() {
        return Collections.unmodifiableList(orders);
    }

    public int getNumberOfOrders() {
        return orders.size();
    }

    public double getTotalSpent() {
        return orders.stream().mapToDouble(Order::getTotal).sum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer other = (Customer) o;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Customer{id=" + id + ", name='" + name + "', email='" + email
                + "', address='" + address + "'}";
    }
}