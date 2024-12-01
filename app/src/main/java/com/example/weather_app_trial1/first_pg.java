package com.example.weather_app_trial1;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.List;

public class first_pg extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_pg); // Ensure this layout corresponds to FirstPgActivity

        // Setup RecyclerView
        recyclerView = findViewById(R.id.recycleable);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Setup Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonlaceholder jsonPlaceholder = retrofit.create(jsonlaceholder.class);
        Call<List<post>> call = jsonPlaceholder.getPost();
        call.enqueue(new Callback<List<post>>() {
            @Override
            public void onResponse(Call<List<post>> call, Response<List<post>> response) {
                if (response.isSuccessful()) {
                    List<post> postList = response.body();
                    postadapter postAdapter = new postadapter(first_pg.this, postList) {
                        @Override
                        public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
                            post post = postList.get(position);
                            holder.id.setText(post.getId());
                            holder.userid.setText(post.getUserId());
                            holder.title.setText(post.getTitle());
                            holder.bdy.setText(post.getBody());
                        }
                    };
                    recyclerView.setAdapter(postAdapter);
                } else {
                    Toast.makeText(first_pg.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<post>> call, Throwable throwable) {
                Toast.makeText(first_pg.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
