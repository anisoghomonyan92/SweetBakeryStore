package am.itspace.sweetbakerystore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyAccount {
    @GetMapping(value = "/my_account")
    public String myAccount() {

        return "web/my-account/index";
    }

    @GetMapping(value = "/lost_password")
    public String lostPassword() {

        return "web/my-account/lost-password/index";
    }
}
