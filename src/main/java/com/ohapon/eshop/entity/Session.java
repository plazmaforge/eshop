package com.ohapon.eshop.entity;

public class Session {

    private String token;

    private User user;

    private Cart cart;

    public Session(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Cart getCart() {
        if (cart == null) {
            cart = new Cart();
        }
        return cart;
    }

}
