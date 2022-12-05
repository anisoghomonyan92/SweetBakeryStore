package am.itspace.sweetbakerystore.controller.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor
public class OrderDetailsController {

    @GetMapping(value = "/oder-details")
    public String checkout() {
        return "web/order-details/index";
    }

}
