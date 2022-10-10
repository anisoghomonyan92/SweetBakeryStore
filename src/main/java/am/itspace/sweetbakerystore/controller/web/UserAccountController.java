package am.itspace.sweetbakerystore.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserAccountController {
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
        return "redirect: web/user-account";
    }

    @GetMapping(value = "/my-orders")
    public String userOrdersPage() {
        return "web/user-account/my-orders";
    }

    @GetMapping(value = "/my-account/edit-address")
    public String userAddressPage() {
        return "web/user-account/edit-address";
    }

    @PostMapping(value = "/my-account/edit-address")
    public String userEditAddress() {
        return "redirect: web/user-account/edit-address";
    }
}
