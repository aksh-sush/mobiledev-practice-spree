package com.example.weather_app_trial1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weather_app_trial1.databinding.ActivityFirstPgBinding;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.ExecutorService;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;


import java.util.concurrent.Executors;
public class first_pg extends AppCompatActivity {
    private MapView mapView;
    private ActivityFirstPgBinding binding; // View binding for first_pg.xml
    private final ExecutorService executorService = Executors.newSingleThreadExecutor(); // Thread pool for async tasks

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFirstPgBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Configure osmdroid settings
        Configuration.getInstance().setUserAgentValue(getPackageName());

        // Initialize MapView
        mapView = findViewById(R.id.mapView);

        // Set the tile source (use OpenStreetMap tiles)
        mapView.setTileSource(TileSourceFactory.MAPNIK);

        // Enable multi-touch controls (zoom, pan)
        mapView.setMultiTouchControls(true);



        Intent intent = getIntent();
        double latitude = intent.getDoubleExtra("latitude", 13.0843);
        double longitude = intent.getDoubleExtra("longitude", 80.2705);


        // Call the async method
        fetchWeatherDataAsync(latitude, longitude);

    }


    // Asynchronous method to fetch weather data
    private void fetchWeatherDataAsync(double latitude, double longitude) {
        executorService.execute(() -> {
            try {
                // Initialize Retrofit
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl("https://api.openweathermap.org/data/2.5/")
                        .build();

                weatherinterface apiInterface = retrofit.create(weatherinterface.class);

                // Execute the API call synchronously
                Call<Weatherdat> call = apiInterface.getWeatherData(
                        latitude,
                        longitude,
                        "53fcecdecf2d714aca6603235a693d6e",
                        "metric"
                );

                Response<Weatherdat> response = call.execute();
                Weatherdat weatherData = null;


                if (response.isSuccessful() && response.body() != null) {
                    weatherData = response.body();
                    String temperature = String.valueOf(weatherData.getMain().getTemp());
                    String feelsLike = String.valueOf(weatherData.getMain().getFeelsLike());
                    String humidity = String.valueOf(weatherData.getMain().getHumidity());


                    // Update the UI on the main thread
                    runOnUiThread(() -> {
                        binding.temp.setText(temperature);

                        binding.humidity.setText("Humidity: " + humidity + " %");

                        // Set up the map with the weather location
                        GeoPoint currentLocation = new GeoPoint(latitude, longitude);
                        mapView.getController().setZoom(15.0); // Zoom level
                        mapView.getController().setCenter(currentLocation); // Center map on current location

                        // Add a marker at the current location
                        Marker locationMarker = new Marker(mapView);
                        locationMarker.setPosition(currentLocation);
                        locationMarker.setTitle("Current Location");
                        mapView.getOverlays().add(locationMarker); // Add marker to map
                    });


                } else {
                    Log.e("TAG", "Failed response: " + response.message());
                }
            } catch (Exception e) {
                Log.e("TAG", "Error fetching weather data", e);
            }
        });
    }
}