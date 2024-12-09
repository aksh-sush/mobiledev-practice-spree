package com.example.weather_app_trial1;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import com.example.weather_app_trial1.databinding.ActivityFirstPgBinding;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class first_pg extends AppCompatActivity {
    private MapView mapView;
    private ActivityFirstPgBinding binding;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // View binding initialization
        binding = ActivityFirstPgBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configure osmdroid settings
        Configuration.getInstance().setUserAgentValue(getPackageName());

        // Initialize MapView
        mapView = binding.mapView;
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setMultiTouchControls(true);

        // Get latitude and longitude from the intent
        Intent intent = getIntent();
        double latitude = intent.getDoubleExtra("latitude", 0.0);
        double longitude = intent.getDoubleExtra("longitude", 0.0);
        Log.d("FirstPg", "Latitude: " + latitude + ", Longitude: " + longitude);

        // Update the map with the location
        updateMap(latitude, longitude);
        fetchWeatherDataAsync(latitude, longitude);



    }


    private void updateMap(double latitude, double longitude) {
        if (mapView != null) {
            // Create a GeoPoint for the current location
            GeoPoint currentLocation = new GeoPoint(latitude, longitude);

            // Center and zoom the map
            mapView.getController().setZoom(15.0);
            mapView.getController().setCenter(currentLocation);

            // Add a marker at the location
            Marker locationMarker = new Marker(mapView);
            locationMarker.setPosition(currentLocation);
            locationMarker.setTitle("Current Location");
            mapView.getOverlays().add(locationMarker);
        }
    }


    private void fetchWeatherDataAsync(double latitude, double longitude) {
        executorService.execute(() -> {
            try {
                // Initialize Retrofit
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.openweathermap.org/data/2.5/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                weatherinterface apiInterface = retrofit.create(weatherinterface.class);

                // Make API call
                Call<Weatherdat> call = apiInterface.getWeatherData(
                        latitude,
                        longitude,
                        "53fcecdecf2d714aca6603235a693d6e",
                        "metric"
                );

                Response<Weatherdat> response = call.execute();

                if (response.isSuccessful() && response.body() != null) {
                    Weatherdat weatherData = response.body();

                    // Extract weather details
                    String temperature = String.valueOf(weatherData.getMain().getTemp());
                    String humidity = String.valueOf(weatherData.getMain().getHumidity());

                    // Update the UI on the main thread
                    runOnUiThread(() -> {
                        binding.temp.setText(temperature + "°C");
                        binding.humidity.setText("Humidity: " + humidity + " %");
                    });
                } else {
                    Log.e("FirstPg", "API response failed: " + response.message());
                }
            } catch (Exception e) {
                Log.e("FirstPg", "Error fetching weather data", e);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mapView != null) {
            mapView.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mapView != null) {
            mapView.onPause();
        }
    }
}

