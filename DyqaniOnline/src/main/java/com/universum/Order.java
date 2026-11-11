package com.universum.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {

    public enum Status { NEW, PAID, SHIPPED, DELIVERED, CANCELLED }

    private final int id;
    private final Customer customer;
    private final LocalDateTime orderDate;
    private final List<OrderItem> items = new ArrayList<>();
    private Status status;

    public Order(int id, Customer customer) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID duhet të jetë më e madhe se 0.");
        }
        if (customer == null) {
            throw new IllegalArgumentException("Porosia duhet të ketë klient.");
        }
        this.id = id;
        this.customer = customer;
        this.orderDate = LocalDateTime.now();
        this.status = Status.NEW;
        customer.addOrder(this);
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public Status getStatus() {
        return status;
    }

    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void addItem(OrderItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Artikulli nuk mund të jetë null.");
        }
        if (status != Status.NEW) {
            throw new IllegalStateException("Artikujt mund të shtohen vetëm në porosi të reja.");
        }
        items.add(item);
    }

    public void removeItem(OrderItem item) {
        if (status != Status.NEW) {
            throw new IllegalStateException("Artikujt mund të hiqen vetëm nga porositë e reja.");
        }
        items.remove(item);
    }

    public double getTotal() {
        return items.stream().mapToDouble(OrderItem::getSubtotal).sum();
    }

    public void pay() {
        if (items.isEmpty()) {
            throw new IllegalStateException("Porosia është bosh, nuk mund të paguhet.");
        }
        if (status != Status.NEW) {
            throw new IllegalStateException("Porosia nuk është në gjendje 'NEW'.");
        }
        status = Status.PAID;
    }

    public void ship() {
        if (status != Status.PAID) {
            throw new IllegalStateException("Porosia duhet të jetë e paguar para dërgimit.");
        }
        status = Status.SHIPPED;
    }

    public void deliver() {
        if (status != Status.SHIPPED) {
            throw new IllegalStateException("Porosia duhet të jetë e dërguar para dorëzimit.");
        }
        status = Status.DELIVERED;
    }

    public void cancel() {
        if (status == Status.SHIPPED || status == Status.DELIVERED) {
            throw new IllegalStateException("Porosia nuk mund të anulohet pasi është dërguar.");
        }
        if (status == Status.CANCELLED) {
            throw new IllegalStateException("Porosia është anuluar tashmë.");
        }
        status = Status.CANCELLED;
    }

    @Override
    public String toString() {
        return "Order{id=" + id + ", customer=" + customer.getName()
                + ", items=" + items.size() + ", total=" + getTotal()
                + ", status=" + status + "}";
    }
}