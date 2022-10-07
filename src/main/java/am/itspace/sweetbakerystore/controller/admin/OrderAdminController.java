package am.itspace.sweetbakerystore.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderAdminController {

    @GetMapping(value = "/orders")
    public String orderPage() {
        return "admin/orders";
    }

    @GetMapping(value = "/order-details")
    public String orderDetailsPage() {
        return "admin/order-details";
    }
}
