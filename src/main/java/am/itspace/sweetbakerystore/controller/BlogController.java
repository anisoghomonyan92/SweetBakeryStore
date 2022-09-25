package am.itspace.sweetbakerystore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogController {
    @GetMapping(value = "/blog/index.htm")
    public String blog() {
        return "/blog/index.htm";
    }
}
