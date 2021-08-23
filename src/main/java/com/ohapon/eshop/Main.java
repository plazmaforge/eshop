package com.ohapon.eshop;

import com.ohapon.eshop.dao.jdbc.ConnectionFactory;
import com.ohapon.eshop.db.DBInitializer;
import com.ohapon.eshop.service.ProductService;
import com.ohapon.eshop.service.SecurityService;
import com.ohapon.eshop.service.ServiceFactory;
import com.ohapon.eshop.service.DefaultServiceFactory;
import com.ohapon.eshop.web.filter.SecurityFilter;
import com.ohapon.eshop.web.servlet.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.DispatcherType;
import java.util.EnumSet;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws Exception {

        /*
        PropertiesLoader propertiesLoader = new PropertiesLoader();
        Properties properties = propertiesLoader.load("application.properties");

        ConnectionFactory connectionFactory = new ConnectionFactory(properties);

        ServiceFactory serviceFactory = new DefaultServiceFactory(connectionFactory);

        DBInitializer dbInitializer = new DBInitializer(connectionFactory);
        dbInitializer.init();

        ProductService productService = serviceFactory.getProductService();
        SecurityService securityService = serviceFactory.getSecurityService();

        ProductsServlet productsServlet = new ProductsServlet();
        productsServlet.setProductService(serviceFactory.getProductService());

        ProductAddServlet productAddServlet = new ProductAddServlet();
        productAddServlet.setProductService(serviceFactory.getProductService());

        ProductEditServlet productEditServlet = new ProductEditServlet();
        productEditServlet.setProductService(serviceFactory.getProductService());

        ProductRemoveServlet productRemoveServlet = new ProductRemoveServlet();
        productRemoveServlet.setProductService(serviceFactory.getProductService());

        ProductSearchServlet productSearchServlet = new ProductSearchServlet();
        productSearchServlet.setProductService(serviceFactory.getProductService());

        // Login
        LoginServlet loginServlet = new LoginServlet();
        loginServlet.setSecurityService(serviceFactory.getSecurityService());

        // Logout
        LogoutServlet logoutServlet = new LogoutServlet();
        logoutServlet.setSecurityService(serviceFactory.getSecurityService());

        // Cart
        CartServlet cartServlet = new CartServlet();

        CartAddServlet cartAddServlet = new CartAddServlet();
        cartAddServlet.setProductService(serviceFactory.getProductService());

        CartRemoveServlet cartRemoveServlet = new CartRemoveServlet();
        cartRemoveServlet.setProductService(serviceFactory.getProductService());

        SecurityFilter securityFilter = new SecurityFilter(securityService);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(productsServlet), "/");
        context.addServlet(new ServletHolder(productAddServlet), "/product/add");
        context.addServlet(new ServletHolder(productEditServlet), "/product/edit");
        context.addServlet(new ServletHolder(productRemoveServlet), "/product/remove");

        context.addServlet(new ServletHolder(productSearchServlet), "/search");

        context.addServlet(new ServletHolder(loginServlet), "/login");
        context.addServlet(new ServletHolder(logoutServlet), "/logout");

        context.addServlet(new ServletHolder(cartServlet), "/cart");
        context.addServlet(new ServletHolder(cartAddServlet), "/cart/add");
        context.addServlet(new ServletHolder(cartRemoveServlet), "/cart/remove");

        context.addFilter(new FilterHolder(securityFilter), "/product/*", EnumSet.of(DispatcherType.REQUEST));

        Server server = new Server(8080);
        server.setHandler(context);
        server.start();
        */

    }
}