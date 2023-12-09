package com.example.movieapp.model;

import java.util.List;

public class MoviesSearchResponseDto {
    private List<MovieDto> Search;
    private String totalResults;
    private String Response;
    // Getters and Setters


    public List<MovieDto> getSearch() {
        return Search;
    }

    public void setSearch(List<MovieDto> search) {
        Search = search;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }
}
