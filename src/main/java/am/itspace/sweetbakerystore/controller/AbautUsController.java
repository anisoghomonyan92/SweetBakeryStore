package am.itspace.sweetbakerystore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AbautUsController {
    @GetMapping(value = "/aboutUs/index.htm")
    public String aboutUs() {
        return "/aboutUs/index.htm";
    }
}
