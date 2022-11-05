package am.itspace.sweetbakerystore.controller.web;

import am.itspace.sweetbakerystore.entity.City;
import am.itspace.sweetbakerystore.entity.Product;
import am.itspace.sweetbakerystore.entity.Review;
import am.itspace.sweetbakerystore.entity.User;
import am.itspace.sweetbakerystore.security.CurrentUser;
import am.itspace.sweetbakerystore.service.ProductService;
import am.itspace.sweetbakerystore.service.ReviewService;
import am.itspace.sweetbakerystore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
public class ShopController {
    private final ProductService productService;
    private final ReviewService reviewService;
    private final UserService userService;

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
    public String productSinglePage(@PathVariable("id") int id, ModelMap modelMap,
                                    @AuthenticationPrincipal CurrentUser currentUser
                                    ) {
        Optional<Product> byId = productService.findById(id);
        if(byId.isEmpty()){
            return "redirect:/shop";
        }
        modelMap.addAttribute("product",byId.get());
        modelMap.addAttribute("currentUser", currentUser);
        return "web/product-category/single-page/index";
    }


    @PostMapping(value = "/add-review")
    public String addReviw(@ModelAttribute Review review,
                           @AuthenticationPrincipal CurrentUser currentUser) {
            reviewService.save(review,currentUser);
        return "redirect:/shop";
    }


}
