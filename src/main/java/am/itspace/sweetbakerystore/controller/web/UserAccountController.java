package am.itspace.sweetbakerystore.controller.web;

import am.itspace.sweetbakerystore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserAccountController {
    private final UserService userService;

    @GetMapping(value = "/my-account")
    public String userAccount() {
        return "web/user-account/index";
    }

    @GetMapping(value = "/my-account-edit")
    public String userEditAccountPage() {
        return "web/user-account/edit-account";
    }

    @PostMapping(value = "/my-account-edit")
    public String userEditAccount() {
        return "redirect:/user/my-account";
    }

    @GetMapping(value = "/my-orders")
    public String userOrdersPage() {
        return "web/user-account/my-orders";
    }

    @GetMapping(value = "/edit-address")
    public String userAddressPage() {
        return "web/user-account/edit-address";
    }

    @PostMapping(value = "/edit-address")
    public String userEditAddress() {
        return "redirect:/user/my-account";
    }

}
