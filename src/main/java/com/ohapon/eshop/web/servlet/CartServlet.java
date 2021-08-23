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
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends HttpServlet {

    private PageGenerator pageGenerator = PageGenerator.instance();
    private SecurityService securityService = ServiceLocator.getService(SecurityService.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String token = WebUtils.getToken(request);
        Session session = securityService.getSession(token);
        Cart cart = session.getCart();

        Map<String, Object> parametersMap = new HashMap<>();
        parametersMap.put("cart", cart);

        String page = pageGenerator.getPage("cart.html", parametersMap);
        response.getWriter().println(page);
    }

}