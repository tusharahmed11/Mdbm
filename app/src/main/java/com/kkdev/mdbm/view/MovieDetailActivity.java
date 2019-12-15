package com.kkdev.mdbm.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.kkdev.mdbm.R;
import com.kkdev.mdbm.adapter.CastAdapter;
import com.kkdev.mdbm.databinding.ActivityMovieDetailBinding;
import com.kkdev.mdbm.model.Cast;
import com.kkdev.mdbm.model.Movie;
import com.kkdev.mdbm.viewmodel.DetailActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailActivity extends AppCompatActivity {
    private Movie movie;
    private ActivityMovieDetailBinding activityMovieDetailBinding;
    private DetailActivityViewModel detailActivityViewModel;
    private ArrayList<Cast> casts;
    private CastAdapter castAdapter;
    private RecyclerView castRecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_movie_detail);
        activityMovieDetailBinding = DataBindingUtil.setContentView(this,R.layout.activity_movie_detail);
        detailActivityViewModel = ViewModelProviders.of(this).get(DetailActivityViewModel.class);

        final Intent intent = getIntent();
        if (intent.hasExtra("movie")) {
            movie = getIntent().getParcelableExtra("movie");
            setUpMovieDetails(movie);
            setUpCastRecycler(movie.getId());
            activityMovieDetailBinding.playFab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = movie.getId();
                    Intent playMovie = new Intent(MovieDetailActivity.this,WebViewActivity.class);
                    playMovie.putExtra("id",id);
                    startActivity(playMovie);
                }
            });

        }
    }

    private void setUpMovieDetails(Movie movie) {
        activityMovieDetailBinding.setMovie(movie);
        getSupportActionBar().setTitle(movie.getTitle());
        activityMovieDetailBinding.movieDetailCover.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));
        activityMovieDetailBinding.playFab.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));
    }

    private void setUpCastRecycler(Integer id) {
        detailActivityViewModel.getAllCast(id).observe(this, new Observer<List<Cast>>() {
            @Override
            public void onChanged(List<Cast> liveDataCasts) {
                casts = (ArrayList<Cast>) liveDataCasts;
                showCastRecycler();
            }
        });
    }

    private void showCastRecycler() {
        castRecycler = activityMovieDetailBinding.rvCast;
        castAdapter = new CastAdapter(this,casts);
        castRecycler.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        castRecycler.setAdapter(castAdapter);
        castAdapter.notifyDataSetChanged();
    }
}
