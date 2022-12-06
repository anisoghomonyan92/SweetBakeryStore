package am.itspace.sweetbakerystore.controller.web;

import am.itspace.sweetbakerystore.entity.Address;
import am.itspace.sweetbakerystore.security.CurrentUser;
import am.itspace.sweetbakerystore.service.AddressService;
import am.itspace.sweetbakerystore.service.CityService;
import am.itspace.sweetbakerystore.entity.User;
import am.itspace.sweetbakerystore.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserAccountController {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserService userService;
    private final AddressService addressService;
    private final CityService cityService;


    @GetMapping(value = "/my-account")
    public String userAccount() {
        return "web/user-account/index";
    }

    @GetMapping(value = "/my-account-edit")
    public String userEditAccountPage(@AuthenticationPrincipal CurrentUser currentUser,
                                      ModelMap modelMap) {
        log.info("Endpoint user/my-account-edit called by {}", currentUser.getUser().getEmail());
        String email = currentUser.getUsername();
        User user = userService.getByEmail(email);
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("addresses", addressService.findAll());
        return "web/user-account/edit-account";
    }

    @PostMapping(value = "/my-account-edit")
    public String userEditAccount(@ModelAttribute User user,
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
        log.info("Endpoint user/my-account-edit updated by {}", currentUser.getUser().getEmail());
        return "redirect:/user/my-account";
    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestParam("oldPassword") String oldPassword,
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
        log.info("{} changed your password.", principal.getName());
        return "redirect:/user/my-account";
    }

    @GetMapping(value = "/my-orders")
    public String userOrdersPage() {
        return "web/user-account/my-orders";
    }

    @GetMapping(value = "/edit-address")
    public String userAddressPage(@RequestParam("id") int id,
                                  @AuthenticationPrincipal CurrentUser currentUser,
                                  ModelMap modelMap) {
        Optional<Address> byIdForEdit = addressService.findByIdForEdit(id);
        if (byIdForEdit.isEmpty()) {
            return "redirect:/user/edit-address";
        }
        log.info("Endpoint user/edit-address called by {}", currentUser.getUser().getEmail());
        modelMap.addAttribute("currentUser", userService.saveUserAddress(currentUser));
        modelMap.addAttribute("address", byIdForEdit.get());
        modelMap.addAttribute("cities", cityService.findAll());
        return "web/user-account/edit-address";
    }

    @PostMapping(value = "/edit-address")
    public String userEditAddress(@ModelAttribute Address address,
                                  @AuthenticationPrincipal CurrentUser currentUser) {
        addressService.saveAddress(address);
        log.info("{} updated address.", currentUser.getUser().getEmail());
        return "redirect:/user/my-account";
    }

    @GetMapping(value = "/getImage", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam("fileName") String fileName) throws IOException {
        return userService.getUserImage(fileName);
    }

}
