package com.example.weather_app_trial1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class vid extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VideoView videoView = null;
        VideoView finalVideoView = videoView;

        // Set up the VideoView
        videoView = findViewById(R.id.idVideoView);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.lostorbinthemountains);
        videoView.setVideoURI(uri);


        // Transition to MainActivity after video completes
        videoView.setOnCompletionListener(mp -> {
            startActivity(new Intent(vid.this, MainActivity.class));
            finish(); // Close the vid activity
        });
    }
}
