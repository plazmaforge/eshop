package com.ohapon.eshop.web;

import com.ohapon.eshop.entity.Product;
import com.ohapon.eshop.service.ProductService;
import com.ohapon.eshop.service.ServiceFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EditProductServlet extends HttpServlet {

    private PageGenerator pageGenerator = PageGenerator.instance();
    private ServiceFactory serviceFactory;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

        long id = Long.valueOf(req.getParameter("id"));

        ProductService productService = serviceFactory.getProductService();
        Product product = productService.findById(id);

        Map<String, Object> parametersMap = new HashMap<>();
        parametersMap.put("product", product);

        String page = pageGenerator.getPage("editProduct.html", parametersMap);
        res.getWriter().println(page);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

        long id = Long.valueOf(req.getParameter("id"));
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));

        Product product = new Product(id, name, price, new Date());

        ProductService productService = serviceFactory.getProductService();
        productService.update(product);

        Map<String, Object> parametersMap = new HashMap<>();
        parametersMap.put("message", "Product '" + product.getName() + "' was updated");

        String page = pageGenerator.getPage("message.html", parametersMap);
        res.getWriter().println(page);

    }

    public void setServiceFactory(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }
}
