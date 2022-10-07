package am.itspace.sweetbakerystore.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Menu2Controller {
    @GetMapping(value = "/menu-2")
    public String menu2() {

        return "web/menu-2/index";
    }
}
