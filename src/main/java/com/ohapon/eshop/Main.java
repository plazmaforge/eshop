package com.ohapon.eshop;

import com.ohapon.eshop.web.AddProductServlet;
import com.ohapon.eshop.web.ProductsServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {

    public static void main(String[] args) throws Exception {

        ProductsServlet productsServlet = new ProductsServlet();
        AddProductServlet addProductServlet = new AddProductServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(productsServlet), "/");
        context.addServlet(new ServletHolder(addProductServlet), "/product/add");

        Server server = new Server(8080);
        server.setHandler(context);
        server.start();

    }
}