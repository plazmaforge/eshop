package com.ohapon.eshop.service;

import com.ohapon.eshop.PropertiesLoader;
import com.ohapon.eshop.dao.jdbc.DefaultDataSource;
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

        DefaultDataSource dataSource = new DefaultDataSource(properties);

        DBInitializer dbInitializer = new DBInitializer(dataSource);
        dbInitializer.init();

        ProductService productService = new ProductService(new JdbcProductDao(dataSource));
        addService(ProductService.class, productService);

        UserService userService = new UserService(new JdbcUserDao(dataSource));
        addService(UserService.class, userService);

        SecurityService securityService = new SecurityService(userService);
        addService(SecurityService.class, securityService);

    }

    public static <T> T getService(Class<T> serviceType) {
        return serviceType.cast(SERVICES.get(serviceType));
    }

    public static void addService(Class<?> serviceName, Object service) {
        SERVICES.put(serviceName, service);
    }

}
