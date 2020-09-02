package com.myapp.myapplication2;

public class nasaInfo {

    public String title;
    public String nasa_id;

    public nasaInfo(String title, String nasa_id)   {
        this.title = title;
        this.nasa_id = nasa_id;
    }

    public String getTitle() {
        return title;
    }

    public String getNasa_id() {
        return nasa_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNasa_id(String nasa_id) {
        this.nasa_id = nasa_id;
    }
}
