package com.ohapon.eshop.services;

import com.ohapon.eshop.entities.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductService {

    public List<Product> findAll() {
        // TODO
        List<Product> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            long id = i;
            String name = "Product " + i;
            double price = i * 100;
            Date date = new Date();
            list.add(new Product(id, name, price, date));
        }
        return list;
    }

    public void addProduct(Product product) {
        // TODO
    }

    public void updateProduct(Product product) {
        // TODO
    }

    public void removeProduct(Long productId) {
        // TODO
    }

}
