package com.ha.cjy.mvpdemo.Common.Net;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 通用的接口请求，包括get/post/流式下载
 * Created by cjy on 18/1/24.
 */

public interface ApiService {

    /**
     * get请求
     * @param url 请求地址，包括参数
     * @return
     */
    @GET
    Observable<ResponseBody> requestGet(@Url String url);

    /**
     * post请求
     * FormUrlEncoded，表示以表单的方式传递键值对，必须要添加的
     * @param url 请求地址
     * @param params 参数
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<ResponseBody> requestPost(@Url String url, @FieldMap Map<String,String> params);

    /**
     * 流式下载
     * Streaming必须添加，避免出现oom
     * @param url 请求地址
     * @param headerParams 头部添加的参数
     * @return
     */
    @Streaming
    @GET
    Observable<ResponseBody> download(@Url String url,@HeaderMap Map<String,String> headerParams);
}
