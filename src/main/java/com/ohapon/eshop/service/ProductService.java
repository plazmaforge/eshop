package com.ohapon.eshop.service;

import com.ohapon.eshop.dao.ProductDao;
import com.ohapon.eshop.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> findAll() {
        return productDao.findAll();
    }

    public Product findById(Long idd) {
        return productDao.findById(idd);
    }

    public List<Product> findByText(String text) {
        return productDao.findByText(text);
    }

    public void add(Product product) {
        productDao.add(product);
    }

    public void update(Product product) {
        productDao.update(product);
    }

    public void remove(Long id) {
        productDao.remove(id);
    }

}
