package am.itspace.sweetbakerystore.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ProductAdminController {

    @GetMapping(value = "/products")
    public String productPage() {
        return "admin/products";
    }

    @GetMapping(value = "/products-add")
    public String addProductPage() {
        return "admin/products";
    }

    @PostMapping(value = "/products-add")
    public String addProduct() {
        return "redirect:/admin/products";
    }

    @PostMapping(value = "/products-edit")
    public String editProductPage() {
        return "redirect:/admin/products";
    }

}


