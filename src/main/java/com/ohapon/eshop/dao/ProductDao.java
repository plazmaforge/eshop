package com.ohapon.eshop.dao;

import com.ohapon.eshop.entity.Product;
import java.util.List;

public interface ProductDao {

    List<Product> findAll();

    Product findById(Long productId);

    void add(Product product);

    void update(Product product);

    void remove(Long productId);

}
