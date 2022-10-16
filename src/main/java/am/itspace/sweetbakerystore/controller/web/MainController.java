package am.itspace.sweetbakerystore.controller.web;

import am.itspace.sweetbakerystore.entity.Address;
import am.itspace.sweetbakerystore.entity.City;
import am.itspace.sweetbakerystore.entity.Role;
import am.itspace.sweetbakerystore.entity.User;
import am.itspace.sweetbakerystore.security.CurrentUser;
import am.itspace.sweetbakerystore.service.AddressService;
import am.itspace.sweetbakerystore.service.CityService;
import am.itspace.sweetbakerystore.service.UserService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;
    private final AddressService addressService;
    private final CityService cityService;

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

    @GetMapping("/register")
    public String registerPage(ModelMap map) {
        map.addAttribute("cities", cityService.findAll());
        return "register";
    }

    //Registered user with address and cityId
    @PostMapping("/register")
    public String addUser(@ModelAttribute User user,
                          @RequestParam("userImage") MultipartFile file,
                          @RequestParam("addressName") String addressName,
                          @RequestParam("cityId") int cityId,
                          ModelMap modelMap) throws IOException {
        Optional<User> byEmail = userService.findByEmail(user.getEmail());
        City byID = cityService.findByID(cityId);
        Address address = new Address();
        address.setCity(byID);
        address.setName(addressName);
        Address savedAddress = addressService.saveAddress(address);
        user.setAddress(savedAddress);
        if (byEmail.isPresent()) {
            modelMap.addAttribute("errorMessage", "Email Already in use");
            return "/register";
        } else {
            if (!file.isEmpty() && file.getSize() > 0) {
                if (file.getContentType() != null && !file.getContentType().contains("image")) {
                    modelMap.addAttribute("errorMessageFile", "Please choose only image");
                    return "/login";
                }
            }
            userService.saveUser(user, file);
            return "redirect:/login";
        }
    }

    @GetMapping(value = "/admin/verify")
    public String verifyUser(@RequestParam("email") String email, @RequestParam("token") String token) throws Exception {
        userService.verifyUser(email, token);
        return "redirect:/login-page";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login-page";
    }
}
