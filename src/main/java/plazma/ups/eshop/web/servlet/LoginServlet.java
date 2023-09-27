package plazma.ups.eshop.web.servlet;

import plazma.ups.eshop.entity.Session;
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

public class LoginServlet extends HttpServlet {

    private PageGenerator pageGenerator = PageGenerator.instance();
    private SecurityService securityService = ServiceLocator.getService(SecurityService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

        Map<String, Object> parametersMap = new HashMap<>();

        String page = pageGenerator.getPage("login.html", parametersMap);
        res.getWriter().println(page);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (securityService.login(username, password)) {
            String token = securityService.generateToken();
            Session session = new Session();
            securityService.addSession(token, session);
            WebUtils.addToken(res, token);
            res.sendRedirect("/products");
            return;
        }

        Map<String, Object> parametersMap = new HashMap<>();
        parametersMap.put("message", "Invalid user name or password");

        String page = pageGenerator.getPage("message.html", parametersMap);
        res.getWriter().println(page);

    }

}
