package am.itspace.sweetbakerystore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePage2Controller {
    @GetMapping(value = "/homePage2/index-5.htm")
    public String page2() {
        return "/homePage2/index-5.htm";
    }
}
