package org.pozvezd.openweatherapp.RestController;

import lombok.RequiredArgsConstructor;
import org.pozvezd.openweatherapp.WeatherService.WeatherResponse.CurrentWeatherResponse;
import org.pozvezd.openweatherapp.WeatherService.WeatherResponse.ForecastWeatherResponse;
import org.pozvezd.openweatherapp.WeatherService.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/{city}")
    public ResponseEntity<CurrentWeatherResponse> getWeather(@PathVariable String city) {
        return ResponseEntity.ok(weatherService.getCurrentWeather(city));
    }

    @GetMapping("/{city}/forecast")
    public ResponseEntity<ForecastWeatherResponse> get5DayForecast(@PathVariable String city) {
        return ResponseEntity.ok(weatherService.get5DayWeather(city));
    }

}
