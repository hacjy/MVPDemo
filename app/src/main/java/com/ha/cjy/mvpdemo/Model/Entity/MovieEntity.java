package com.ha.cjy.mvpdemo.Model.Entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * 电影实体类
 * Created by cjy on 18/1/22.
 */

public class MovieEntity implements Serializable {
    private String movieId;
    private String movieName;

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
}
