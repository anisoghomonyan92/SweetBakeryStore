package am.itspace.sweetbakerystore.controller.web;

import am.itspace.sweetbakerystore.entity.Order;
import am.itspace.sweetbakerystore.entity.Order;
import am.itspace.sweetbakerystore.security.CurrentUser;
import am.itspace.sweetbakerystore.service.AddressService;
import am.itspace.sweetbakerystore.service.OrderService;
import am.itspace.sweetbakerystore.service.PaymentService;
import am.itspace.sweetbakerystore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class OrderDetailsController {

    @GetMapping(value = "/order-details")
    public String orderForm(ModelMap map) {
        map.addAttribute("order", new Order());
        return "web/order-details/index";
    }

    @GetMapping(value = "/order/buy-now")
    public String createOrder() {
        return "web/checkout/index";
    }

}
