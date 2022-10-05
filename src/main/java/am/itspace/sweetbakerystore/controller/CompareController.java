package am.itspace.sweetbakerystore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CompareController {
    @GetMapping(value = "/compare")
    public String compare() {
        return "web/compare/index";
    }
}
