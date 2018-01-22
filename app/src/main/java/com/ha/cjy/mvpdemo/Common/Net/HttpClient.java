package com.ha.cjy.mvpdemo.Common.Net;

import android.os.Message;
import android.util.Log;

import com.ha.cjy.mvpdemo.Base.BaseObserver;
import com.ha.cjy.mvpdemo.Constants.InterfaceConstant;
import com.ha.cjy.mvpdemo.Model.Entity.MovieEntity;
import com.ha.cjy.mvpdemo.Model.HttpResultEntity;
import com.ha.cjy.mvpdemo.Model.Manager.ApiManager;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求
 * Created by cjy on 18/1/22.
 */

public class HttpClient {
    public static final int CONNECT_TIMEOUT = 5;
    private Retrofit mRetrofit;
    private ApiManager mApiManager;


    private static HttpClient mInstance;

    private HttpClient(){
        init();
    }

    public static HttpClient getInstance(){
        if (mInstance == null){
            synchronized (HttpClient.class){
                if (mInstance == null){
                    mInstance = new HttpClient();
                }
            }
        }
        return mInstance;
    }

    private void init(){
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
        //设置日志拦截器
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("Httplog",message);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder.addInterceptor(interceptor);

        mRetrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(InterfaceConstant.BASE_URL)
                .build();
        mApiManager = mRetrofit.create(ApiManager.class);
    }

    public ApiManager getApiManager() {
        return mApiManager;
    }
}
