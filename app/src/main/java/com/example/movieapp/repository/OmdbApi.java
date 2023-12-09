package com.example.movieapp.repository;


import com.example.movieapp.model.MoviesSearchResponseDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface OmdbApi {
    @GET(".")
    Call<MoviesSearchResponseDto> getMovies(@Query("s") String search, @Query("apikey") String apiKey);
}
