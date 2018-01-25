package com.ha.cjy.mvpdemo.Model.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * 获取网络电影接口返回
 * Created by cjy on 18/1/24.
 */

public class GetMovieListResultData implements Serializable{
    private List<MovieEntity> movieList;

    public List<MovieEntity> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<MovieEntity> movieList) {
        this.movieList = movieList;
    }
}
