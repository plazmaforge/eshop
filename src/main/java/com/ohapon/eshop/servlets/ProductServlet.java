package com.ohapon.eshop.servlets;

import com.ohapon.eshop.entities.Product;
import com.ohapon.eshop.services.ProductService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProductServlet extends HttpServlet {

    public ProductServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html;charset=utf-8");

        // TODO
        ProductService service = new ProductService();
        List<Product> products = service.findAll();
        for (Product product: products) {
            res.getWriter().println("" + product.getId() + " " + product.getName() + "</br>");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.getWriter().println("PUT: Product");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.getWriter().println("DELETE: Product");
    }

}