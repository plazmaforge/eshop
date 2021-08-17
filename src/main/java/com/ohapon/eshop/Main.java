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
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.DispatcherType;
import java.util.EnumSet;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws Exception {

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

        AddProductServlet addProductServlet = new AddProductServlet();
        addProductServlet.setProductService(serviceFactory.getProductService());

        EditProductServlet editProductServlet = new EditProductServlet();
        editProductServlet.setProductService(serviceFactory.getProductService());

        DeleteProductServlet deleteProductServlet = new DeleteProductServlet();
        deleteProductServlet.setProductService(serviceFactory.getProductService());

        LoginServlet loginServlet = new LoginServlet();
        loginServlet.setSecurityService(serviceFactory.getSecurityService());

        SecurityFilter securityFilter = new SecurityFilter(securityService);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(productsServlet), "/");
        context.addServlet(new ServletHolder(addProductServlet), "/product/add");
        context.addServlet(new ServletHolder(editProductServlet), "/product/edit");
        context.addServlet(new ServletHolder(deleteProductServlet), "/product/delete");
        context.addServlet(new ServletHolder(loginServlet), "/login");

        context.addFilter(new FilterHolder(securityFilter), "/product/*", EnumSet.of(DispatcherType.REQUEST));

        Server server = new Server(8080);
        server.setHandler(context);
        server.start();

    }
}