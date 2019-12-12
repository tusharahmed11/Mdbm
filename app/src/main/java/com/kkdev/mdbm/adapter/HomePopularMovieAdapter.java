package com.kkdev.mdbm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kkdev.mdbm.R;
import com.kkdev.mdbm.databinding.ItemMovieListBinding;
import com.kkdev.mdbm.model.Movie;

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
        public PopularMovieViewHolder(@NonNull ItemMovieListBinding itemMovieListBinding) {
            super(itemMovieListBinding.getRoot());
            this.itemMovieListBinding = itemMovieListBinding;
        }
    }

}
