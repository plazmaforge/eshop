package plazma.ups.eshop.service;

import plazma.ups.eshop.PropertiesLoader;
import plazma.ups.eshop.dao.jdbc.DefaultDataSource;
import plazma.ups.eshop.dao.jdbc.JdbcProductDao;
import plazma.ups.eshop.dao.jdbc.JdbcUserDao;
import plazma.ups.eshop.db.DBInitializer;

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
