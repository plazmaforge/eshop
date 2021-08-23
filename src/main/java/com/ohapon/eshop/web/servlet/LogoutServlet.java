package com.ohapon.eshop.web.servlet;

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

public class LogoutServlet extends HttpServlet {

    private PageGenerator pageGenerator = PageGenerator.instance();
    private SecurityService securityService = ServiceLocator.getService(SecurityService.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String token = WebUtils.getToken(request);
        if (securityService.removeToken(token)) {
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
