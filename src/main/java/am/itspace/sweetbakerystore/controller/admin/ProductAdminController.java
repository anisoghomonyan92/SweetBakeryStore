package am.itspace.sweetbakerystore.controller.admin;

import am.itspace.sweetbakerystore.entity.Product;
import am.itspace.sweetbakerystore.security.CurrentUser;
import am.itspace.sweetbakerystore.service.CategoryService;
import am.itspace.sweetbakerystore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class ProductAdminController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping(value = "/products")
    public String productPage(ModelMap modelMap,
                              @RequestParam("page") Optional<Integer> page,
                              @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        Page<Product> paginated = productService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        modelMap.addAttribute("products", paginated);
        int totalPages = paginated.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelMap.addAttribute("pageNumbers", pageNumbers);
        }
        return "admin/products";
    }

    @GetMapping(value = "/products/delete/{id}")
    public String delete(@PathVariable("id") int id, ModelMap modelMap) {
        try {
            productService.deleteById(id);
        } catch (Exception e) {
            modelMap.addAttribute("deleteErrorMessage", "You can not delete this object because there is some relationships with it.");
            return "admin/products";
        }
        return "redirect:/admin/products";
    }

    @GetMapping(value = "/products-add")
    public String addProductPage(ModelMap modelMap) {
        modelMap.addAttribute("categories", categoryService.findAll());
        return "admin/products-add";
    }

    @PostMapping(value = "/products-add")
    public String addProduct(@ModelAttribute Product product,
                             @RequestParam("productImage") MultipartFile file,
                             @AuthenticationPrincipal CurrentUser currentUser,
                             ModelMap modelMap) throws IOException {
        if (!file.isEmpty() && file.getSize() > 0) {
            if (file.getContentType() != null && !file.getContentType().contains("image")) {
                modelMap.addAttribute("errorMessageFile", "Please choose only image");
                return "admin/products-add";
            }
        }
        productService.save(product, file, currentUser);
        return "redirect:/admin/products";
    }

    @GetMapping(value = "/products/getImage", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam("fileName") String fileName) throws IOException {
        return productService.getProductImage(fileName);
    }

    @GetMapping(value = "/products-edit")
    public String editProductPage(@RequestParam("id") int id,
                                  ModelMap modelMap) {
        Optional<Product> productOptional = productService.findById(id);
        if (productOptional.isEmpty()) {
            return "redirect:/admin/products";
        }
        modelMap.addAttribute("product", productOptional.get());
        modelMap.addAttribute("categories", categoryService.findAll());
        return "admin/products-edit";
    }

    @PostMapping(value = "/products-edit")
    public String editProduct(@ModelAttribute Product product,
                              @RequestParam("productImage") MultipartFile file,
                              @AuthenticationPrincipal CurrentUser currentUser,
                              ModelMap modelMap) throws Exception {
        if (!file.isEmpty() && file.getSize() > 0) {
            if (file.getContentType() != null && !file.getContentType().contains("image")) {
                modelMap.addAttribute("errorMessageFile", "Please choose only image");
            }
        }
        productService.save(product, file, currentUser);
        return "redirect:/admin/products";
    }
}
