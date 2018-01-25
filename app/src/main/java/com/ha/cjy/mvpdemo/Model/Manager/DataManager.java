package com.ha.cjy.mvpdemo.Model.Manager;


import com.ha.cjy.mvpdemo.Common.Net.HttpClient;
import com.ha.cjy.mvpdemo.Common.Net.OnResultListener;
import com.ha.cjy.mvpdemo.Constants.InterfaceConstant;
import com.ha.cjy.mvpdemo.Model.Entity.GetMovieListResultData;
/**
 * 数据管理器
 * Created by cjy on 18/1/19.
 */

public class DataManager{
    private static DataManager mInstance;

    private DataManager(){

    }

    public static DataManager getInstance(){
        if (mInstance == null){
            synchronized (DataManager.class){
                if (mInstance == null)
                    mInstance = new DataManager();
            }
        }
        return mInstance;
    }

    /**
     * 获取电影列表
     * @param start 页数
     * @param count 每页记录数
     * @param callBack 回调
     */
    public void getMovieList(int start,int count, final OnResultListener callBack) {
        //请求
        HttpClient client = new HttpClient.Builder()
                .baseUrl(InterfaceConstant.BASE_URL)
                .url("getMovieList")
//                .params("start",String.valueOf(start))
//                .params("count",String.valueOf(count))
                .resultClass(GetMovieListResultData.class)
                .build();
        client.get(new OnResultListener<GetMovieListResultData>(){
            @Override
            public void onSuccess(GetMovieListResultData data) {
                callBack.onSuccess(data);
            }

            @Override
            public void onFail(int code, String message) {
                callBack.onFail(code,message);
            }
        });
    }

    /**
     * 添加电影，post方式
     * @param callBack
     */
    public void addMovie(final OnResultListener callBack){
        //请求
        HttpClient client = new HttpClient.Builder()
                .baseUrl(InterfaceConstant.BASE_URL)
                .url("getMovieList")
                .params("movieId","4")
                .params("movieName","电影《分手大师》")
                .resultClass(GetMovieListResultData.class)
                .build();
        client.postJson(new OnResultListener<GetMovieListResultData>(){
            @Override
            public void onSuccess(GetMovieListResultData data) {
                callBack.onSuccess(data);
            }

            @Override
            public void onFail(int code, String message) {
                callBack.onFail(code,message);
            }
        });
    }

    public void download(final OnResultListener callBack){
        //http://txt.bookben.com/c_down/2018/01/135071/%E6%97%B6%E4%BB%A3%E5%B7%A8%E6%93%98(%E4%B9%A6%E6%9C%AC%E7%BD%91www.bookben.com).txt
        //请求
        HttpClient client = new HttpClient.Builder()
                .baseUrl("http://txt.bookben.com/")
                .url("c_down/2018/01/135071/%E6%97%B6%E4%BB%A3%E5%B7%A8%E6%93%98(%E4%B9%A6%E6%9C%AC%E7%BD%91www.bookben.com).txt")
                .fileName("小说.txt")
                .resultClass(GetMovieListResultData.class)
                .build();
        client.download(new OnResultListener<GetMovieListResultData>(){
            @Override
            public void onSuccess(GetMovieListResultData data) {
                callBack.onSuccess(data);
            }

            @Override
            public void onFail(int code, String message) {
                callBack.onFail(code,message);
            }
        });

    }

}
