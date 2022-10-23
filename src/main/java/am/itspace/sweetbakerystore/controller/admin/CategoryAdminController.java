package am.itspace.sweetbakerystore.controller.admin;

import am.itspace.sweetbakerystore.entity.Category;
import am.itspace.sweetbakerystore.security.CurrentUser;
import am.itspace.sweetbakerystore.service.CategoryService;
import am.itspace.sweetbakerystore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class CategoryAdminController {

    private final CategoryService categoryService;
    private final UserService userService;

    @GetMapping(value = "/categories")
    public String categoryPage(ModelMap modelMap,
                               @RequestParam("page") Optional<Integer> page,
                               @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        Page<Category> paginated = categoryService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        modelMap.addAttribute("categories", paginated);
        int totalPages = paginated.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelMap.addAttribute("pageNumbers", pageNumbers);
        }
        return "admin/categories";
    }

    @GetMapping(value = "/categories/delete/{id}")
    public String delete(@PathVariable("id") int id, ModelMap modelMap) {
        try {
            categoryService.deleteById(id);
        } catch (Exception e) {
            modelMap.addAttribute("deleteErrorMessage", "You can not delete this object because there is some relationships with it.");
            return "admin/categories";
        }
        return "redirect:/admin/categories";
    }

    @GetMapping(value = "/categories-add")
    public String addCategoryPage() {
        return "admin/categories-add";
    }

    @PostMapping(value = "/categories-add")
    public String addCategory(@ModelAttribute Category category,
                              @AuthenticationPrincipal CurrentUser currentUser,
                              ModelMap modelMap) throws Exception {
        Optional<Category> byName = categoryService.findByName(category.getName());

        if (byName.isPresent()) {
            modelMap.addAttribute("errorMessageCategoryName", "Category with this name has already exists.");
            return "admin/categories-add";
        }
        categoryService.save(category, currentUser);
        return "redirect:/admin/categories";

    }

    @GetMapping(value = "/categories-edit")
    public String editCategoryPage(@RequestParam("id") int id,
                                   ModelMap modelMap) {
        Optional<Category> categoryOptional = categoryService.findByIdForEdit(id);
        if (categoryOptional.isEmpty()) {
            return "redirect:/admin/categories";
        }
        modelMap.addAttribute("category", categoryOptional.get());
        return "admin/categories-edit";
    }

    @PostMapping(value = "/categories-edit")
    public String editCategory(@ModelAttribute Category category,
                               @AuthenticationPrincipal CurrentUser currentUser) throws Exception {
        categoryService.save(category, currentUser);
        return "redirect:/admin/categories";
    }
}
