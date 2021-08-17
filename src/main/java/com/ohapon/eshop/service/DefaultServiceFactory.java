package com.ohapon.eshop.service;

import com.ohapon.eshop.dao.jdbc.ConnectionFactory;
import com.ohapon.eshop.dao.jdbc.JdbcProductDao;
import com.ohapon.eshop.dao.jdbc.JdbcUserDao;

public class DefaultServiceFactory implements ServiceFactory {

    private ConnectionFactory connectionFactory;
    private ProductService productService;
    private UserService userService;
    private SecurityService securityService;

    public DefaultServiceFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public ProductService getProductService() {
        if (productService == null) {
            productService = new ProductService(new JdbcProductDao(connectionFactory));
        }
        return productService;
    }

    @Override
    public SecurityService getSecurityService() {
        if (securityService == null) {
            securityService = new SecurityService(getUserService());
        }
        return securityService;
    }

    @Override
    public UserService getUserService() {
        if (userService == null) {
            userService = new UserService(new JdbcUserDao(connectionFactory));
        }
        return userService;
    }

}
