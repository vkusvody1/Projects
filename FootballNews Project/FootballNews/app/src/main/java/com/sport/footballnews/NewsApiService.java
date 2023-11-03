package com.sport.footballnews;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiService {
    @GET("top-headlines")
    Call<NewsResponse> getFootballNews(
            @Query("q") String query,
            @Query("apiKey") String apiKey
    );
}
