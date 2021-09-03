package com.ohapon.eshop.web.controller;

import com.ohapon.eshop.entity.Session;
import com.ohapon.eshop.service.SecurityService;
import com.ohapon.eshop.web.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

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

        if (securityService.login(username, password)) {
            String token = securityService.generateToken();
            Session session = new Session();
            securityService.addSession(token, session);
            WebUtils.addToken(response, token);
            return "redirect:/products";
        }

        model.addAttribute("message", "Invalid user name or password");
        return "message";
    }

}
