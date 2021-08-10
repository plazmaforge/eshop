package com.ohapon.eshop;

import com.ohapon.eshop.dao.jdbc.ConnectionFactory;
import com.ohapon.eshop.db.DBInitializer;
import com.ohapon.eshop.service.ServiceFactory;
import com.ohapon.eshop.service.ServiceFactoryImpl;
import com.ohapon.eshop.web.AddProductServlet;
import com.ohapon.eshop.web.EditProductServlet;
import com.ohapon.eshop.web.ProductsServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.util.Properties;

public class Main {

    public static void main(String[] args) throws Exception {

        PropertiesLoader propertiesLoader = new PropertiesLoader();
        Properties properties = propertiesLoader.load("application.properties");

        ConnectionFactory connectionFactory = new ConnectionFactory(properties);
        connectionFactory.init();

        ServiceFactory serviceFactory = new ServiceFactoryImpl(connectionFactory);

        DBInitializer dbInitializer = new DBInitializer(connectionFactory);
        dbInitializer.init();

        ProductsServlet productsServlet = new ProductsServlet();
        productsServlet.setServiceFactory(serviceFactory);

        AddProductServlet addProductServlet = new AddProductServlet();
        addProductServlet.setServiceFactory(serviceFactory);

        EditProductServlet editProductServlet = new EditProductServlet();
        editProductServlet.setServiceFactory(serviceFactory);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(productsServlet), "/");
        context.addServlet(new ServletHolder(addProductServlet), "/product/add");
        context.addServlet(new ServletHolder(editProductServlet), "/product/edit");

        Server server = new Server(8080);
        server.setHandler(context);
        server.start();

    }
}