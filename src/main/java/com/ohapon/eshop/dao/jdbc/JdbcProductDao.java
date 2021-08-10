package com.ohapon.eshop.dao.jdbc;

import com.ohapon.eshop.dao.ProductDao;
import com.ohapon.eshop.entity.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JdbcProductDao implements ProductDao {

    @Override
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

    @Override
    public void add(Product product) {
        // TODO
    }

    @Override
    public void update(Product product) {
        // TODO
    }

    @Override
    public void remove(Long productId) {
        // TODO
    }

}
