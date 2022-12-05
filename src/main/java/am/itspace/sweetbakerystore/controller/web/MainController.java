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
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
            log.info("User with username {} logged", currentUser.getUser().getEmail());
            if (user.getRole() == Role.ADMIN || user.getRole() == Role.PARTNER) {
                return "redirect:/admin/admin-page";
            } else if (user.getRole() == Role.USER) {
                return "redirect:/user/my-account";
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
                          ModelMap modelMap
                          ) throws IOException {
        Optional<User> byEmail = userService.findByEmail(user.getEmail());
        City byID = cityService.findByID(cityId);
        Address address = new Address();
        address.setCity(byID);
        address.setName(addressName);
        Address savedAddress = addressService.saveAddress(address);
        user.setAddress(savedAddress);
        if (byEmail.isPresent()) {
            modelMap.addAttribute("errorMessage", "Email Already in use");
            return "register";
        } else {
            if (!file.isEmpty() && file.getSize() > 0) {
                if (file.getContentType() != null && !file.getContentType().contains("image")) {
                    modelMap.addAttribute("errorMessageFile", "Please choose only image");
                    return "register";
                }
            }
            log.info("User with username {} registered", user.getEmail());
            userService.saveUser(user, file);
            return "redirect:/login";
        }
    }

    @GetMapping(value = "/verify/user")
    public String verifyUser(@RequestParam("email") String email, @RequestParam("token") String token,
                             ModelMap modelMap) throws Exception {
        boolean verified = userService.verifyUser(email, token);
        String pageTitle = verified ? "Verification Succeeded!" : "Verification Failed";
        modelMap.addAttribute("pageTitle", pageTitle);
        return verified ? "verify-success" : "verify-fail";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login-page";
    }
}
