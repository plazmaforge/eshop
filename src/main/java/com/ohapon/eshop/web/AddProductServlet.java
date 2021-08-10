package com.ohapon.eshop.web;

import com.ohapon.eshop.entity.Product;
import com.ohapon.eshop.service.ServiceFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AddProductServlet extends HttpServlet {

    private PageGenerator pageGenerator = PageGenerator.instance();
    private ServiceFactory serviceFactory;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

        Map<String, Object> parametersMap = new HashMap<>();

        String page = pageGenerator.getPage("addProduct.html", parametersMap);
        res.getWriter().println(page);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));

        // TODO: Use ProductService
        Product product = new Product(1, name, price, new Date());

        Map<String, Object> parametersMap = new HashMap<>();
        parametersMap.put("product", product);

        String page = pageGenerator.getPage("resultProduct.html", parametersMap);
        res.getWriter().println(page);

    }

    public void setServiceFactory(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }
}
