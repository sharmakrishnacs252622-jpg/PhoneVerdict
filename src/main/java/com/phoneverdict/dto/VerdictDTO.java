package com.phoneverdict.dto;

public class VerdictDTO {

    private Double performanceScore;
    private Double cameraScore;
    private Double batteryScore;
    private Double displayScore;
    private Double valueScore;
    private Double userRatingScore;
    private Double overallScore;
    private String verdictText;
    private String verdictClass;

    public VerdictDTO() {
    }

    public VerdictDTO(Double performanceScore, Double cameraScore, Double batteryScore,
                      Double displayScore, Double valueScore, Double userRatingScore,
                      Double overallScore, String verdictText, String verdictClass) {
        this.performanceScore = performanceScore;
        this.cameraScore = cameraScore;
        this.batteryScore = batteryScore;
        this.displayScore = displayScore;
        this.valueScore = valueScore;
        this.userRatingScore = userRatingScore;
        this.overallScore = overallScore;
        this.verdictText = verdictText;
        this.verdictClass = verdictClass;
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

    public Double getUserRatingScore() {
        return userRatingScore;
    }

    public void setUserRatingScore(Double userRatingScore) {
        this.userRatingScore = userRatingScore;
    }

    public Double getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(Double overallScore) {
        this.overallScore = overallScore;
    }

    public String getVerdictText() {
        return verdictText;
    }

    public void setVerdictText(String verdictText) {
        this.verdictText = verdictText;
    }

    public String getVerdictClass() {
        return verdictClass;
    }

    public void setVerdictClass(String verdictClass) {
        this.verdictClass = verdictClass;
    }
}
