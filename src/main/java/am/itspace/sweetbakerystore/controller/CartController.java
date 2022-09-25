package am.itspace.sweetbakerystore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {
    @GetMapping(value = "/cart/index.htm")
    public String cart() {
        return "/cart/index.htm";
    }
}
