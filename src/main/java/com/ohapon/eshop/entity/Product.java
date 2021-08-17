package com.ohapon.eshop.entity;

import java.util.Date;

public class Product {

    private long id;
    private String name;
    private double price;
    private Date date;

    public Product(String name, double price, Date date) {
        this.name = name;
        this.price = price;
        this.date = date;
    }

    public Product(long id, String name, double price, Date date) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
