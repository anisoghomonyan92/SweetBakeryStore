package am.itspace.sweetbakerystore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryController {
    @GetMapping(value = "/category/theRollingPin/index.htm")
    public String productRollingPin() {
        return "/category/theRollingPin/index.htm";
    }
}
