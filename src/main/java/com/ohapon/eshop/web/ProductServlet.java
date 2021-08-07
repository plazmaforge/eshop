package com.ohapon.eshop.web;

import com.ohapon.eshop.entity.Product;
import com.ohapon.eshop.service.ProductService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductServlet extends HttpServlet {

    private PageGenerator pageGenerator = PageGenerator.instance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html;charset=utf-8");

        ProductService service = new ProductService();
        List<Product> products = service.findAll();

        Map<String, Object> parametersMap = new HashMap<>();
        parametersMap.put("products", products);

        String page = pageGenerator.getPage("products.html", parametersMap);
        res.getWriter().println(page);

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse res) throws IOException {
        // TODO
        res.getWriter().println("PUT: Product");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException {
        // TODO
        res.getWriter().println("DELETE: Product");
    }

}