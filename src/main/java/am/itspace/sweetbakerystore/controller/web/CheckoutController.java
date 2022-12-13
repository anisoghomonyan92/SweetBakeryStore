package am.itspace.sweetbakerystore.controller.web;

import am.itspace.sweetbakerystore.dto.CheckoutDto;
import am.itspace.sweetbakerystore.security.CurrentUser;
import am.itspace.sweetbakerystore.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class CheckoutController {
    private final PaymentService paymentService;


    @GetMapping(path = "/payment/card/add")
    public String linkCard(
            ModelMap map,
            @RequestParam(required = false) Integer productId,
            @RequestParam(required = false) Integer quantity
    ) {
        map.addAttribute("checkoutDto", new CheckoutDto());
        return "web/checkout/index";
    }

    @PostMapping(value = "/payment/card/add")
    public String linkCard(@Valid @ModelAttribute CheckoutDto checkoutDto, BindingResult result,
                           @AuthenticationPrincipal CurrentUser currentUser) {
        if (result.hasErrors()) {
            return "web/checkout/index";
        } else {
            paymentService.save(checkoutDto, currentUser.getUser());
            return "redirect:/user/order-details";
        }
    }
}
