package com.ohapon.eshop.service;

import com.ohapon.eshop.PropertiesLoader;
import com.ohapon.eshop.dao.jdbc.ConnectionFactory;
import com.ohapon.eshop.dao.jdbc.JdbcProductDao;
import com.ohapon.eshop.dao.jdbc.JdbcUserDao;
import com.ohapon.eshop.db.DBInitializer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ServiceLocator {

    private static final Map<Class<?>, Object> SERVICES = new HashMap<>();

    static {

        PropertiesLoader propertiesLoader = new PropertiesLoader();
        Properties properties = propertiesLoader.load("application.properties");

        ConnectionFactory connectionFactory = new ConnectionFactory(properties);

        //ServiceFactory serviceFactory = new DefaultServiceFactory(connectionFactory);

        DBInitializer dbInitializer = new DBInitializer(connectionFactory);
        dbInitializer.init();

        ProductService productService = new ProductService(new JdbcProductDao(connectionFactory));
        addService(ProductService.class, productService);

        UserService userService = new UserService(new JdbcUserDao(connectionFactory));
        addService(UserService.class, userService);

        SecurityService securityService = new SecurityService(userService);
        addService(SecurityService.class, securityService);


        ////

        /*
        SettingsLoader settingsLoader = new SettingsLoader("config.properties");

        PGSimpleDataSource pgSimpleDataSource = new PGSimpleDataSource();
        pgSimpleDataSource.setUrl(settingsLoader.getUrl());
        pgSimpleDataSource.setPassword(settingsLoader.getPassword());
        pgSimpleDataSource.setUser(settingsLoader.getUser());

        // DAO
        JdbcProductDao jdbcProductDao = new JdbcProductDao(pgSimpleDataSource);

        ProductService productService = new ProductService();
        productService.setProductDao(jdbcProductDao);

        addService(ProductService.class, productService);
        */
    }

    public static <T> T getService(Class<T> serviceType) {
        return serviceType.cast(SERVICES.get(serviceType));
    }

    public static void addService(Class<?> serviceName, Object service) {
        SERVICES.put(serviceName, service);
    }

}
