package am.itspace.sweetbakerystore.controller.admin;


import am.itspace.sweetbakerystore.entity.Role;
import am.itspace.sweetbakerystore.entity.User;

import am.itspace.sweetbakerystore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class UserAdminController {

    private final UserService userService;

    @GetMapping(value = "/users")
    public String usersPage(ModelMap modelMap,
                            @RequestParam("page") Optional<Integer> page,
                            @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        Page<User> paginated = userService.findPaginated(PageRequest.of(currentPage-1,pageSize));

        modelMap.addAttribute("users", paginated);
        int totalPages = paginated.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelMap.addAttribute("pageNumbers", pageNumbers);
        }
        List<Role> roles = Arrays.asList(Role.values());
        modelMap.addAttribute("roles", roles);
        return "admin/users";
    }

    @GetMapping(value = "/users/getImage", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam("fileName") String fileName) throws IOException {
        return userService.getUserImage(fileName);

    }
    @GetMapping(value = "/users/delete")
    public String delete(@RequestParam("id") int id) {
        userService.deleteById(id);
        return "redirect:/admin/users";
    }

//    @GetMapping(value = "/users/change-role")
//    public String userChangeRolePage() {
//        return "admin/users";
//    }

    @PostMapping(value = "/users/change-role")
    public String userChangeRole(@RequestParam("userId") int userId,
                                 @RequestParam("role") Role role) {
       Optional<User> userOptional= userService.findById(userId, role);

        return "redirect:/admin/users";
    }



}
