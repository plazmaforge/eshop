package com.ohapon.eshop.web.controller;

import com.ohapon.eshop.service.ProductService;
import com.ohapon.eshop.service.ServiceLocator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    private ProductService productService = ServiceLocator.getService(ProductService.class);

    @RequestMapping(method = RequestMethod.GET, path = {"/products","/"})
    public String findAll(Model model) {
        model.addAttribute("products", productService.findAll());
        return "products";
    }

    @RequestMapping(method = RequestMethod.GET, path = "search")
    public String search() {
        return "product_search";
    }

    @RequestMapping(method = RequestMethod.POST, path = "search")
    public String search(@RequestParam String text, Model model) {
        model.addAttribute("products", productService.findByText(text));
        return "products";
    }

}
