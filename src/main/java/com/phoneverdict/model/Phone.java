package com.phoneverdict.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "phones")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Brand is required")
    @Column(nullable = false)
    private String brand;

    @NotBlank(message = "Model is required")
    @Column(nullable = false)
    private String model;

    @NotNull(message = "Price is required")
    @Column(nullable = false)
    private Double price;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    // Display
    @Column(name = "display_size")
    private String displaySize;

    private String resolution;

    @Column(name = "refresh_rate")
    private String refreshRate;

    @Column(name = "panel_type")
    private String panelType;

    // Performance
    private String processor;
    private String ram;
    private String storage;
    private String gpu;

    // Camera
    @Column(name = "main_camera")
    private String mainCamera;

    @Column(name = "ultrawide_camera")
    private String ultrawideCamera;

    @Column(name = "telephoto_camera")
    private String telephotoCamera;

    @Column(name = "front_camera")
    private String frontCamera;

    // Battery & Charging
    private String battery;

    @Column(name = "charging_speed")
    private String chargingSpeed;

    // Software
    private String os;

    // Connectivity
    @Column(name = "five_g")
    private Boolean fiveG = true;

    private String wifi;
    private String bluetooth;
    private Boolean nfc = true;

    // Body & Design
    @Column(name = "water_resistance")
    private String waterResistance;

    private String weight;
    private String colors;

    // Pros & Cons
    @Column(columnDefinition = "TEXT")
    private String pros;

    @Column(columnDefinition = "TEXT")
    private String cons;

    // Verdict Scores (0.0 - 10.0)
    @Column(name = "performance_score")
    private Double performanceScore = 8.0;

    @Column(name = "camera_score")
    private Double cameraScore = 8.0;

    @Column(name = "battery_score")
    private Double batteryScore = 8.0;

    @Column(name = "display_score")
    private Double displayScore = 8.0;

    @Column(name = "value_score")
    private Double valueScore = 8.0;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Transient fields calculated on-the-fly
    @Transient
    private Double overallScore;

    @Transient
    private Double averageUserRating = 0.0;

    @Transient
    private Long reviewCount = 0L;

    public Phone() {
    }

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }

    public List<String> getProsList() {
        if (pros == null || pros.trim().isEmpty()) {
            return new ArrayList<>();
        }
        return Arrays.stream(pros.split(";"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }

    public List<String> getConsList() {
        if (cons == null || cons.trim().isEmpty()) {
            return new ArrayList<>();
        }
        return Arrays.stream(cons.split(";"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDisplaySize() {
        return displaySize;
    }

    public void setDisplaySize(String displaySize) {
        this.displaySize = displaySize;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getRefreshRate() {
        return refreshRate;
    }

    public void setRefreshRate(String refreshRate) {
        this.refreshRate = refreshRate;
    }

    public String getPanelType() {
        return panelType;
    }

    public void setPanelType(String panelType) {
        this.panelType = panelType;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public String getMainCamera() {
        return mainCamera;
    }

    public void setMainCamera(String mainCamera) {
        this.mainCamera = mainCamera;
    }

    public String getUltrawideCamera() {
        return ultrawideCamera;
    }

    public void setUltrawideCamera(String ultrawideCamera) {
        this.ultrawideCamera = ultrawideCamera;
    }

    public String getTelephotoCamera() {
        return telephotoCamera;
    }

    public void setTelephotoCamera(String telephotoCamera) {
        this.telephotoCamera = telephotoCamera;
    }

    public String getFrontCamera() {
        return frontCamera;
    }

    public void setFrontCamera(String frontCamera) {
        this.frontCamera = frontCamera;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public String getChargingSpeed() {
        return chargingSpeed;
    }

    public void setChargingSpeed(String chargingSpeed) {
        this.chargingSpeed = chargingSpeed;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public Boolean getFiveG() {
        return fiveG;
    }

    public void setFiveG(Boolean fiveG) {
        this.fiveG = fiveG;
    }

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    public String getBluetooth() {
        return bluetooth;
    }

    public void setBluetooth(String bluetooth) {
        this.bluetooth = bluetooth;
    }

    public Boolean getNfc() {
        return nfc;
    }

    public void setNfc(Boolean nfc) {
        this.nfc = nfc;
    }

    public String getWaterResistance() {
        return waterResistance;
    }

    public void setWaterResistance(String waterResistance) {
        this.waterResistance = waterResistance;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public String getPros() {
        return pros;
    }

    public void setPros(String pros) {
        this.pros = pros;
    }

    public String getCons() {
        return cons;
    }

    public void setCons(String cons) {
        this.cons = cons;
    }

    public Double getPerformanceScore() {
        return performanceScore;
    }

    public void setPerformanceScore(Double performanceScore) {
        this.performanceScore = performanceScore;
    }

    public Double getCameraScore() {
        return cameraScore;
    }

    public void setCameraScore(Double cameraScore) {
        this.cameraScore = cameraScore;
    }

    public Double getBatteryScore() {
        return batteryScore;
    }

    public void setBatteryScore(Double batteryScore) {
        this.batteryScore = batteryScore;
    }

    public Double getDisplayScore() {
        return displayScore;
    }

    public void setDisplayScore(Double displayScore) {
        this.displayScore = displayScore;
    }

    public Double getValueScore() {
        return valueScore;
    }

    public void setValueScore(Double valueScore) {
        this.valueScore = valueScore;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Double getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(Double overallScore) {
        this.overallScore = overallScore;
    }

    public Double getAverageUserRating() {
        return averageUserRating;
    }

    public void setAverageUserRating(Double averageUserRating) {
        this.averageUserRating = averageUserRating;
    }

    public Long getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Long reviewCount) {
        this.reviewCount = reviewCount;
    }
}
