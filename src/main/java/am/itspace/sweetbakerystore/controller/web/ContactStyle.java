package am.itspace.sweetbakerystore.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactStyle {

    @GetMapping(value = "/contact-style")
    public String contactStyle() {
        return "web/contact-style/index";
    }
}

