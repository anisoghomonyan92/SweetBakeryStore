package am.itspace.sweetbakerystore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactStyle {

    @GetMapping(value = "/contact_style")
    public String contactStyle() {
        return "web/contact-style/index";
    }
}

