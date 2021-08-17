package com.ohapon.eshop.entity;

import java.time.LocalDateTime;

public class Product {

    private long id;
    private String name;
    private double price;
    private LocalDateTime date;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Product(long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Product(long id, String name, double price, LocalDateTime date) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
