package am.itspace.sweetbakerystore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Shop {
    @GetMapping(value = "/shop")
    public String shop() {

        return "web/shop/index";
    }



}
