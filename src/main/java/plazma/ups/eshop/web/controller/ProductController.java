package plazma.ups.eshop.web.controller;

import plazma.ups.eshop.entity.Product;
import plazma.ups.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET, path = {"/products","/"})
    public String productList(Model model) {
        model.addAttribute("products", productService.findAll());
        return "product_list";
    }

    @RequestMapping(method = RequestMethod.GET, path = "search")
    public String productSearch() {
        return "product_search";
    }

    @RequestMapping(method = RequestMethod.POST, path = "search")
    public String productSearch(@RequestParam String text, Model model) {
        model.addAttribute("products", productService.findByText(text));
        return "product_list";
    }

    @RequestMapping(method = RequestMethod.GET, path = "product/add")
    public String productAdd() {
        return "product_add";
    }

    @RequestMapping(method = RequestMethod.POST, path = "product/add")
    public String productAdd(@RequestParam String name,
                             @RequestParam String description,
                             @RequestParam String price,
                             Model model) {

        double priceVal = Double.parseDouble(price);

        Product product = new Product(name, description, priceVal);
        productService.add(product);

        return "redirect:/products";
    }

    @RequestMapping(method = RequestMethod.POST, path = "product/remove")
    public String productRemove(@RequestParam String id,
                             Model model) {

        long idVal = Long.valueOf(id);

        productService.remove(idVal);

        return "redirect:/products";
    }

    @RequestMapping(method = RequestMethod.GET, path = "product/edit")
    public String productEdit(@RequestParam String id,
                                Model model) {

        long idVal = Long.valueOf(id);

        Product product = productService.findById(idVal);

        model.addAttribute("product", product);
        return "product_edit";
    }

    @RequestMapping(method = RequestMethod.POST, path = "product/edit")
    public String productEdit(@RequestParam String id,
                              @RequestParam String name,
                             @RequestParam String description,
                             @RequestParam String price,
                             Model model) {

        long idVal = Long.valueOf(id);
        double priceVal = Double.parseDouble(price);

        Product product = new Product(idVal, name, description, priceVal);
        productService.update(product);

        return "redirect:/products";
    }

}