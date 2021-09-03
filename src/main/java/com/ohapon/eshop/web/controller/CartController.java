package com.ohapon.eshop.web.controller;

import com.ohapon.eshop.entity.Cart;
import com.ohapon.eshop.entity.Product;
import com.ohapon.eshop.entity.Session;
import com.ohapon.eshop.service.ProductService;
import com.ohapon.eshop.service.SecurityService;
import com.ohapon.eshop.web.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CartController {

    public static final String MESSAGE_SESSION_EXPIRED = "Session expired";

    @Autowired
    private SecurityService securityService;

    @Autowired
    private ProductService productService;


    @RequestMapping(method = RequestMethod.GET, path = "cart")
    public String cart(HttpServletRequest request,
                             Model model) {

        Session session = getSession(request);
        if (session == null) {
            model.addAttribute("message", MESSAGE_SESSION_EXPIRED);
            return "message";
        }
        Cart cart = session.getCart();

        model.addAttribute("cart", cart);
        return "cart";
    }

    @RequestMapping(method = RequestMethod.POST, path = "cart/add")
    public String cartAdd(@RequestParam String id,
                             Model model,
                             HttpServletRequest request) {

        Session session = getSession(request);
        if (session == null) {
            model.addAttribute("message", MESSAGE_SESSION_EXPIRED);
            return "message";
        }
        Cart cart = session.getCart();

        long idVal = Long.valueOf(id);
        Product product = productService.findById(idVal);
        cart.addItem(product, 1f);

        return "redirect:/cart";
    }

    @RequestMapping(method = RequestMethod.POST, path = "cart/remove")
    public String cartRemove(@RequestParam String id,
                             Model model,
                             HttpServletRequest request) {

        Session session = getSession(request);
        if (session == null) {
            model.addAttribute("message", MESSAGE_SESSION_EXPIRED);
            return "message";
        }
        Cart cart = session.getCart();

        long idVal = Long.valueOf(id);
        cart.removeItem(idVal);

        return "redirect:/cart";
    }

    private Session getSession(HttpServletRequest request) {
        String token = WebUtils.getToken(request);
        Session session = securityService.getSession(token);
        return session;
    }

}
