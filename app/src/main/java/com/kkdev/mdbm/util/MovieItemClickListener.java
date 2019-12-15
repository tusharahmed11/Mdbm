package com.kkdev.mdbm.util;

import android.widget.ImageView;

import com.kkdev.mdbm.model.Movie;

public interface MovieItemClickListener {
    void onMovieClick(Movie movie, ImageView movieImageView);
}
