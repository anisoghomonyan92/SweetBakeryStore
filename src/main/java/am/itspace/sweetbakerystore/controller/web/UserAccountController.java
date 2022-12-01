package am.itspace.sweetbakerystore.controller.web;

import am.itspace.sweetbakerystore.entity.Address;
import am.itspace.sweetbakerystore.security.CurrentUser;
import am.itspace.sweetbakerystore.service.AddressService;
import am.itspace.sweetbakerystore.service.CityService;
import am.itspace.sweetbakerystore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserAccountController {
    private final UserService userService;
    private final AddressService addressService;
    private final CityService cityService;

    @GetMapping(value = "/my-account")
    public String userAccount() {
        return "web/user-account/index";
    }

    @GetMapping(value = "/my-account-edit")
    public String userEditAccountPage() {
        return "web/user-account/edit-account";
    }

    @PostMapping(value = "/my-account-edit")
    public String userEditAccount() {
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
        modelMap.addAttribute("currentUser", userService.saveUserAddress(currentUser));
        modelMap.addAttribute("address", byIdForEdit.get());
        modelMap.addAttribute("cities", cityService.findAll());
        return "web/user-account/edit-address";
    }

    @PostMapping(value = "/edit-address")
    public String userEditAddress(@ModelAttribute Address address) {
        addressService.saveAddress(address);
        return "redirect:/user/my-account";
    }

}
