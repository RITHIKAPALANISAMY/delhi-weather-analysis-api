package com.kce.weather.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Document(collection = "weather")
public class Weather {

    @Id
    private String id;

    private LocalDate date;
    private Double temperature;
    private Double humidity;
    private Double pressure;
    private Double heatIndex;
    private String weatherCondition;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Double getTemperature() {
		return temperature;
	}
	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}
	public Double getHumidity() {
		return humidity;
	}
	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}
	public Double getPressure() {
		return pressure;
	}
	public void setPressure(Double pressure) {
		this.pressure = pressure;
	}
	public Double getHeatIndex() {
		return heatIndex;
	}
	public void setHeatIndex(Double heatIndex) {
		this.heatIndex = heatIndex;
	}
	public String getWeatherCondition() {
		return weatherCondition;
	}
	public void setWeatherCondition(String weatherCondition) {
		this.weatherCondition = weatherCondition;
	}

    // Getters and Setters
    
    
}