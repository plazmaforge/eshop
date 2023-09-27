package plazma.ups.eshop.web.servlet;

import plazma.ups.eshop.service.ProductService;
import plazma.ups.eshop.service.ServiceLocator;
import plazma.ups.eshop.web.PageGenerator;

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
