package com.example.weather_app_trial1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

public class vid extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // Ensure the layout is correct

        // Initialize VideoView
        VideoView videoView = findViewById(R.id.idVideoView);  // Ensure this ID exists in your layout

        // Set the video URI (replace with your own video resource in 'res/raw')
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.lostorbinthemountains);
        videoView.setVideoURI(uri);  // Set URI to VideoView

        // Start the video automatically when activity is created
        videoView.start();

        // Transition to MainActivity after video completes
        videoView.setOnCompletionListener(mp -> {
            // After video finishes, navigate to MainActivity
            Intent intent = new Intent(vid.this, MainActivity.class);
            startActivity(intent);
            finish(); // Close the current vid activity
        });
    }
}
