package com.ohapon.eshop.web.controller;

import com.ohapon.eshop.entity.Session;
import com.ohapon.eshop.entity.User;
import com.ohapon.eshop.service.SecurityService;
import com.ohapon.eshop.web.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    public static final String MESSAGE_INVALID_LOGIN = "Invalid user name or password";

    @Autowired
    private SecurityService securityService;

    @RequestMapping(method = RequestMethod.GET, path = "login")
    public String login() {
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST, path = "login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model,
                        HttpServletResponse response) {

        User user = securityService.login(username, password);
        if (user != null) {
            Session session = securityService.addSession(user);
            WebUtils.addToken(response, session.getToken());
            return "redirect:/products";
        }

        model.addAttribute("message", MESSAGE_INVALID_LOGIN);
        return "message";
    }

}
