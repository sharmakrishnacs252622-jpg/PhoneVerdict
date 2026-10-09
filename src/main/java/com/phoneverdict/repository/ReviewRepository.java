package com.phoneverdict.repository;

import com.phoneverdict.model.Review;
import com.phoneverdict.model.ReviewStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByPhoneIdAndStatus(Long phoneId, ReviewStatus status);
    List<Review> findByStatus(ReviewStatus status);
    List<Review> findByPhoneId(Long phoneId);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.phone.id = :phoneId AND r.status = 'APPROVED'")
    Double findAverageRatingByPhoneId(@Param("phoneId") Long phoneId);

    @Query("SELECT COUNT(r) FROM Review r WHERE r.phone.id = :phoneId AND r.status = 'APPROVED'")
    Long countApprovedReviewsByPhoneId(@Param("phoneId") Long phoneId);

    long countByStatus(ReviewStatus status);

    List<Review> findTop5ByStatusOrderByCreatedAtDesc(ReviewStatus status);
    List<Review> findAllByOrderByCreatedAtDesc();
}
