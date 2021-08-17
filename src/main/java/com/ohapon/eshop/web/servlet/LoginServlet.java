package com.ohapon.eshop.web.servlet;

import com.ohapon.eshop.service.SecurityService;
import com.ohapon.eshop.web.PageGenerator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginServlet extends HttpServlet {

    private PageGenerator pageGenerator = PageGenerator.instance();
    private SecurityService securityService;

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
            res.addCookie(new Cookie("user-token", token));
            res.sendRedirect("/products");
            return;
        }

        Map<String, Object> parametersMap = new HashMap<>();
        parametersMap.put("message", "Invalid user name or password");

        String page = pageGenerator.getPage("message.html", parametersMap);
        res.getWriter().println(page);

    }

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

}
