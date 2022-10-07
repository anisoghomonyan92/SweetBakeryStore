package am.itspace.sweetbakerystore.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReviewAdminController {
    @GetMapping(value = "/reviews")
    public String reviewPage() {
        return "admin/reviews";
    }

}
