package plazma.ups.eshop.web.servlet;

import plazma.ups.eshop.service.SecurityService;
import plazma.ups.eshop.service.ServiceLocator;
import plazma.ups.eshop.web.PageGenerator;
import plazma.ups.eshop.web.utils.WebUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LogoutServlet extends HttpServlet {

    private PageGenerator pageGenerator = PageGenerator.instance();
    private SecurityService securityService = ServiceLocator.getService(SecurityService.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String token = WebUtils.getToken(request);
        if (securityService.existsToken(token)) {
            securityService.removeSession(token);
            WebUtils.removeToken(response);
            response.sendRedirect("/products");
            return;
        }

        Map<String, Object> parametersMap = new HashMap<>();
        parametersMap.put("message", "User is not login");

        String page = pageGenerator.getPage("message.html", parametersMap);
        response.getWriter().println(page);

    }

}
