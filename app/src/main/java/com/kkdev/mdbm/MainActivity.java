package com.kkdev.mdbm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.kkdev.mdbm.adapter.HomePopularMovieAdapter;
import com.kkdev.mdbm.adapter.SliderPagerAdapter;
import com.kkdev.mdbm.databinding.ActivityMainBinding;
import com.kkdev.mdbm.model.Movie;
import com.kkdev.mdbm.model.Slide;
import com.kkdev.mdbm.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private ArrayList<Slide> listSlides;
    private MainActivityViewModel mainActivityViewModel;
    private ArrayList<Movie> movies;
    private ArrayList<Movie> popularMovies;
    private ViewPager sliderPager;
    private SliderPagerAdapter sliderPagerAdapter;
    private TabLayout indicator;
    private RecyclerView popularRecycler;
    private HomePopularMovieAdapter homePopularMovieAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        getSlideMovies();
        getPopularMovies();



    }

    private void showSlider() {

        listSlides = new ArrayList<>();
        sliderPager = activityMainBinding.sliderPager;
        int maxItem;
        if (movies!=null){
            for (maxItem=0; maxItem<4; maxItem++){
                listSlides.add(new Slide(movies.get(maxItem).getPosterPath(),movies.get(maxItem).getTitle()));
            }
        }

        sliderPagerAdapter = new SliderPagerAdapter(this,listSlides);
        sliderPager.setAdapter(sliderPagerAdapter);
        indicator = activityMainBinding.indicator;

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MainActivity.SliderTimer(),4000,6000);

        indicator.setupWithViewPager(sliderPager);

    }

    public void getSlideMovies(){
        mainActivityViewModel.getRecentMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> moviesFromLiveData) {
                movies = (ArrayList<Movie>) moviesFromLiveData;
                showSlider();
            }
        });
    }
    class SliderTimer extends TimerTask{

        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (sliderPager.getCurrentItem()<listSlides.size()-1){
                        sliderPager.setCurrentItem(sliderPager.getCurrentItem()+1);
                    }
                    else
                        sliderPager.setCurrentItem(0);
                }
            });
        }
    }
    private void getPopularMovies(){
        mainActivityViewModel.getAllMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> moviesFromLiveData) {
                popularMovies = (ArrayList<Movie>) moviesFromLiveData;
                showPopularRecycler();
            }

        });
    }

    private void showPopularRecycler() {
        popularRecycler = activityMainBinding.rvPopularMovie;
        homePopularMovieAdapter = new HomePopularMovieAdapter(this,popularMovies);
        popularRecycler.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        popularRecycler.setAdapter(homePopularMovieAdapter);
        homePopularMovieAdapter.notifyDataSetChanged();

    }
}
