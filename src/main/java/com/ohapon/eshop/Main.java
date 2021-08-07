package com.ohapon.eshop;

import com.ohapon.eshop.web.ProductServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {

    public static void main(String[] args) throws Exception {

        ProductServlet productServlet = new ProductServlet();

        // TODO
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(productServlet), "/");
        //context.addServlet(new ServletHolder(createServlet), "/create");

        Server server = new Server(8080);
        server.setHandler(context);
        server.start();

    }
}