package com.kkdev.mdbm.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.kkdev.mdbm.model.Cast;
import com.kkdev.mdbm.model.Movie;
import com.kkdev.mdbm.model.MovieRepository;

import java.util.List;

public class DetailActivityViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;
    public DetailActivityViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository(application);
    }
    public LiveData<List<Cast>> getAllCast(int movie_id){
        return movieRepository.getCastMutableLiveData(movie_id);
    }
}
