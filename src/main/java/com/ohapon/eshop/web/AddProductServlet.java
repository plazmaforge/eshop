package com.ohapon.eshop.web;

import com.ohapon.eshop.entity.Product;
import com.ohapon.eshop.service.ProductService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AddProductServlet extends HttpServlet {

    private PageGenerator pageGenerator = PageGenerator.instance();
    private ProductService productService;

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

        Product product = new Product(name, price, new Date());
        productService.add(product);

        res.sendRedirect("/products");

        //Map<String, Object> parametersMap = new HashMap<>();
        //parametersMap.put("message", "New Product '" + product.getName() + "' was added");

        //String page = pageGenerator.getPage("message.html", parametersMap);
        //res.getWriter().println(page);

    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

}
