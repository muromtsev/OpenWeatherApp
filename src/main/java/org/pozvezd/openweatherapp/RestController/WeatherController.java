package org.pozvezd.openweatherapp.RestController;

import lombok.RequiredArgsConstructor;
import org.pozvezd.openweatherapp.WeatherService.WeatherResponse.CurrentWeatherResponse;
import org.pozvezd.openweatherapp.WeatherService.WeatherResponse.ForecastWeatherResponse;
import org.pozvezd.openweatherapp.WeatherService.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/{city}")
    @ResponseBody
    public ResponseEntity<CurrentWeatherResponse> getWeather(@PathVariable String city) {
        return ResponseEntity.ok(weatherService.getCurrentWeather(city));
    }

    @GetMapping("/{city}/forecast")
    @ResponseBody
    public ResponseEntity<ForecastWeatherResponse> get5DayForecast(@PathVariable String city) {
        return ResponseEntity.ok(weatherService.get5DayWeather(city));
    }

    @GetMapping
    public String weatherPage() {
        return "weather";
    }

    @PostMapping
    public String getWeatherForCity(@RequestParam String city, Model model) {
        try {
            CurrentWeatherResponse currentWeatherResponse = weatherService.getCurrentWeather(city);
            model.addAttribute("weather",  currentWeatherResponse);
            model.addAttribute("city",  city);
            model.addAttribute("hasError", false);
        } catch (Exception e) {
            model.addAttribute("hasError", true);
            model.addAttribute("errorMessage", "Не удалось получить данные для города " + city);
            model.addAttribute("city",  city);
        }
        return "weather";
    }

}
