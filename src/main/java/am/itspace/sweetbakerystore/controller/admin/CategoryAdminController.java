package am.itspace.sweetbakerystore.controller.admin;

import am.itspace.sweetbakerystore.entity.Address;
import am.itspace.sweetbakerystore.entity.Category;
import am.itspace.sweetbakerystore.entity.Product;
import am.itspace.sweetbakerystore.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class CategoryAdminController {

    private final CategoryService categoryService;

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

    @GetMapping(value = "/categories/delete")
    public String delete(@RequestParam("id") int id) {
        categoryService.deleteById(id);
        return "redirect:/categories";
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
