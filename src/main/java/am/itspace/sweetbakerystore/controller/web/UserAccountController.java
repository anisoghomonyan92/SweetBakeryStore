package am.itspace.sweetbakerystore.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserAccountController {
    @GetMapping(value = "/user-account")
    public String userAccount() {
        return "web/user-account/index";
    }
}
