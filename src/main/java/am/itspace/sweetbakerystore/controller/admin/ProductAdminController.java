package am.itspace.sweetbakerystore.controller.admin;

import am.itspace.sweetbakerystore.entity.Payment;
import am.itspace.sweetbakerystore.entity.Product;
import am.itspace.sweetbakerystore.service.CategoryService;
import am.itspace.sweetbakerystore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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
        Page<Product> paginated = productService.findPaginated(PageRequest.of(currentPage-1,pageSize));

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

    @GetMapping(value = "/products/getImage", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam("fileName") String fileName) throws IOException {
        return productService.getProductImage(fileName);
    }

    @GetMapping(value = "/products/delete")
    public String delete(@RequestParam("id") int id) {
        productService.deleteById(id);
        return "redirect:/admin/products";
    }

    @GetMapping(value = "/products-add")
    public String addProductPage() {
        return "admin/products";
    }

    @PostMapping(value = "/products-add")
    public String addProduct() {
        return "redirect:/admin/products";
    }

    @GetMapping (value = "/products-edit")
    public String editProductPage() {
        return "admin/products-edit";
    }

    @PostMapping(value = "/products-edit")
    public String editProduct() {
        return "redirect:/admin/products";
    }


}


