package com.phoneverdict.service;

import com.phoneverdict.dto.ComparisonResult;
import com.phoneverdict.dto.VerdictDTO;
import com.phoneverdict.model.Phone;
import com.phoneverdict.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class VerdictService {

    public static final double PERFORMANCE_WEIGHT = 0.25;
    public static final double CAMERA_WEIGHT = 0.20;
    public static final double BATTERY_WEIGHT = 0.15;
    public static final double DISPLAY_WEIGHT = 0.15;
    public static final double VALUE_WEIGHT = 0.15;
    public static final double USER_RATING_WEIGHT = 0.10;

    private final ReviewRepository reviewRepository;

    @Autowired
    public VerdictService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Double calculateOverallScore(Phone phone) {
        if (phone == null) {
            return 0.0;
        }

        double perf = phone.getPerformanceScore() != null ? phone.getPerformanceScore() : 7.0;
        double cam = phone.getCameraScore() != null ? phone.getCameraScore() : 7.0;
        double batt = phone.getBatteryScore() != null ? phone.getBatteryScore() : 7.0;
        double disp = phone.getDisplayScore() != null ? phone.getDisplayScore() : 7.0;
        double val = phone.getValueScore() != null ? phone.getValueScore() : 7.0;

        Double avgUserRating = null;
        if (phone.getId() != null) {
            avgUserRating = reviewRepository.findAverageRatingByPhoneId(phone.getId());
        }

        double userRatingScore = (avgUserRating != null && avgUserRating > 0) ? (avgUserRating * 2.0) : 7.5;

        double weighted = (perf * PERFORMANCE_WEIGHT)
                + (cam * CAMERA_WEIGHT)
                + (batt * BATTERY_WEIGHT)
                + (disp * DISPLAY_WEIGHT)
                + (val * VALUE_WEIGHT)
                + (userRatingScore * USER_RATING_WEIGHT);

        BigDecimal bd = BigDecimal.valueOf(weighted).setScale(1, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public String getVerdictText(Double score) {
        if (score == null) return "Unknown";
        if (score >= 9.0) return "Excellent — Highly Recommended";
        if (score >= 8.0) return "Very Good — Recommended";
        if (score >= 7.0) return "Good — Worth Considering";
        if (score >= 6.0) return "Average";
        return "Not Recommended";
    }

    public String getVerdictClass(Double score) {
        if (score == null) return "average";
        if (score >= 9.0) return "excellent";
        if (score >= 8.0) return "very-good";
        if (score >= 7.0) return "good";
        if (score >= 6.0) return "average";
        return "not-recommended";
    }

    public VerdictDTO getVerdictForPhone(Phone phone) {
        Double overall = calculateOverallScore(phone);
        Double avgRating = phone.getId() != null ? reviewRepository.findAverageRatingByPhoneId(phone.getId()) : null;
        double userRatingScore = (avgRating != null && avgRating > 0) ? (avgRating * 2.0) : 7.5;

        BigDecimal userRatingFormatted = BigDecimal.valueOf(userRatingScore).setScale(1, RoundingMode.HALF_UP);

        return new VerdictDTO(
                phone.getPerformanceScore(),
                phone.getCameraScore(),
                phone.getBatteryScore(),
                phone.getDisplayScore(),
                phone.getValueScore(),
                userRatingFormatted.doubleValue(),
                overall,
                getVerdictText(overall),
                getVerdictClass(overall)
        );
    }

    public ComparisonResult comparePhones(List<Phone> phones) {
        ComparisonResult result = new ComparisonResult();
        result.setPhones(phones);

        if (phones == null || phones.size() < 2) {
            return result;
        }

        for (Phone p : phones) {
            p.setOverallScore(calculateOverallScore(p));
            Double avg = reviewRepository.findAverageRatingByPhoneId(p.getId());
            p.setAverageUserRating(avg != null ? avg : 0.0);
            p.setReviewCount(reviewRepository.countApprovedReviewsByPhoneId(p.getId()));
        }

        Map<String, Integer> winners = new HashMap<>();

        // 1. Price (lower is better)
        winners.put("price", findMinIndex(phones, p -> p.getPrice() != null ? p.getPrice() : Double.MAX_VALUE));

        // 2. RAM (higher is better)
        winners.put("ram", findMaxIndex(phones, p -> extractNumericValue(p.getRam())));

        // 3. Storage (higher is better)
        winners.put("storage", findMaxIndex(phones, p -> extractNumericValue(p.getStorage())));

        // 4. Battery (higher is better)
        winners.put("battery", findMaxIndex(phones, p -> extractNumericValue(p.getBattery())));

        // 5. Camera (cameraScore higher is better)
        winners.put("camera", findMaxIndex(phones, p -> p.getCameraScore() != null ? p.getCameraScore() : 0.0));

        // 6. Performance (performanceScore higher is better)
        winners.put("performance", findMaxIndex(phones, p -> p.getPerformanceScore() != null ? p.getPerformanceScore() : 0.0));

        // 7. Display (refreshRate or displayScore)
        winners.put("display", findMaxIndex(phones, p -> p.getDisplayScore() != null ? p.getDisplayScore() : 0.0));

        // 8. Charging speed (higher W is better)
        winners.put("charging", findMaxIndex(phones, p -> extractNumericValue(p.getChargingSpeed())));

        // 9. Overall Score
        int overallWinnerIdx = findMaxIndex(phones, Phone::getOverallScore);
        winners.put("overallScore", overallWinnerIdx);

        result.setWinners(winners);
        result.setOverallWinnerIndex(overallWinnerIdx >= 0 ? overallWinnerIdx : 0);

        // Generate descriptive verdict comparison text
        Phone winnerPhone = phones.get(result.getOverallWinnerIndex());
        StringBuilder verdictText = new StringBuilder();

        if (phones.size() == 2) {
            Phone p1 = phones.get(0);
            Phone p2 = phones.get(1);

            verdictText.append(winnerPhone.getBrand()).append(" ").append(winnerPhone.getModel())
                    .append(" emerges as the overall winner with a verdict score of ")
                    .append(winnerPhone.getOverallScore()).append("/10. ");

            if (p1.getPrice() != null && p2.getPrice() != null) {
                if (p1.getPrice() < p2.getPrice()) {
                    verdictText.append(p1.getModel()).append(" provides better budget value, while ");
                } else if (p2.getPrice() < p1.getPrice()) {
                    verdictText.append(p2.getModel()).append(" is more cost-effective, while ");
                }
            }
            verdictText.append(winnerPhone.getModel())
                    .append(" leads in core performance and flagship multimedia capabilities.");
        } else {
            verdictText.append("In this comparison, ").append(winnerPhone.getBrand()).append(" ").append(winnerPhone.getModel())
                    .append(" takes the lead with an overall score of ")
                    .append(winnerPhone.getOverallScore()).append("/10, excelling across key metrics.");
        }

        result.setVerdictText(verdictText.toString());
        return result;
    }

    private int findMinIndex(List<Phone> list, ValueExtractor extractor) {
        double minVal = Double.MAX_VALUE;
        int minIdx = -1;
        boolean tie = false;

        for (int i = 0; i < list.size(); i++) {
            double val = extractor.extract(list.get(i));
            if (val < minVal) {
                minVal = val;
                minIdx = i;
                tie = false;
            } else if (Math.abs(val - minVal) < 0.001) {
                tie = true;
            }
        }
        return tie ? -1 : minIdx;
    }

    private int findMaxIndex(List<Phone> list, ValueExtractor extractor) {
        double maxVal = -1.0;
        int maxIdx = -1;
        boolean tie = false;

        for (int i = 0; i < list.size(); i++) {
            double val = extractor.extract(list.get(i));
            if (val > maxVal) {
                maxVal = val;
                maxIdx = i;
                tie = false;
            } else if (Math.abs(val - maxVal) < 0.001) {
                tie = true;
            }
        }
        return tie ? -1 : maxIdx;
    }

    private double extractNumericValue(String text) {
        if (text == null || text.trim().isEmpty()) return 0.0;
        Pattern pattern = Pattern.compile("([0-9]+(?:\\.[0-9]+)?)");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            try {
                return Double.parseDouble(matcher.group(1));
            } catch (NumberFormatException ignored) {}
        }
        return 0.0;
    }

    @FunctionalInterface
    private interface ValueExtractor {
        double extract(Phone phone);
    }
}
