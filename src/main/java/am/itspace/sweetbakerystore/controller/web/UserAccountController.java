package am.itspace.sweetbakerystore.controller.web;

import am.itspace.sweetbakerystore.entity.User;
import am.itspace.sweetbakerystore.security.CurrentUser;
import am.itspace.sweetbakerystore.service.AddressService;
import am.itspace.sweetbakerystore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.aspectj.bridge.Message;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserAccountController {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserService userService;
    private final AddressService addressService;

    @GetMapping(value = "/my-account")
    public String userAccount() {
        return "web/user-account/index";
    }

    @GetMapping(value = "/my-account-edit")
    public String userEditAccountPage(@AuthenticationPrincipal CurrentUser currentUser,
                                      ModelMap modelMap) {
        String email = currentUser.getUsername();
        User user = userService.getByEmail(email);
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("addresses", addressService.findAll());
        return "web/user-account/edit-account";
    }

    @PostMapping(value = "/my-account-edit")
    public String userEditAccount( @ModelAttribute User user,
                                  @AuthenticationPrincipal CurrentUser currentUser,
                                  @RequestParam("userImage") MultipartFile file,
                                  ModelMap modelMap) throws IOException {
        if (!file.isEmpty() && file.getSize() > 0) {
            if (file.getContentType() != null && !file.getContentType().contains("image")) {
                modelMap.addAttribute("errorMessageFile", "Please choose only image");
            }
        }
        Optional<User> userById = userService.findById(currentUser.getUser().getId());
        if (userById.isPresent()) {
            user.setId(userById.get().getId());
            user.setProfilePic(userById.get().getProfilePic());
            userService.updateUser(user, file);
        }
        return "redirect:/user/my-account";
    }

    @PostMapping("/changePassword")
    public String changePassword( @RequestParam("oldPassword") String oldPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 Principal principal, ModelMap modelMap) {
        String userName = principal.getName();
        User currentUser = this.userService.getByEmail(userName);
        if (this.passwordEncoder.matches(oldPassword, currentUser.getPassword())) {
            //change password
            currentUser.setPassword(this.passwordEncoder.encode(newPassword));
            this.userService.save(currentUser);
            modelMap.addAttribute("message", "Your password is successfully changed");
        } else {
            modelMap.addAttribute("message", "Please enter correct old password");
        }
        return "redirect:/user/my-account";
    }

    @GetMapping(value = "/my-orders")
    public String userOrdersPage() {
        return "web/user-account/my-orders";
    }

    @GetMapping(value = "/edit-address")
    public String userAddressPage() {
        return "web/user-account/edit-address";
    }

    @PostMapping(value = "/edit-address")
    public String userEditAddress() {
        return "redirect:/user/my-account";
    }

    @GetMapping(value = "/getImage", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam("fileName") String fileName) throws IOException {
        return userService.getUserImage(fileName);
    }

}
