package com.ohapon.eshop.service;

import com.ohapon.eshop.dao.ProductDao;
import com.ohapon.eshop.entity.Product;
import java.util.List;

public class ProductService {

    private ProductDao productDao;

    public ProductService() {
    }

    public List<Product> findAll() {
        return productDao.findAll();
    }

    public List<Product> findByText(String text) {
        return productDao.findByText(text);
    }

    public Product findById(Long productId) {
        return productDao.findById(productId);
    }

    public void add(Product product) {
        productDao.add(product);
    }

    public void update(Product product) {
        productDao.update(product);
    }

    public void remove(Long productId) {
        productDao.remove(productId);
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

}
