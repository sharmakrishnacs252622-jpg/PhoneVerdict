package com.phoneverdict.controller;

import com.phoneverdict.service.PhoneService;
import com.phoneverdict.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final PhoneService phoneService;
    private final ReviewService reviewService;

    @Autowired
    public HomeController(PhoneService phoneService, ReviewService reviewService) {
        this.phoneService = phoneService;
        this.reviewService = reviewService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("latestPhones", phoneService.getLatestPhones());
        model.addAttribute("topRatedPhones", phoneService.getTopRatedPhones());
        model.addAttribute("recentReviews", reviewService.getRecentApprovedReviews());
        model.addAttribute("bestCamera", phoneService.getPhonesByCategory("best-camera").stream().limit(3).toList());
        model.addAttribute("bestBattery", phoneService.getPhonesByCategory("best-battery").stream().limit(3).toList());
        return "index";
    }
}
