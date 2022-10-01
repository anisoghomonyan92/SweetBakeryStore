package am.itspace.sweetbakerystore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePage3Controller {
    @GetMapping(value = "/homePage3/index-5.htm")
    public String page3() {
        return "web/homePage3/index-5.htm";
    }
}
