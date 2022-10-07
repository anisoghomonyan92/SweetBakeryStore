package am.itspace.sweetbakerystore.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoryAdminController {
    @GetMapping(value = "/categories")
    public String categoryPage() {
        return "admin/categories";
    }

    @GetMapping(value = "/categories-add")
    public String addCategoryPage() {
        return "admin/categories";
    }

    @PostMapping(value = "/categories-add")
    public String addCategory() {
        return "redirect:/admin/categories";
    }

    @PostMapping(value = "/categories-edit")
    public String editCategoryPage() {
        return "redirect:/admin/categories-edit";
    }
}
