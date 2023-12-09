package com.example.movieapp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.movieapp.databinding.MainActivityBinding;
import com.example.movieapp.model.MovieDto;
import com.example.movieapp.model.MoviesSearchResponseDto;
import com.example.movieapp.repository.OmdbApi;
import com.example.movieapp.repository.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private MainActivityBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.searchButton.setOnClickListener(v -> {
            String searchQuery = binding.searchQueryText.getText().toString();
            searchMovie(searchQuery);
        });
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(new MoviesAdapter(new ArrayList<>()));
    }

    private void searchMovie(String searchQuery) {
        OmdbApi service = RetrofitClient.getRetrofitInstance().create(OmdbApi.class);
        Call<MoviesSearchResponseDto> call = service.getMovies(searchQuery, "a2935151");

        call.enqueue(new Callback<MoviesSearchResponseDto>() {
            @Override
            public void onResponse(Call<MoviesSearchResponseDto> call, Response<MoviesSearchResponseDto> response) {
                if (response.isSuccessful()) {
                    MoviesSearchResponseDto movieDto = response.body();

                    List<MovieDto> movies = response.body().getSearch();
                    if (movies != null) {
                        MoviesAdapter adapter = new MoviesAdapter(movies);
                        binding.recyclerView.setAdapter(adapter);
                        Log.d("MainActivity", "Movie: " + movieDto);
                    }

                }
            }

            @Override
            public void onFailure(Call<MoviesSearchResponseDto> call, Throwable t) {
                Log.e("MainActivity", "Error: " + t.getMessage(), t);
            }
        });

    }
}
