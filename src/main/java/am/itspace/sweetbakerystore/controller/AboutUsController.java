package am.itspace.sweetbakerystore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutUsController {

    @GetMapping(value = "/about_us")
    public String aboutUs() {

        return "web/about-us/index";
    }
}