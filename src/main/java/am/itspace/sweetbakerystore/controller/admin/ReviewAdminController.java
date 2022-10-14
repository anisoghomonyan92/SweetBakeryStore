package am.itspace.sweetbakerystore.controller.admin;

import am.itspace.sweetbakerystore.entity.Payment;
import am.itspace.sweetbakerystore.entity.Review;
import am.itspace.sweetbakerystore.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
public class ReviewAdminController {

    private final ReviewService reviewService;

    @GetMapping(value = "/reviews")
    public String reviewPage(ModelMap modelMap,
                             @RequestParam("page") Optional<Integer> page,
                             @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        Page<Review> paginated = reviewService.findPaginated(PageRequest.of(currentPage-1,pageSize));

        modelMap.addAttribute("reviews", paginated);
        int totalPages = paginated.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelMap.addAttribute("pageNumbers", pageNumbers);
        }

        return "admin/reviews";
    }

}
