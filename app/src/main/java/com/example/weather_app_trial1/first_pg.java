/*package com.example.weather_app_trial1;

import android.os.Bundle;
import android.view.SurfaceView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.weather_app_trial1.R;
import com.google.android.filament.Model;
import com.google.android.filament.ModelViewer;
import com.google.android.filament.utils.Utils;
import com.google.android.filament.android.FilamentSurfaceView;

import org.tensorflow.lite.schema.Model;

public class first_pg extends AppCompatActivity {

    private SurfaceView surfaceView;
    private ModelViewer modelViewer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_pg);

        // Initialize the SurfaceView
        surfaceView = findViewById(R.id.surface_view);

        // Initialize the ModelViewer using the SurfaceView
        modelViewer = new ModelViewer(this, surfaceView);

        // Load the model from the assets folder
        Model model = loadModel("my_3d_model.gltf"); // Replace with your model's file name
        modelViewer.setModel(model);

        // Set camera position (adjust as needed)
        modelViewer.setCameraPosition(0f, 1f, 3f);

        // Set lighting mode (optional, but helps in rendering)
        modelViewer.setLightingMode(ModelViewer.LightingMode.DEFAULT);
    }

    // Utility method to load the 3D model from assets
    private Model loadModel(String modelPath) {
        // Use Filament's Model utility to load the model from the assets folder
        return Model.loadFromAssets(getAssets(), modelPath);
    }
}
*/