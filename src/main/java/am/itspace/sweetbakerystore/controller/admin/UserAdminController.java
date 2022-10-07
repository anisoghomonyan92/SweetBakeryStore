package am.itspace.sweetbakerystore.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserAdminController {
    @GetMapping(value = "/users")
    public String usersPage() {
        return "admin/users";
    }

    @GetMapping(value = "/users/change-role")
    public String userChangeRolePage() {
        return "admin/users";
    }

    @PostMapping(value = "/users/change-role")
    public String userChangeRole() {
        return "admin/users";
    }
}
