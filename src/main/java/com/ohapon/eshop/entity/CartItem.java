package com.ohapon.eshop.entity;

public class CartItem {

    private Product product;
    private float quantity;

    public CartItem() {
    }

    public CartItem(Product product, float quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

}
