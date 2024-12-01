package com.example.weather_app_trial1;
import java.util.List;

import retrofit2.Call;

import retrofit2.http.GET;

public interface jsonlaceholder<WeatherData> {
    @GET("53fcecdecf2d714aca6603235a693d6e")
    Call<List<WeatherData>> getPost();

    Call<List<WeatherData>> getWeatherData();
}
