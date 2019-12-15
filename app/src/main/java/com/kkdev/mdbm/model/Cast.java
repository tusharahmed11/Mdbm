package com.kkdev.mdbm.model;

import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kkdev.mdbm.BR;
import com.kkdev.mdbm.R;

public class Cast extends BaseObservable {
    @SerializedName("cast_id")
    @Expose
    private Integer castId;
    @SerializedName("character")
    @Expose
    private String character;
    @SerializedName("credit_id")
    @Expose
    private String creditId;
    @SerializedName("gender")
    @Expose
    private Integer gender;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("order")
    @Expose
    private Integer order;
    @SerializedName("profile_path")
    @Expose
    private String profilePath;
    @BindingAdapter("profilePath")
    public static void loadProfileImage(ImageView imageView, String imageURL){
        String imagePath = "https://image.tmdb.org/t/p/w500"+imageURL;
        Glide.with(imageView.getContext())
                .load(imagePath)
                .placeholder(R.drawable.ic_play_arrow)
                .into(imageView);
    }

    @Bindable
    public Integer getCastId() {
        return castId;
    }

    public void setCastId(Integer castId) {
        this.castId = castId;
        notifyPropertyChanged(BR.castId);
    }
    @Bindable
    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
        notifyPropertyChanged(BR.character);
    }

    @Bindable
    public String getCreditId() {
        return creditId;
    }

    public void setCreditId(String creditId) {
        this.creditId = creditId;
        notifyPropertyChanged(BR.creditId);
    }

    @Bindable
    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
        notifyPropertyChanged(BR.gender);
    }

    @Bindable
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
        notifyPropertyChanged(BR.order);
    }

    @Bindable
    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
        notifyPropertyChanged(BR.profilePath);
    }
}
