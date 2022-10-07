package am.itspace.sweetbakerystore.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaymentAdminController {

    @GetMapping(value = "/payments")
    public String orderPage() {
        return "admin/payments";
    }
}
