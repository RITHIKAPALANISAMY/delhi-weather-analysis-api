package com.kce.weather.service;

import com.kce.weather.dto.MonthlyStatsDTO;
import com.kce.weather.model.Weather;
import com.kce.weather.repository.WeatherRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class WeatherService {

    private final WeatherRepository repository;

    public WeatherService(WeatherRepository repository) {
        this.repository = repository;
    }

    // Get all records
    public List<Weather> getAllWeather() {
        return repository.findAll();
    }

    // Get by exact date
    public List<Weather> getWeatherByDate(LocalDate date) {
        return repository.findByDate(date);
    }

    // Get by month
    public List<Weather> getWeatherByMonth(int year, int month) {

        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate start = yearMonth.atDay(1);
        LocalDate end = yearMonth.atEndOfMonth();

        return repository.findByDateBetween(start, end);
    }

    // Get stats for ONE month
    public Map<String, Double> getMonthlyTemperatureStats(int year, int month) {

        List<Double> temps = getWeatherByMonth(year, month).stream()
                .map(Weather::getTemperature)
                .filter(Objects::nonNull)
                .sorted()
                .collect(Collectors.toList());

        if (temps.isEmpty()) {
            return Collections.emptyMap();
        }

        double min = temps.get(0);
        double max = temps.get(temps.size() - 1);
        double median = calculateMedian(temps);

        Map<String, Double> stats = new HashMap<>();
        stats.put("min", min);
        stats.put("max", max);
        stats.put("median", median);

        return stats;
    }

    // Get stats for ALL 12 months of a year
    public List<MonthlyStatsDTO> getYearlyTemperatureStats(int year) {

        List<MonthlyStatsDTO> result = new ArrayList<>();

        for (Month month : Month.values()) {

            LocalDate start = LocalDate.of(year, month, 1);
            LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

            List<Double> temps = repository.findByDateBetween(start, end)
                    .stream()
                    .map(Weather::getTemperature)
                    .filter(Objects::nonNull)
                    .sorted()
                    .collect(Collectors.toList());

            if (!temps.isEmpty()) {

                double min = temps.get(0);
                double max = temps.get(temps.size() - 1);
                double median = calculateMedian(temps);

                result.add(new MonthlyStatsDTO(
                        month.getValue(),
                        max,
                        min,
                        median
                ));
            }
        }

        return result;
    }

    // Correct median calculation
    private double calculateMedian(List<Double> temps) {

        int size = temps.size();

        if (size % 2 == 0) {
            return (temps.get(size / 2 - 1) + temps.get(size / 2)) / 2;
        } else {
            return temps.get(size / 2);
        }
    }
}