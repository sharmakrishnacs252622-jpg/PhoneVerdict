package com.phoneverdict.controller;

import com.phoneverdict.dto.ReviewDTO;
import com.phoneverdict.dto.VerdictDTO;
import com.phoneverdict.model.Phone;
import com.phoneverdict.model.Review;
import com.phoneverdict.service.PhoneService;
import com.phoneverdict.service.ReviewService;
import com.phoneverdict.service.VerdictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/phones")
public class PhoneController {

    private final PhoneService phoneService;
    private final ReviewService reviewService;
    private final VerdictService verdictService;

    @Autowired
    public PhoneController(PhoneService phoneService,
                           ReviewService reviewService,
                           VerdictService verdictService) {
        this.phoneService = phoneService;
        this.reviewService = reviewService;
        this.verdictService = verdictService;
    }

    @GetMapping
    public String listPhones(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false, defaultValue = "latest") String sort,
            Model model) {

        List<Phone> phones;
        if ((brand != null && !brand.isEmpty()) || minPrice != null || maxPrice != null || (sort != null && !sort.equalsIgnoreCase("latest"))) {
            phones = phoneService.filterPhones(brand, minPrice, maxPrice, sort);
        } else {
            phones = phoneService.getAllPhones();
        }

        model.addAttribute("phones", phones);
        model.addAttribute("brands", phoneService.getAllBrands());
        model.addAttribute("selectedBrand", brand != null ? brand : "");
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("selectedSort", sort);

        return "phones";
    }

    @GetMapping("/{id}")
    public String phoneDetails(@PathVariable Long id, Model model) {
        Phone phone = phoneService.getPhoneById(id);
        List<Review> reviews = reviewService.getApprovedReviewsByPhone(id);
        VerdictDTO verdict = verdictService.getVerdictForPhone(phone);

        model.addAttribute("phone", phone);
        model.addAttribute("reviews", reviews);
        model.addAttribute("verdict", verdict);

        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setPhoneId(id);
        model.addAttribute("reviewDTO", reviewDTO);

        return "phone-detail";
    }
}
