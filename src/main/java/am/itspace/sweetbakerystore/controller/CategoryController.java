package am.itspace.sweetbakerystore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryController {
    @GetMapping(value = "/category/the_rolling_pin")
    public String productRollingPin() {
        return "web/category/the_rolling_pin/index";
    }
}
