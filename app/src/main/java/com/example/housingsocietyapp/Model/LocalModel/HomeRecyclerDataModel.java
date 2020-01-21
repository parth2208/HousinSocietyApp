package com.example.housingsocietyapp.Model.LocalModel;

import android.graphics.drawable.Drawable;

public class HomeRecyclerDataModel {

    public String title;
    public int banner;
    public String color;

    public HomeRecyclerDataModel(String title, int banner, String color) {
        this.title = title;
        this.banner = banner;
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getBanner() {
        return banner;
    }

    public void setBanner(int banner) {
        this.banner = banner;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
