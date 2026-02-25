package com.kce.weather.repository;

import com.kce.weather.model.Weather;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface WeatherRepository extends MongoRepository<Weather, String> {

    List<Weather> findByDate(LocalDate date);

    List<Weather> findByDateBetween(LocalDate start, LocalDate end);
}