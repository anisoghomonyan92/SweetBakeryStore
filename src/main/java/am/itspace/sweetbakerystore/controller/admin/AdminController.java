package am.itspace.sweetbakerystore.controller.admin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping(value = "/admin-page")
    public String adminPage() {
        return "admin/home";
    }
}
