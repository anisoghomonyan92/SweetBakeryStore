package am.itspace.sweetbakerystore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CompareController {
    @GetMapping(value = "/compare/index.htm")
    public String compare() {
        return "/compare/index.htm";
    }
}
