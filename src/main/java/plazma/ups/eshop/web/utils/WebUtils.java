package plazma.ups.eshop.web.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtils {

    public static final String USER_TOKEN = "user-token";

    // Cookie token implementation
    public static String getToken(HttpServletRequest request) {
        return getCookie(request, USER_TOKEN);
    }

    public static String getCookie(HttpServletRequest request, String name) {
        if (name == null) {
            return null;
        }
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }

    public static void addToken(HttpServletResponse response, String token) {
        addCookie(response, USER_TOKEN, token);
    }

    public static void removeToken(HttpServletResponse response) {
        removeCookie(response, USER_TOKEN);
    }

    public static void addCookie(HttpServletResponse response, String name, String value) {
        response.addCookie(new Cookie(name, value));
    }

    public static void removeCookie(HttpServletResponse response, String name) {
        response.addCookie(new Cookie(name, null));
    }

}
