package com.ha.cjy.mvpdemo.Model.Manager;


import com.ha.cjy.mvpdemo.Common.Net.HttpClient;
import com.ha.cjy.mvpdemo.Common.Net.OnResultListener;
import com.ha.cjy.mvpdemo.Constants.InterfaceConstant;
import com.ha.cjy.mvpdemo.Model.Entity.MovieEntity;
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
    public void getMovieList(String start,String count, final OnResultListener callBack) {
        //请求
        HttpClient client = new HttpClient.Builder()
                .baseUrl(InterfaceConstant.BASE_URL)
                .url("top250")
                .params("start",start)
                .params("count",count)
                .resultClass(MovieEntity.class)
                .build();
        client.get(new OnResultListener<MovieEntity>(){
            @Override
            public void onSuccess(MovieEntity data) {
                callBack.onSuccess(data);
            }

            @Override
            public void onFail(int code, String message) {
                callBack.onFail(code,message);
            }
        });
    }

}
