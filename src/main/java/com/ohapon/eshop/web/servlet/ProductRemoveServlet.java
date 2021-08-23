package com.ohapon.eshop.web.servlet;

import com.ohapon.eshop.service.ProductService;
import com.ohapon.eshop.service.ServiceLocator;
import com.ohapon.eshop.web.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductRemoveServlet extends HttpServlet {

    private PageGenerator pageGenerator = PageGenerator.instance();
    private ProductService productService = ServiceLocator.getService(ProductService.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        long id = Long.valueOf(req.getParameter("id"));

        productService.remove(id);
        res.sendRedirect("/products");
    }

}
