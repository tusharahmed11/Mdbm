package com.kkdev.mdbm.service;

import com.kkdev.mdbm.model.CastResponse;
import com.kkdev.mdbm.model.MovieDBResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieDataService {
    @GET("movie/popular")
    Call<MovieDBResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/now_playing")
    Call<MovieDBResponse> getRecentMovies(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Call<MovieDBResponse> getPopularMoviesWithPaging(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("movie/{movie_id}/credits")
    Call<CastResponse> getCast(@Path("movie_id") int id,@Query("api_key") String apiKey);

    @GET("getticket.php")
    Call<String> getTicket(@Query("key") String key,
                         @Query("secret_key") String secretKey,
                         @Query("video_id") String id,
                         @Query("ip") String ip);

    @GET("getvideo")
    Call<Void> getVideo(@Query("key") String key,
                        @Query("video_id") String vId,
                        @Query("ticket") String ticket,
                        @Query("tmdb") int yes);


}
