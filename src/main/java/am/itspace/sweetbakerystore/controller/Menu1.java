package am.itspace.sweetbakerystore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Menu1 {
    @GetMapping(value = "/menu_1")
    public String menu1() {

        return "web/menu-1/index";
    }
}
