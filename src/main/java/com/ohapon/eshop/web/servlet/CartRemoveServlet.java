package com.ohapon.eshop.web.servlet;

import com.ohapon.eshop.entity.Cart;
import com.ohapon.eshop.entity.Session;
import com.ohapon.eshop.service.ProductService;
import com.ohapon.eshop.service.SecurityService;
import com.ohapon.eshop.service.ServiceLocator;
import com.ohapon.eshop.web.PageGenerator;
import com.ohapon.eshop.web.utils.WebUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CartRemoveServlet extends HttpServlet {

    private PageGenerator pageGenerator = PageGenerator.instance();
    private SecurityService securityService = ServiceLocator.getService(SecurityService.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long id = Long.valueOf(request.getParameter("id"));

        String token = WebUtils.getToken(request);
        Session session = securityService.getSession(token);
        Cart cart = session.getCart();

        cart.removeItem(id);

        response.sendRedirect("/cart");
    }

}
