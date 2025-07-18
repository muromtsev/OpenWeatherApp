package org.pozvezd.openweatherapp.WeatherService;

import lombok.RequiredArgsConstructor;
import org.pozvezd.openweatherapp.WeatherService.WeatherResponse.CurrentWeatherResponse;
import org.pozvezd.openweatherapp.WeatherService.WeatherResponse.ForecastWeatherResponse;
import org.pozvezd.openweatherapp.WeatherService.WeatherResponse.GeoOfCityResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
public class WeatherService {

    private final RestTemplate restTemplate;
    @Value("${open-weather.apiKey}")
    private String apiKey;
    GeoOfCityResponse[] geo;

    public GeoOfCityResponse[] getGeoOfCity(String city) {
        String url = String.format("http://api.openweathermap.org/geo/1.0/direct?q=%s&limit=1&appid=%s",
                city, apiKey);

        return restTemplate.getForObject(url, GeoOfCityResponse[].class);
    }

    public CurrentWeatherResponse getCurrentWeather(String city) {

        geo = getGeoOfCity(city);

        if (geo == null || geo.length == 0) {
            return null;
        }

        String url = String.format("https://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&appid=%s&units=metric",
                geo[0].getLat(), geo[0].getLon(), apiKey);

        return restTemplate.getForObject(url, CurrentWeatherResponse.class);
    }

    public ForecastWeatherResponse get5DayWeather(String city) {
        geo = getGeoOfCity(city);

        if (geo == null || geo.length == 0) {
            return null;
        }
        String url = String.format("https://api.openweathermap.org/data/2.5/forecast?lat=%s&lon=%s&appid=%s&units=metric",
                geo[0].getLat(), geo[0].getLon(), apiKey);

        return restTemplate.getForObject(url, ForecastWeatherResponse.class);
    }

}
