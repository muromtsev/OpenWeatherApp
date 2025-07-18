package org.pozvezd.openweatherapp.WeatherService.WeatherResponse;

import lombok.Data;

@Data
public class GeoOfCityResponse {

    private String name;
    private String lat;
    private String lon;
    private String country;
    private String state;

}
