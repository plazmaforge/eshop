package plazma.ups.eshop.web.servlet;

import plazma.ups.eshop.entity.Cart;
import plazma.ups.eshop.entity.Session;
import plazma.ups.eshop.service.SecurityService;
import plazma.ups.eshop.service.ServiceLocator;
import plazma.ups.eshop.web.PageGenerator;
import plazma.ups.eshop.web.utils.WebUtils;

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
