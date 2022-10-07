package am.itspace.sweetbakerystore.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FavoriteProductController {
    @GetMapping(value = "/favorite-products")
    public String orderPage() {
        return "admin/favorite-products";
    }
}
