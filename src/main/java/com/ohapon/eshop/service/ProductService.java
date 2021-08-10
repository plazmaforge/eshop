package com.ohapon.eshop.service;

import com.ohapon.eshop.dao.ProductDao;
import com.ohapon.eshop.dao.jdbc.JdbcProductDao;
import com.ohapon.eshop.entity.Product;
import java.util.List;

public class ProductService {

    private ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> findAll() {
        return getProductDao().findAll();
    }

    public void add(Product product) {
        getProductDao().add(product);
    }

    public void update(Product product) {
        getProductDao().update(product);
    }

    public void remove(Long productId) {
        getProductDao().remove(productId);
    }

    private ProductDao getProductDao() {
        return productDao;
    }

}
