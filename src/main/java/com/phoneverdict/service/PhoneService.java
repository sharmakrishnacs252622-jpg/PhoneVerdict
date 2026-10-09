package com.phoneverdict.service;

import com.phoneverdict.model.Phone;
import com.phoneverdict.repository.PhoneRepository;
import com.phoneverdict.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhoneService {

    private final PhoneRepository phoneRepository;
    private final ReviewRepository reviewRepository;
    private final VerdictService verdictService;

    @Autowired
    public PhoneService(PhoneRepository phoneRepository,
                        ReviewRepository reviewRepository,
                        @Lazy VerdictService verdictService) {
        this.phoneRepository = phoneRepository;
        this.reviewRepository = reviewRepository;
        this.verdictService = verdictService;
    }

    public List<Phone> getAllPhones() {
        List<Phone> phones = phoneRepository.findAllOrderByCreatedAtDesc();
        populateTransientFields(phones);
        return phones;
    }

    public Phone getPhoneById(Long id) {
        Phone phone = phoneRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Phone not found with id: " + id));
        populateTransientFields(phone);
        return phone;
    }

    @Transactional
    public Phone savePhone(Phone phone) {
        return phoneRepository.save(phone);
    }

    @Transactional
    public void deletePhone(Long id) {
        phoneRepository.deleteById(id);
    }

    public List<Phone> searchPhones(String query) {
        if (query == null || query.trim().isEmpty()) {
            return getAllPhones();
        }
        List<Phone> phones = phoneRepository.searchPhones(query.trim());
        populateTransientFields(phones);
        return phones;
    }

    public List<Phone> filterPhones(String brand, Double minPrice, Double maxPrice, String sort) {
        List<Phone> phones = phoneRepository.findAll();
        populateTransientFields(phones);

        // Filter by brand
        if (brand != null && !brand.trim().isEmpty() && !brand.equalsIgnoreCase("all")) {
            phones = phones.stream()
                    .filter(p -> p.getBrand().equalsIgnoreCase(brand.trim()))
                    .collect(Collectors.toList());
        }

        // Filter by price range
        if (minPrice != null) {
            phones = phones.stream()
                    .filter(p -> p.getPrice() != null && p.getPrice() >= minPrice)
                    .collect(Collectors.toList());
        }
        if (maxPrice != null) {
            phones = phones.stream()
                    .filter(p -> p.getPrice() != null && p.getPrice() <= maxPrice)
                    .collect(Collectors.toList());
        }

        // Apply sorting
        if (sort != null) {
            switch (sort.toLowerCase()) {
                case "price-asc":
                    phones.sort(Comparator.comparing(Phone::getPrice, Comparator.nullsLast(Double::compareTo)));
                    break;
                case "price-desc":
                    phones.sort(Comparator.comparing(Phone::getPrice, Comparator.nullsLast(Double::compareTo)).reversed());
                    break;
                case "rating":
                    phones.sort(Comparator.comparing(Phone::getOverallScore, Comparator.nullsLast(Double::compareTo)).reversed());
                    break;
                case "latest":
                default:
                    phones.sort(Comparator.comparing(Phone::getCreatedAt, Comparator.nullsLast(Comparator.naturalOrder())).reversed());
                    break;
            }
        }

        return phones;
    }

    public List<Phone> getLatestPhones() {
        List<Phone> phones = phoneRepository.findTop6ByOrderByCreatedAtDesc();
        populateTransientFields(phones);
        return phones;
    }

    public List<Phone> getTopRatedPhones() {
        List<Phone> phones = getAllPhones();
        return phones.stream()
                .sorted(Comparator.comparing(Phone::getOverallScore, Comparator.nullsLast(Double::compareTo)).reversed())
                .limit(6)
                .collect(Collectors.toList());
    }

    public List<Phone> getPhonesByCategory(String category) {
        List<Phone> phones;
        if (category == null) {
            return getTopRatedPhones();
        }

        switch (category.toLowerCase()) {
            case "best-camera":
                phones = phoneRepository.findTop6ByOrderByCameraScoreDesc();
                break;
            case "best-gaming":
                phones = phoneRepository.findTop6ByOrderByPerformanceScoreDesc();
                break;
            case "best-battery":
                phones = phoneRepository.findTop6ByOrderByBatteryScoreDesc();
                break;
            case "best-display":
                phones = phoneRepository.findTop6ByOrderByDisplayScoreDesc();
                break;
            case "best-value":
                phones = phoneRepository.findTop6ByOrderByValueScoreDesc();
                break;
            case "best-overall":
            default:
                return getTopRatedPhones();
        }

        populateTransientFields(phones);
        return phones;
    }

    public List<String> getAllBrands() {
        return phoneRepository.findAllBrands();
    }

    public long getPhoneCount() {
        return phoneRepository.count();
    }

    public void populateTransientFields(Phone phone) {
        if (phone == null) return;
        phone.setOverallScore(verdictService.calculateOverallScore(phone));
        if (phone.getId() != null) {
            Double avgRating = reviewRepository.findAverageRatingByPhoneId(phone.getId());
            phone.setAverageUserRating(avgRating != null ? Math.round(avgRating * 10.0) / 10.0 : 0.0);
            Long count = reviewRepository.countApprovedReviewsByPhoneId(phone.getId());
            phone.setReviewCount(count != null ? count : 0L);
        } else {
            phone.setAverageUserRating(0.0);
            phone.setReviewCount(0L);
        }
    }

    public void populateTransientFields(List<Phone> phones) {
        if (phones != null) {
            phones.forEach(this::populateTransientFields);
        }
    }
}
