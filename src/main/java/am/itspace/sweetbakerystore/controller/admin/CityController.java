package am.itspace.sweetbakerystore.controller.admin;

import am.itspace.sweetbakerystore.entity.City;
import am.itspace.sweetbakerystore.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class CityController {
    private final CityService cityService;

    @GetMapping(value = "/cities")
    public String citiesPage(ModelMap modelMap) {
        modelMap.addAttribute("cities", cityService.findAll());
        return "admin/cities";
    }

    @GetMapping(value = "/cities/delete/{id}")
    public String delete(@PathVariable("id") int id, ModelMap modelMap) {
        try {
            cityService.deleteById(id);
        } catch (Exception e) {
            modelMap.addAttribute("deleteErrorMessage", "You can not delete this object because there is some relationships with it.");
            return "admin/cities";
        }
        return "redirect:/admin/cities";
    }

    @GetMapping(value = "/cities-add")
    public String addAddressPage() {
        return "admin/cities-add";
    }

    @PostMapping(value = "/cities-add")
    public String addCity(@ModelAttribute City city, ModelMap modelMap) {
        Optional<City> byName = cityService.findByName(city.getName());
        if (byName.isPresent()) {
            modelMap.addAttribute("errorMessage", "City with this name has already exists.");
            return "admin/cities-add";
        }
        cityService.save(city);
        return "redirect:/admin/cities";
    }

    @GetMapping("/cities-edit")
    public String editCityPage(@RequestParam("id") int id, ModelMap modelMap) {
        Optional<City> cityOptional = cityService.findById(id);
        if (cityOptional.isEmpty()) {
            return "redirect:/admin/cities";
        }
        modelMap.addAttribute("city", cityOptional.get());
        return "admin/cities-edit";
    }

    @PostMapping("/cities-edit")
    public String editCity(@ModelAttribute City city) {
        cityService.save(city);
        return "redirect:/admin";
    }
}
