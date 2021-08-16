package com.ohapon.eshop.web;

import com.ohapon.eshop.service.ProductService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DeleteProductServlet extends HttpServlet {

    private PageGenerator pageGenerator = PageGenerator.instance();
    private ProductService productService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

        long id = Long.valueOf(req.getParameter("id"));

        productService.remove(id);

        res.sendRedirect("/products");

        //Map<String, Object> parametersMap = new HashMap<>();
        //parametersMap.put("message", "Product '" + id + "' was removed");

        //String page = pageGenerator.getPage("message.html", parametersMap);
        //res.getWriter().println(page);

    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
