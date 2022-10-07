package am.itspace.sweetbakerystore.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddressController {
    @GetMapping(value = "/addresses")
    public String orderPage() {
        return "admin/addresses";
    }
}
