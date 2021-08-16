package com.ohapon.eshop;

import com.ohapon.eshop.dao.jdbc.ConnectionFactory;
import com.ohapon.eshop.db.DBInitializer;
import com.ohapon.eshop.service.ServiceFactory;
import com.ohapon.eshop.service.DefaultServiceFactory;
import com.ohapon.eshop.web.AddProductServlet;
import com.ohapon.eshop.web.DeleteProductServlet;
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

        ServiceFactory serviceFactory = new DefaultServiceFactory(connectionFactory);

        DBInitializer dbInitializer = new DBInitializer(connectionFactory);
        dbInitializer.init();

        ProductsServlet productsServlet = new ProductsServlet();
        productsServlet.setProductService(serviceFactory.getProductService());

        AddProductServlet addProductServlet = new AddProductServlet();
        addProductServlet.setProductService(serviceFactory.getProductService());

        EditProductServlet editProductServlet = new EditProductServlet();
        editProductServlet.setProductService(serviceFactory.getProductService());

        DeleteProductServlet deleteProductServlet = new DeleteProductServlet();
        deleteProductServlet.setProductService(serviceFactory.getProductService());

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(productsServlet), "/");
        context.addServlet(new ServletHolder(addProductServlet), "/product/add");
        context.addServlet(new ServletHolder(editProductServlet), "/product/edit");
        context.addServlet(new ServletHolder(deleteProductServlet), "/product/delete");

        Server server = new Server(8080);
        server.setHandler(context);
        server.start();

    }
}