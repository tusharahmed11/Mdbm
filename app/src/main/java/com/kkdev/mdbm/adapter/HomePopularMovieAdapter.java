package com.kkdev.mdbm.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kkdev.mdbm.R;
import com.kkdev.mdbm.databinding.ItemMovieListBinding;
import com.kkdev.mdbm.model.Movie;
import com.kkdev.mdbm.view.MovieDetailActivity;

import java.util.ArrayList;

public class HomePopularMovieAdapter extends RecyclerView.Adapter<HomePopularMovieAdapter.PopularMovieViewHolder>{
    private Context context;
    private ArrayList<Movie> movieArrayList;

    public HomePopularMovieAdapter(Context context, ArrayList<Movie> movieArrayList) {
        this.context = context;
        this.movieArrayList = movieArrayList;
    }

    @NonNull
    @Override
    public PopularMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMovieListBinding itemMovieListBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_movie_list,parent,false);
        return new PopularMovieViewHolder(itemMovieListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularMovieViewHolder holder, int position) {
        Movie movie = movieArrayList.get(position);
        holder.itemMovieListBinding.setMovie(movie);
    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }

    class PopularMovieViewHolder extends RecyclerView.ViewHolder{
        private ItemMovieListBinding itemMovieListBinding;
        private ImageView moviewImage;
        public PopularMovieViewHolder(@NonNull final ItemMovieListBinding itemMovieListBinding) {
            super(itemMovieListBinding.getRoot());
            this.itemMovieListBinding = itemMovieListBinding;
            moviewImage = itemMovieListBinding.itemMovieImage;
            itemMovieListBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position!=RecyclerView.NO_POSITION){
                        Movie selectedMovie = movieArrayList.get(position);
                        Intent intent = new Intent(context, MovieDetailActivity.class);
                        intent.putExtra("movie",selectedMovie);
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context, (View) moviewImage,"sharedName");

                        context.startActivity(intent,options.toBundle());
                    }
                }
            });
        }
    }

}
