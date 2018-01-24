package com.ha.cjy.mvpdemo.Common.Net;

import android.text.TextUtils;
import android.util.Log;

import com.ha.cjy.mvpdemo.Base.BaseObserver;
import com.ha.cjy.mvpdemo.Common.Utils.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求工具类
 * Created by cjy on 18/1/22.
 */

public class HttpClient {
    /**
     * 超时5秒
     */
    public static final int CONNECT_TIMEOUT = 5;
    /**
     * 地址
     */
    public static String BASE_URL = "";

    private Retrofit mRetrofit;
    private ApiService mApiService;
    private static HttpClient mInstance;
    private Observable<ResponseBody> mCall;
    private Builder mBuilder;
    private String mBaseUrl = "";

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
        mBaseUrl = BASE_URL;

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
        //设置日志拦截器
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            /**
             * 重写log方法，才会打印出日志
             * @param message
             */
            @Override
            public void log(String message) {
                Log.i("HttpLog",message);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder.addInterceptor(interceptor);

        mRetrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(mBaseUrl)
                .build();

        mApiService = mRetrofit.create(ApiService.class);
    }

    public Builder getBuilder() {
        return mBuilder;
    }

    public void setBuilder(Builder mBuilder) {
        this.mBuilder = mBuilder;
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    public ApiService getApiService() {
        return mApiService;
    }

    /**
     * get请求
     * @param onResultListener 请求结果回调
     */
    public void get(final OnResultListener onResultListener) {
        Builder builder = mBuilder;
        if (!builder.params.isEmpty()) {
            String value = "";
            for (Map.Entry<String, String> entry : builder.params.entrySet()) {
                String mapKey = entry.getKey();
                String mapValue = entry.getValue();
                String span = value.equals("") ? "" : "&";
                String part = StringUtils.buffer(span, mapKey, "=", mapValue);
                value = StringUtils.buffer(value, part);
            }
            builder.url(StringUtils.buffer(builder.url, "?", value));
        }
        mCall = mApiService.requestGet(builder.url);
        request(builder, onResultListener);
    }

    /**
     * post请求
     * @param onResultListener 请求结果回调
     */
    public void post(OnResultListener onResultListener){
        Builder builder = mBuilder;
        mCall = mApiService.requestPost(builder.url,builder.params);
        request(builder, onResultListener);
    }

    private void request(final Builder builder, final OnResultListener onResultListener){
        mCall.compose(new SchedulerHandler().<ResponseBody>setScheduler())
                .subscribe(new BaseObserver<ResponseBody>() {
                    @Override
                    public void onSuccess(String result) {
                        if (builder.resultClass == null)
                            throw new UnsupportedOperationException("Builder resultClass can not empty，please set resultClass");
                        onResultListener.onSuccess(ResultObject.getData(result,builder.resultClass));
                    }

                    @Override
                    public void onFail(int code, String message) {
                        onResultListener.onFail(code,message);
                    }
                });
    }


    /**
     * 使用建造者模式设置属性
     */
    public static class Builder{
        /**
         * 请求参数
         */
        private Map<String,String> params = new HashMap<String,String>();
        /**
         * 基本地址，如http://127.0.0.1
         */
        private String baseUrl = "";
        /**
         * 接口地址，如mobile/login
         */
        private String url = "";
        /**
         * 返回结果data数据的结构,如返回用户信息，UserModel.class
         */
        private Class resultClass;

        public Builder() {
        }

        public Builder baseUrl(String baseUrl){
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder url(String url){
            this.url = url;
            return this;
        }

        public Builder params(String key,String value){
            this.params.put(key,value);
            return this;
        }

        public Builder resultClass(Class resultClass){
            this.resultClass = resultClass;
            return this;
        }

        public HttpClient build(){
            if (!TextUtils.isEmpty(baseUrl)){
                BASE_URL = baseUrl;
            }
            HttpClient client = HttpClient.getInstance();
            client.setBuilder(this);
            return client;
        }
    }


}
