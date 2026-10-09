package com.phoneverdict.controller;

import com.phoneverdict.dto.ReviewDTO;
import com.phoneverdict.model.Phone;
import com.phoneverdict.model.Review;
import com.phoneverdict.model.ReviewStatus;
import com.phoneverdict.model.User;
import com.phoneverdict.service.PhoneService;
import com.phoneverdict.service.ReviewService;
import com.phoneverdict.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class ReviewController {

    private final ReviewService reviewService;
    private final PhoneService phoneService;
    private final UserService userService;

    @Autowired
    public ReviewController(ReviewService reviewService,
                            PhoneService phoneService,
                            UserService userService) {
        this.reviewService = reviewService;
        this.phoneService = phoneService;
        this.userService = userService;
    }

    @PostMapping("/phones/{phoneId}/reviews")
    public String submitReview(@PathVariable Long phoneId,
                               @Valid @ModelAttribute("reviewDTO") ReviewDTO reviewDTO,
                               BindingResult bindingResult,
                               @AuthenticationPrincipal UserDetails userDetails,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Please provide a valid rating, title, and review description.");
            return "redirect:/phones/" + phoneId;
        }

        try {
            Phone phone = phoneService.getPhoneById(phoneId);
            Review review = new Review();
            review.setPhone(phone);
            review.setRating(reviewDTO.getRating());
            review.setTitle(reviewDTO.getTitle().trim());
            review.setComment(reviewDTO.getComment().trim());
            review.setStatus(ReviewStatus.PENDING);

            if (userDetails != null) {
                Optional<User> userOpt = userService.findByEmail(userDetails.getUsername());
                userOpt.ifPresent(review::setUser);
            }

            reviewService.saveReview(review);
            redirectAttributes.addFlashAttribute("successMessage", "Thank you! Your review has been submitted and is pending administrator approval.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to submit review: " + e.getMessage());
        }

        return "redirect:/phones/" + phoneId;
    }
}
