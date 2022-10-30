package am.itspace.sweetbakerystore.controller.web;

import am.itspace.sweetbakerystore.entity.Product;
import am.itspace.sweetbakerystore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
public class ShopController {
    private final ProductService productService;

    @GetMapping(value = "/shop")
    public String shop(ModelMap modelMap, @PageableDefault(size = 9) Pageable pageable) {

        Page<Product> paginated = productService.findPaginated (pageable);
        modelMap.addAttribute("products", paginated);
        int totalPages = paginated.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelMap.addAttribute("pageNumbers", pageNumbers);
        }
        return "web/shop/index";
    }

    @GetMapping(value = "/products/getImage", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam("fileName") String fileName) throws IOException {
        return productService.getProductImage(fileName);
    }

    @GetMapping(value = "/product/single-page/{id}")
    public String productSinglePage(@PathVariable("id") int id, ModelMap modelMap) {
        Optional<Product> byId = productService.findById(id);
        if(byId.isEmpty()){
            return "redirect:/shop";
        }
        modelMap.addAttribute("product",byId.get());

        return "web/product-category/single-page/index";
    }
}
