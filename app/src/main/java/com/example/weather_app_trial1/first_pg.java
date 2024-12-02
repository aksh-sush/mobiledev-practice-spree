package com.example.weather_app_trial1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather_app_trial1.databinding.ActivityFirstPgBinding;  // Use the correct ViewBinding class for first_pg.xml

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class first_pg extends AppCompatActivity {

    //private android.widget.RecyclerView recyclerView;
    private ActivityFirstPgBinding binding;  // Use the correct ViewBinding class

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFirstPgBinding.inflate(getLayoutInflater());  // Inflate the correct layout
        setContentView(binding.getRoot());  // Set the root view of the layout

        setContentView(R.layout.activity_first_pg); // Ensure the layout is correct
        Intent intent = getIntent();
        double latitude = intent.getDoubleExtra("latitude", 0.0);  // Default value is 0.0
        double longitude = intent.getDoubleExtra("longitude", 0.0); // Default value is 0.0
        fetchWeatherData(latitude, longitude);
        // Setup RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recycleable);  // Make sure the ID matches
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


    }




    // Method to fetch weather data
    private void fetchWeatherData(double latitude, double longitude) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.openweathermap.org/data/2.5/weather?")
                .build();


        weatherinterface apiInterface = retrofit.create(weatherinterface.class);

        Call<Weatherdat> call = apiInterface.getWeatherData(
                latitude,
                longitude,
                "53fcecdecf2d714aca6603235a693d6e", // Your API key
                "metric"     );
        call.enqueue(new Callback<Weatherdat>() {
            @Override
            public void onResponse(Call<Weatherdat> call, Response<Weatherdat> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Weatherdat responseBody = response.body();
                    String temperature = String.valueOf(responseBody.getMain().getTemp());
                    Log.d("TAG", "onResponse: " + temperature);

                    // Assuming 'temp' is the ID of the TextView in first_pg.xml
                    binding.temp.setText(temperature);  // Set the temperature text
                }
            }

            @Override
            public void onFailure(Call<Weatherdat> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.getMessage());
            }
        });
    }
}
