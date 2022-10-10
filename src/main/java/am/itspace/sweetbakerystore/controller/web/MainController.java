package am.itspace.sweetbakerystore.controller.web;

import am.itspace.sweetbakerystore.entity.Role;
import am.itspace.sweetbakerystore.entity.User;
import am.itspace.sweetbakerystore.security.CurrentUser;
import am.itspace.sweetbakerystore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public String mainPage() {
        return "web/index";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }

    @GetMapping("/login-success")
    public String loginSuccess(@AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null) {
            User user = currentUser.getUser();
            if (user.getRole() == Role.ADMIN || user.getRole() == Role.PARTNER) {
                return "redirect:/admin/admin-page";
            } else if (user.getRole() == Role.USER) {
                return "redirect:/";
            }
        }
        return "redirect:/";
    }

    @PostMapping("/register")
    public String addUser(@ModelAttribute User user,
                          @RequestParam("userImage") MultipartFile file, ModelMap modelMap) throws IOException {
        Optional<User> byEmail = userService.findByEmail(user.getEmail());
        if (byEmail.isPresent()) {
            modelMap.addAttribute("errorMessage", "Email Already in use");
            return "/login-page";
        } else {
            if (!file.isEmpty() && file.getSize() > 0) {
                if (file.getContentType() != null && !file.getContentType().contains("image")) {
                    modelMap.addAttribute("errorMessageFile", "Please choose only image");
                    return "/login-page";
                }
            }
            userService.saveUser(user, file);
            return "redirect:/my-account";
        }
    }
    @GetMapping("/login")
    public String loginPage() {
        return "login-page";
    }
}
