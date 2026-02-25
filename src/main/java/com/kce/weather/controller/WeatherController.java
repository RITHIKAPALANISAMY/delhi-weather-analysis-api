package com.kce.weather.controller;

import com.kce.weather.dto.MonthlyStatsDTO;
import com.kce.weather.model.Weather;
import com.kce.weather.service.WeatherService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService service;

    public WeatherController(WeatherService service) {
        this.service = service;
    }

    // 1️⃣ Get all weather data
    @GetMapping
    public List<Weather> getAll() {
        return service.getAllWeather();
    }

    // 2️⃣ Get weather by exact date
    @GetMapping("/by-date")
    public List<Weather> getByDate(@RequestParam String date) {
        return service.getWeatherByDate(LocalDate.parse(date));
    }

    // 3️⃣ Get weather by month
    @GetMapping("/by-month")
    public List<Weather> getByMonth(@RequestParam int year,
                                    @RequestParam int month) {
        return service.getWeatherByMonth(year, month);
    }

    // 4️⃣ Get monthly temperature statistics
    @GetMapping("/monthly-stats")
    public Map<String, Double> getMonthlyStats(@RequestParam int year,
                                               @RequestParam int month) {
        return service.getMonthlyTemperatureStats(year, month);
    }
    @GetMapping("/yearly-stats")
    public List<MonthlyStatsDTO> getYearlyStats(@RequestParam int year) {
        return service.getYearlyTemperatureStats(year);
    }
}