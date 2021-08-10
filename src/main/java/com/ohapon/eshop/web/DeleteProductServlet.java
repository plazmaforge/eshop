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

public class DeleteProductServlet extends HttpServlet {

    private PageGenerator pageGenerator = PageGenerator.instance();
    private ServiceFactory serviceFactory;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

        long id = Long.valueOf(req.getParameter("id"));

        ProductService productService = serviceFactory.getProductService();
        productService.remove(id);

        Map<String, Object> parametersMap = new HashMap<>();
        parametersMap.put("message", "Product '" + id + "' was removed");

        String page = pageGenerator.getPage("message.html", parametersMap);
        res.getWriter().println(page);

    }

    public void setServiceFactory(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }
}
