package com.ohapon.eshop.service;

import com.ohapon.eshop.dao.ProductDao;
import com.ohapon.eshop.dao.jdbc.ConnectionFactory;
import com.ohapon.eshop.dao.jdbc.JdbcProductDao;

public class DefaultServiceFactory implements ServiceFactory {

    private ConnectionFactory connectionFactory;
    private ProductService productService;
    SecurityService securityService;

    public DefaultServiceFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public ProductService getProductService() {
        if (productService == null) {
            ProductDao productDao = new JdbcProductDao(connectionFactory);
            productService = new ProductService(productDao);
        }
        return productService;
    }

    @Override
    public SecurityService getSecurityService() {
        if (securityService == null) {
            securityService = new SecurityService();
        }
        return securityService;
    }

}
