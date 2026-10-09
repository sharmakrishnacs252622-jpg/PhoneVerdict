package com.phoneverdict.controller;

import com.phoneverdict.model.Phone;
import com.phoneverdict.model.Review;
import com.phoneverdict.service.PhoneService;
import com.phoneverdict.service.ReviewService;
import com.phoneverdict.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final PhoneService phoneService;
    private final ReviewService reviewService;
    private final UserService userService;

    @Autowired
    public AdminController(PhoneService phoneService,
                           ReviewService reviewService,
                           UserService userService) {
        this.phoneService = phoneService;
        this.reviewService = reviewService;
        this.userService = userService;
    }

    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("totalPhones", phoneService.getPhoneCount());
        model.addAttribute("totalUsers", userService.getUserCount());
        model.addAttribute("totalReviews", reviewService.getTotalReviewCount());
        model.addAttribute("averageRating", reviewService.getOverallAverageRating());
        model.addAttribute("pendingReviewCount", reviewService.getPendingReviewCount());
        model.addAttribute("recentReviews", reviewService.getAllReviews().stream().limit(6).toList());
        return "admin/dashboard";
    }

    // Phone Management
    @GetMapping("/phones")
    public String listPhones(Model model) {
        model.addAttribute("phones", phoneService.getAllPhones());
        return "admin/phones";
    }

    @GetMapping("/phones/add")
    public String addPhoneForm(Model model) {
        model.addAttribute("phone", new Phone());
        return "admin/add-phone";
    }

    @PostMapping("/phones/add")
    public String savePhone(@ModelAttribute Phone phone, RedirectAttributes redirectAttributes) {
        try {
            phoneService.savePhone(phone);
            redirectAttributes.addFlashAttribute("successMessage", "Smartphone added successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to add smartphone: " + e.getMessage());
        }
        return "redirect:/admin/phones";
    }

    @GetMapping("/phones/edit/{id}")
    public String editPhoneForm(@PathVariable Long id, Model model) {
        model.addAttribute("phone", phoneService.getPhoneById(id));
        return "admin/edit-phone";
    }

    @PostMapping("/phones/edit/{id}")
    public String updatePhone(@PathVariable Long id,
                              @ModelAttribute Phone phone,
                              RedirectAttributes redirectAttributes) {
        try {
            phone.setId(id);
            phoneService.savePhone(phone);
            redirectAttributes.addFlashAttribute("successMessage", "Smartphone updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to update smartphone: " + e.getMessage());
        }
        return "redirect:/admin/phones";
    }

    @PostMapping("/phones/delete/{id}")
    public String deletePhone(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            phoneService.deletePhone(id);
            redirectAttributes.addFlashAttribute("successMessage", "Smartphone deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete smartphone: " + e.getMessage());
        }
        return "redirect:/admin/phones";
    }

    // Review Management
    @GetMapping("/reviews")
    public String listReviews(Model model) {
        model.addAttribute("reviews", reviewService.getAllReviews());
        return "admin/reviews";
    }

    @PostMapping("/reviews/{id}/approve")
    public String approveReview(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            reviewService.approveReview(id);
            redirectAttributes.addFlashAttribute("successMessage", "Review approved successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to approve review: " + e.getMessage());
        }
        return "redirect:/admin/reviews";
    }

    @PostMapping("/reviews/{id}/reject")
    public String rejectReview(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            reviewService.rejectReview(id);
            redirectAttributes.addFlashAttribute("successMessage", "Review rejected.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to reject review: " + e.getMessage());
        }
        return "redirect:/admin/reviews";
    }

    @PostMapping("/reviews/{id}/delete")
    public String deleteReview(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            reviewService.deleteReview(id);
            redirectAttributes.addFlashAttribute("successMessage", "Review deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete review: " + e.getMessage());
        }
        return "redirect:/admin/reviews";
    }

    // User Management
    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/users";
    }
}
