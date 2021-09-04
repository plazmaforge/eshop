package com.ohapon.eshop.dao;

import com.ohapon.eshop.entity.Product;
import java.util.List;

public interface ProductDao {

    List<Product> findAll();

    Product findById(Long id);

    List<Product> findByText(String text);

    void add(Product product);

    void update(Product product);

    void remove(Long id);

}
