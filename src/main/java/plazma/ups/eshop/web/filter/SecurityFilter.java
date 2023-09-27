package plazma.ups.eshop.web.filter;

import plazma.ups.eshop.service.SecurityService;
import plazma.ups.eshop.service.ServiceLocator;
import plazma.ups.eshop.web.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityFilter implements Filter {

    private SecurityService securityService = ServiceLocator.getService(SecurityService.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String token = WebUtils.getToken(httpServletRequest);
        if (securityService.existsToken(token)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        httpServletResponse.sendRedirect("/login");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }


}
