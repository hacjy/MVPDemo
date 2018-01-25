package com.ha.cjy.mvpdemo.Common.Net;

import android.os.Environment;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;

import com.ha.cjy.mvpdemo.Base.BaseObserver;
import com.ha.cjy.mvpdemo.Common.Utils.FileUtils;
import com.ha.cjy.mvpdemo.Common.Utils.GsonUtils;
import com.ha.cjy.mvpdemo.Common.Utils.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
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
    private final static String TAG = "HttpClient";
    /**
     * 超时5秒
     */
    public static final int CONNECT_TIMEOUT = 5;

    /**
     * 正常的请求get/post
     */
    public static final int DEFAULT_TYPE = 0;
    /**
     * 流式下载
     */
    public static final int DOWNLOAD_TYPE = 1;

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
        httpClientBuilder.addNetworkInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                okhttp3.Response orginalResponse = chain.proceed(chain.request());

                return orginalResponse.newBuilder()
                        .body(new ProgressResponseBody(orginalResponse.body(), new ProgressListener() {
                            @Override
                            public void onProgress(long progress, long total, boolean done) {
                                Log.i(TAG, "onProgress: " + "total ---->" + total + "，done ---->" + progress );
                            }
                        }))
                        .build();
            }
        });

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
        request(DEFAULT_TYPE,builder, onResultListener);
    }

    /**
     * post请求
     * @param onResultListener 请求结果回调
     */
    public void post(OnResultListener onResultListener){
        Builder builder = mBuilder;
        mCall = mApiService.requestPost(builder.url,builder.params);
        request(DEFAULT_TYPE,builder, onResultListener);
    }

    /**
     * post请求，提交json数据
     * @param onResultListener 请求结果回调
     */
    public void postJson(OnResultListener onResultListener){
        Builder builder = mBuilder;
        String jsonData = GsonUtils.toJson(builder.params);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"),jsonData);
        mCall = mApiService.requestPostJson(builder.url,body);
        request(DEFAULT_TYPE,builder, onResultListener);
    }

    /**
     * 下载
     * @param onResultListener
     */
    public void download(OnResultListener onResultListener){
        Builder builder = mBuilder;
        mCall = mApiService.download(builder.url,builder.params);
        request(DOWNLOAD_TYPE,builder, onResultListener);
    }

    private void request(final int type, final Builder builder, final OnResultListener onResultListener){
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

                    @Override
                    public void onResult(ResponseBody responseBody) {
                        if (type == DOWNLOAD_TYPE && !TextUtils.isEmpty(builder.fileName)){
                            //下载文件的回调
                            String path = Environment.getExternalStorageDirectory().getPath();
                            FileUtils.saveFile(responseBody.byteStream(), path,builder.fileName);
                        }
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
        /**
         * 下载文件成功后要保存的文件名称
         */
        private String fileName;

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

        public Builder fileName(String fileName){
            this.fileName = fileName;
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
