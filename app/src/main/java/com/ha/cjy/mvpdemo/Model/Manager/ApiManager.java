package com.ha.cjy.mvpdemo.Model.Manager;

import com.ha.cjy.mvpdemo.Model.HttpResultEntity;
import com.ha.cjy.mvpdemo.Model.Entity.MovieEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 接口请求管理
 * Created by cjy on 18/1/22.
 */

public interface ApiManager {

    //请求豆瓣前250条电影信息
    @GET("top250")
    Observable<HttpResultEntity<MovieEntity>> getMovieList(@Query("start") int start,@Query("count") int count);
}
