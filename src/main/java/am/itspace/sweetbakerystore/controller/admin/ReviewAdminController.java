package am.itspace.sweetbakerystore.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ReviewAdminController {
    @GetMapping(value = "/reviews")
    public String reviewPage() {
        return "admin/reviews";
    }

}
