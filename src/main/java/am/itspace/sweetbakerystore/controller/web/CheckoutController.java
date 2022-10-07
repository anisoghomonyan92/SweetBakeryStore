package am.itspace.sweetbakerystore.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CheckoutController {
    @GetMapping(value = "/checkout")
    public String checkout() {
        return "web/checkout/index";
    }
}
