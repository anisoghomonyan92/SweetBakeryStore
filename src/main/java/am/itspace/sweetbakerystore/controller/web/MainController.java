package am.itspace.sweetbakerystore.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(value = "/")
    public String mainPage() {
        return "web/index";
    }
}
