package am.itspace.sweetbakerystore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Whishlist {
    @GetMapping(value = "/wishlist")
    public String whishlist() {
        return "web/wishlist/index";
    }

    @GetMapping(value = "/review")
    public String review() {
        return "web/wishlist/index-1";
    }
}
