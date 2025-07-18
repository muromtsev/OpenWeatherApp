package org.pozvezd.openweatherapp.WeatherService.WeatherResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
public class CurrentWeatherResponse {

    @JsonProperty("weather")
    private List<Weather> weather;
    @JsonProperty("main")
    private Main main;
    @JsonProperty("wind")
    private Wind wind;
    private long dt;
    @JsonProperty("sys")
    private Sys sys;

    private static LocalDateTime toLocalDateTime(long timestamp) {
        return LocalDateTime.ofInstant(
                Instant.ofEpochSecond(timestamp),
                ZoneId.systemDefault()
        );
    }

    public static String formatTime(long timestamp, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return toLocalDateTime(timestamp).format(formatter);
    }

    public String getDt() {
        return formatTime(dt, "YYYY-MM-dd HH:mm");
    }

    @Data
    public static class Weather {
        private String main;
        private String description;
    }

    @Data
    public static class Main {
        private double temp;
        private double feels_like;
        private double temp_min;
        private double temp_max;
    }

    @Data
    public static class Wind {
        private double speed;
    }

    @Data
    public static class Sys {
        private long sunrise;
        private long sunset;

        public String getSunrise() {
            return formatTime(sunrise, "HH:mm");
        }

        public String getSunset() {
            return formatTime(sunset, "HH:mm");
        }

    }

}
