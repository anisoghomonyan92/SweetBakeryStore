package am.itspace.sweetbakerystore.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WhishlistController {
    @GetMapping(value = "/wishlist")
    public String whishlist() {
        return "web/wishlist/index";
    }

    @GetMapping(value = "/review")
    public String review() {
        return "web/wishlist/index-1";
    }

    @PostMapping(value = "/review/add")
    public String addReview() {
        return "web/wishlist/index-1";
    }

}
