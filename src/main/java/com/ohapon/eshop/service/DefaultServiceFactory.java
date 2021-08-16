package com.ohapon.eshop.service;

import com.ohapon.eshop.dao.ProductDao;
import com.ohapon.eshop.dao.jdbc.ConnectionFactory;
import com.ohapon.eshop.dao.jdbc.JdbcProductDao;

public class DefaultServiceFactory implements ServiceFactory {

    private ConnectionFactory connectionFactory;

    public DefaultServiceFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public ProductService getProductService() {
        ProductDao productDao = new JdbcProductDao(connectionFactory);
        ProductService productService = new ProductService(productDao);
        return productService;
    }

}
