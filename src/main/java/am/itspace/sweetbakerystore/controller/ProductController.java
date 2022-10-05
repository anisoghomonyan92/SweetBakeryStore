package am.itspace.sweetbakerystore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {
    @GetMapping(value = "/product/category")
    public String product() {

        return "web/product-category/index";
    }

    @GetMapping(value = "/product/cherry")
    public String productCherry() {

        return "web/product-category/cherry-coke/index";
    }


}
