package com.example.weather_app_trial1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather_app_trial1.databinding.ActivityFirstPgBinding;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;public class first_pg extends AppCompatActivity {

    private ActivityFirstPgBinding binding; // View binding for first_pg.xml
    private final ExecutorService executorService = Executors.newSingleThreadExecutor(); // Thread pool for async tasks

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFirstPgBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        double latitude = intent.getDoubleExtra("latitude", 0.0);
        double longitude = intent.getDoubleExtra("longitude", 0.0);

        // Call the async method
        fetchWeatherDataAsync(latitude, longitude);

        // Setup RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recycleable);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
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

                        binding.feelsLike.setText("Feels like: " + feelsLike + " °C");
                        binding.humidity.setText("Humidity: " + humidity + " %");

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