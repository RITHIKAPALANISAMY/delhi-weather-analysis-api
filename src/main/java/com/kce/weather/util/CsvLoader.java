package com.kce.weather.util;

import com.kce.weather.model.Weather;
import com.kce.weather.repository.WeatherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class CsvLoader implements CommandLineRunner {

    private final WeatherRepository repository;

    public CsvLoader(WeatherRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {

        // Prevent duplicate loading
        if (repository.count() > 0) {
            return;
        }

        ClassPathResource resource = new ClassPathResource("delhi_weather.csv");

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(resource.getInputStream()))) {

            String line;
            br.readLine(); // Skip header

            // Date format in CSV: 19961101-11:00
            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("yyyyMMdd-HH:mm");

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                Weather weather = new Weather();

                // Column mapping based on your CSV
                weather.setDate(LocalDate.parse(data[0], formatter));     // datetime_utc
                weather.setWeatherCondition(data[1]);                     // _conds
                weather.setHumidity(parseDoubleSafe(data[6]));             // _hum
                weather.setPressure(parseDoubleSafe(data[8]));             // _pressurem
                weather.setTemperature(parseDoubleSafe(data[11]));         // _tempm

                repository.save(weather);
            }
        }

        System.out.println("CSV Data Loaded Successfully ✅");
    }

    // Safe parsing for numeric values (handles empty fields)
    private Double parseDoubleSafe(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        return Double.parseDouble(value.trim());
    }
}