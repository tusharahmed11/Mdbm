package com.kkdev.mdbm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.kkdev.mdbm.R;
import com.kkdev.mdbm.model.Slide;

import java.util.List;

public class SliderPagerAdapter extends PagerAdapter {

    private Context mContext;
    private List<Slide> mList;

    public SliderPagerAdapter(Context mContext, List<Slide> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View sliderLayout = inflater.inflate(R.layout.slide_item,null );
        ImageView slideImg = sliderLayout.findViewById(R.id.slider_img);
        TextView slideTxt = sliderLayout.findViewById(R.id.slider_title);
//        slideImg.setImageResource(mList.get(position).getImage());
        String imagePath = "https://image.tmdb.org/t/p/w500"+mList.get(position).getImage();

        Glide.with(sliderLayout.getContext())
                .load(imagePath)
                .placeholder(R.drawable.ic_play_arrow)
                .into(slideImg);
        slideTxt.setText(mList.get(position).getTitle());
        container.addView(sliderLayout);
        return sliderLayout;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
