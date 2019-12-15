package com.kkdev.mdbm.model;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.kkdev.mdbm.R;
import com.kkdev.mdbm.service.MovieDataService;
import com.kkdev.mdbm.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MovieRepository  {
    private ArrayList<Movie> movies = new ArrayList<>();
    private ArrayList<Cast> casts = new ArrayList<>();
    private MutableLiveData<List<Movie>> mutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Movie>> recentMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Cast>> castMutableLiveData = new MutableLiveData<>();


    private Application application;

    public MovieRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Movie>> getMutableLiveData() {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                MovieDataService movieDataService = RetrofitInstance.getService();
                Call<MovieDBResponse> call =  movieDataService.getPopularMovies(application.getApplicationContext().getString(R.string.api_key));
                call.enqueue(new Callback<MovieDBResponse>() {
                    @Override
                    public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {
                        MovieDBResponse movieDBResponse = response.body();
                        if (movieDBResponse!= null && movieDBResponse.getMovies() !=null){
                            movies = (ArrayList<Movie>) movieDBResponse.getMovies();
                            mutableLiveData.setValue(movies);
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieDBResponse> call, Throwable t) {

                    }
                });
            }
        });

        return mutableLiveData;
    }

    public MutableLiveData<List<Movie>> getRecentMutableLiveData() {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                MovieDataService movieDataService = RetrofitInstance.getService();
                Call<MovieDBResponse> call = movieDataService.getRecentMovies(application.getApplicationContext().getString(R.string.api_key));
                call.enqueue(new Callback<MovieDBResponse>() {
                    @Override
                    public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {
                        MovieDBResponse movieDBResponse = response.body();
                        if (movieDBResponse!= null && movieDBResponse.getMovies() !=null){
                            movies = (ArrayList<Movie>) movieDBResponse.getMovies();
                            recentMutableLiveData.setValue(movies);
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieDBResponse> call, Throwable t) {

                    }
                });
            }
        });

        return recentMutableLiveData;
    }
    public MutableLiveData<List<Cast>> getCastMutableLiveData(int movie_id) {
        MovieDataService movieDataService = RetrofitInstance.getService();
        Call<CastResponse> castResponseCall = movieDataService.getCast(movie_id,application.getApplicationContext().getString(R.string.api_key));
        castResponseCall.enqueue(new Callback<CastResponse>() {
            @Override
            public void onResponse(Call<CastResponse> call, Response<CastResponse> response) {
                CastResponse castResponse = response.body();
                if (castResponse!=null && castResponse.getCast()!=null){
                    casts = (ArrayList<Cast>) castResponse.getCast();
                    castMutableLiveData.setValue(casts);
                }
            }

            @Override
            public void onFailure(Call<CastResponse> call, Throwable t) {

            }
        });
        return castMutableLiveData;
    }

}
