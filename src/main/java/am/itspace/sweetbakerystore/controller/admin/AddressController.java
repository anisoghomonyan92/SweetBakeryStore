package am.itspace.sweetbakerystore.controller.admin;

import am.itspace.sweetbakerystore.entity.Address;
import am.itspace.sweetbakerystore.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AddressController {

    private final AddressService addressService;

    @GetMapping(value = "/addresses")
    public String addressPage(ModelMap modelMap,
                              @RequestParam("page") Optional<Integer> page,
                              @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        Page<Address> paginated = addressService.findPaginated(PageRequest.of(currentPage-1,pageSize));

        modelMap.addAttribute("addresses", paginated);
        int totalPages = paginated.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelMap.addAttribute("pageNumbers", pageNumbers);
        }

        return "admin/addresses";
    }

    @GetMapping(value = "/addresses/delete")
    public String delete(@RequestParam("id") int id) {
        addressService.deleteById(id);
        return "redirect:/addresses";
    }
}
