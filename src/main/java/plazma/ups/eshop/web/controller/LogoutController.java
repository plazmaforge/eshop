package plazma.ups.eshop.web.controller;

import plazma.ups.eshop.service.SecurityService;
import plazma.ups.eshop.web.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LogoutController {

    public static final String MESSAGE_USER_NOT_LOGIN = "User is not login";

    @Autowired
    private SecurityService securityService;


    @RequestMapping(method = RequestMethod.GET, path = "logout")
    public String logout(Model model,
                        HttpServletRequest request,
                        HttpServletResponse response) {

        String token = WebUtils.getToken(request);
        if (securityService.existsToken(token)) {
            securityService.removeSession(token);
            WebUtils.removeToken(response);
            return "redirect:/products";
        }

        model.addAttribute("message", MESSAGE_USER_NOT_LOGIN);
        return "message";
    }

}
