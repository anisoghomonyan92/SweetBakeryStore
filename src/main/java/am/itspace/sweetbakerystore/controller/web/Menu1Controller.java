package am.itspace.sweetbakerystore.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Menu1Controller {
    @GetMapping(value = "/menu-1")
    public String menu1() {

        return "web/menu-1/index";
    }
}
