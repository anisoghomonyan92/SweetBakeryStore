package am.itspace.sweetbakerystore.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePage3Controller {
    @GetMapping(value = "/homePage-3")
    public String page3() {
        return "web/home-page-3/index";
    }
}
