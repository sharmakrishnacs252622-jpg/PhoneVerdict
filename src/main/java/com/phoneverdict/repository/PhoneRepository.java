package com.phoneverdict.repository;

import com.phoneverdict.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
    List<Phone> findByBrand(String brand);
    List<Phone> findByBrandIgnoreCase(String brand);
    List<Phone> findByPriceBetween(Double min, Double max);
    List<Phone> findByPriceLessThan(Double price);
    List<Phone> findByPriceGreaterThan(Double price);

    @Query("SELECT DISTINCT p.brand FROM Phone p ORDER BY p.brand")
    List<String> findAllBrands();

    @Query("SELECT p FROM Phone p WHERE LOWER(p.brand) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(p.model) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(p.processor) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Phone> searchPhones(@Param("query") String query);

    List<Phone> findTop6ByOrderByCreatedAtDesc();
    List<Phone> findTop6ByOrderByPerformanceScoreDesc();
    List<Phone> findTop6ByOrderByCameraScoreDesc();
    List<Phone> findTop6ByOrderByBatteryScoreDesc();
    List<Phone> findTop6ByOrderByDisplayScoreDesc();
    List<Phone> findTop6ByOrderByValueScoreDesc();

    @Query("SELECT p FROM Phone p ORDER BY p.createdAt DESC")
    List<Phone> findAllOrderByCreatedAtDesc();
}
