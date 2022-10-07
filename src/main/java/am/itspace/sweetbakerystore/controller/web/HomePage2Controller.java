package am.itspace.sweetbakerystore.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePage2Controller {
    @GetMapping(value = "/home-page-2")
    public String page2() {
        return "web/home-page-2/index";
    }
}
