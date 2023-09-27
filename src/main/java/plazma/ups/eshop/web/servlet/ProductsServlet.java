package plazma.ups.eshop.web.servlet;

import plazma.ups.eshop.entity.Product;
import plazma.ups.eshop.service.ProductService;
import plazma.ups.eshop.service.ServiceLocator;
import plazma.ups.eshop.web.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductsServlet extends HttpServlet {

    private PageGenerator pageGenerator = PageGenerator.instance();
    private ProductService productService = ServiceLocator.getService(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        List<Product> products = productService.findAll();

        Map<String, Object> parametersMap = new HashMap<>();
        parametersMap.put("products", products);

        String page = pageGenerator.getPage("products.html", parametersMap);
        res.getWriter().println(page);
    }

}