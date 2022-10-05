package am.itspace.sweetbakerystore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Typography {
    @GetMapping(value = "/typography")
    public String compare() {
        return "web/typography/index";
    }
}
