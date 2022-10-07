package am.itspace.sweetbakerystore.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderDetailsController {
    @GetMapping(value = "/oder-details")
    public String checkout() {
        return "web/order-details/index";
    }
}
