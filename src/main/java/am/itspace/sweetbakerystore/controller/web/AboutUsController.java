package am.itspace.sweetbakerystore.controller.web;

import am.itspace.sweetbakerystore.entity.User;
import am.itspace.sweetbakerystore.service.AboutUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class AboutUsController {
    private final AboutUserService aboutUsService;

    @GetMapping(value = "/about-us")
    public String aboutUs() {
        return "web/about-us/index";
    }

    @GetMapping(value = "/contact-us")
    public String contactUs() {
        return "web/about-us/index";
    }

    @PostMapping(value = "/contact-us")
    public String submitContact(@ModelAttribute User user,
                                @RequestParam String message) {
        aboutUsService.sendMail(user, message);
        return "web/about-us/contact";
    }
}
