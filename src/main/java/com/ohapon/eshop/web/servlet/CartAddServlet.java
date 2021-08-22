package com.ohapon.eshop.web.servlet;

import com.ohapon.eshop.entity.Cart;
import com.ohapon.eshop.entity.Product;
import com.ohapon.eshop.service.ProductService;
import com.ohapon.eshop.web.PageGenerator;
import com.ohapon.eshop.web.utils.WebUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CartAddServlet extends HttpServlet {

    private PageGenerator pageGenerator = PageGenerator.instance();
    private ProductService productService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long id = Long.valueOf(request.getParameter("id"));

        Cart cart = WebUtils.getCart(request);
        Product product = productService.findById(id);
        cart.addItem(product, 1f);

        response.sendRedirect("/cart");
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

}
