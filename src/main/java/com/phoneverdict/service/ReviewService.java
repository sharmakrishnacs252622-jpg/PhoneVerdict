package com.phoneverdict.service;

import com.phoneverdict.model.Review;
import com.phoneverdict.model.ReviewStatus;
import com.phoneverdict.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Transactional
    public Review saveReview(Review review) {
        if (review.getStatus() == null) {
            review.setStatus(ReviewStatus.PENDING);
        }
        return reviewRepository.save(review);
    }

    public List<Review> getApprovedReviewsByPhone(Long phoneId) {
        return reviewRepository.findByPhoneIdAndStatus(phoneId, ReviewStatus.APPROVED);
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAllByOrderByCreatedAtDesc();
    }

    public List<Review> getPendingReviews() {
        return reviewRepository.findByStatus(ReviewStatus.PENDING);
    }

    public List<Review> getRecentApprovedReviews() {
        return reviewRepository.findTop5ByStatusOrderByCreatedAtDesc(ReviewStatus.APPROVED);
    }

    @Transactional
    public void approveReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Review not found with id: " + id));
        review.setStatus(ReviewStatus.APPROVED);
        reviewRepository.save(review);
    }

    @Transactional
    public void rejectReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Review not found with id: " + id));
        review.setStatus(ReviewStatus.REJECTED);
        reviewRepository.save(review);
    }

    @Transactional
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    public Double getAverageRating(Long phoneId) {
        Double avg = reviewRepository.findAverageRatingByPhoneId(phoneId);
        return avg != null ? Math.round(avg * 10.0) / 10.0 : 0.0;
    }

    public Long getReviewCount(Long phoneId) {
        Long count = reviewRepository.countApprovedReviewsByPhoneId(phoneId);
        return count != null ? count : 0L;
    }

    public long getTotalReviewCount() {
        return reviewRepository.count();
    }

    public long getPendingReviewCount() {
        return reviewRepository.countByStatus(ReviewStatus.PENDING);
    }

    public double getOverallAverageRating() {
        List<Review> approved = reviewRepository.findByStatus(ReviewStatus.APPROVED);
        if (approved.isEmpty()) {
            return 0.0;
        }
        double sum = approved.stream().mapToInt(Review::getRating).sum();
        return Math.round((sum / approved.size()) * 10.0) / 10.0;
    }
}
