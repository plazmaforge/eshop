package plazma.ups.eshop.web.security;

import plazma.ups.eshop.service.SecurityService;
import plazma.ups.eshop.web.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityInterceptor implements HandlerInterceptor {

    @Autowired
    private SecurityService securityService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        String token = WebUtils.getToken(request);
        if (securityService.existsToken(token)) {
            return true;
        }

        response.sendRedirect("/login");
        return false;
    }

}
