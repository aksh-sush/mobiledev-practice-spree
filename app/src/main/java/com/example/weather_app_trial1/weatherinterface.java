package com.example.weather_app_trial1;

import com.example.weather_app_trial1.Weatherdat;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface weatherinterface {

    @GET("weather")
    Call<Weatherdat> getWeatherData(
            @Query("lat") double lat,      // Latitude
            @Query("lon") double lon,      // Longitude
            @Query("appid") String appid,  // API key
            @Query("units") String units   // Units (metric, imperial, etc.)
    );
}
