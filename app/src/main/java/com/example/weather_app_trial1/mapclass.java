package com.example.weather_app_trial1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.location.Geocoder;
import android.location.Location;

import androidx.appcompat.app.AppCompatActivity;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class mapclass extends AppCompatActivity {

    private MapView mapView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        setContentView(R.layout.activity_first_pg);
        Configuration.getInstance().setUserAgentValue(getPackageName());




        // Fetch latitude and longitude from Intent (passed from your weather data activity)
        Intent intent = getIntent();
        double latitude = intent.getDoubleExtra("LATITUDE", 13.0843);
        double longitude = intent.getDoubleExtra("LONGITUDE", 80.2705);

        // Find the MapView in the layout
        mapView = findViewById(R.id.mapView);

        // Set the tile source (use OpenStreetMap tiles)
        mapView.setTileSource(TileSourceFactory.MAPNIK);

        // Enable multi-touch controls
        mapView.setMultiTouchControls(true);

       // MainActivity.onLocationChanged(Location location);//First input the name .
        //String nameMo = MainActivity.onLocationChanged(location);//Retrieve the name

        // Set the map view and zoom level to the location
        GeoPoint currentLocation = new GeoPoint(latitude, longitude);
        mapView.getController().setZoom(15.0);
        mapView.getController().setCenter(currentLocation);

        // Add a marker at the current location
        Marker locationMarker = new Marker(mapView);
        locationMarker.setPosition(currentLocation);
        locationMarker.setTitle("Current Location");
        mapView.getOverlays().add(locationMarker);
        System.out.println(latitude+","+longitude);
    }

    // Override onNewIntent to handle Intent data when activity is restarted
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);  // Update the current intent

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
