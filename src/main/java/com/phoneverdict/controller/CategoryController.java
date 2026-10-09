package com.phoneverdict.controller;

import com.phoneverdict.model.Phone;
import com.phoneverdict.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final PhoneService phoneService;

    @Autowired
    public CategoryController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @GetMapping
    public String categories(Model model) {
        model.addAttribute("bestOverall", phoneService.getPhonesByCategory("best-overall"));
        model.addAttribute("bestCamera", phoneService.getPhonesByCategory("best-camera"));
        model.addAttribute("bestGaming", phoneService.getPhonesByCategory("best-gaming"));
        model.addAttribute("bestBattery", phoneService.getPhonesByCategory("best-battery"));
        model.addAttribute("bestDisplay", phoneService.getPhonesByCategory("best-display"));
        model.addAttribute("bestValue", phoneService.getPhonesByCategory("best-value"));
        return "categories";
    }

    @GetMapping("/{category}")
    public String categoryDetail(@PathVariable String category, Model model) {
        List<Phone> phones = phoneService.getPhonesByCategory(category);
        String categoryName;

        switch (category.toLowerCase()) {
            case "best-camera":
                categoryName = "Best Camera Smartphones";
                break;
            case "best-gaming":
                categoryName = "Best Gaming Smartphones";
                break;
            case "best-battery":
                categoryName = "Best Battery Life Smartphones";
                break;
            case "best-display":
                categoryName = "Best Display Smartphones";
                break;
            case "best-value":
                categoryName = "Best Value for Money Smartphones";
                break;
            case "best-overall":
            default:
                categoryName = "Best Overall Smartphones";
                break;
        }

        model.addAttribute("phones", phones);
        model.addAttribute("categoryName", categoryName);
        model.addAttribute("categorySlug", category);
        return "category-detail";
    }
}
