package com.kce.weather.dto;

public class MonthlyStatsDTO {

    private int month;
    private Double maxTemp;
    private Double minTemp;
    private Double medianTemp;

    public MonthlyStatsDTO(int month, Double maxTemp, Double minTemp, Double medianTemp) {
        this.month = month;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.medianTemp = medianTemp;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public Double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(Double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public Double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(Double minTemp) {
        this.minTemp = minTemp;
    }

    public Double getMedianTemp() {
        return medianTemp;
    }

    public void setMedianTemp(Double medianTemp) {
        this.medianTemp = medianTemp;
    }
}