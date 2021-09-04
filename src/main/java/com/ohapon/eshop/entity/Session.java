package com.ohapon.eshop.entity;

public class Session {

    private String token;

    private Cart cart;

    public Session(String token) {
        this.token = token;
    }

    public Cart getCart() {
        if (cart == null) {
            cart = new Cart();
        }
        return cart;
    }

    public String getToken() {
        return token;
    }
}
