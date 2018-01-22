package com.ha.cjy.mvpdemo.Model.Manager;

import android.os.Message;

import com.ha.cjy.mvpdemo.Base.BaseObserver;
import com.ha.cjy.mvpdemo.Common.Net.HttpClient;
import com.ha.cjy.mvpdemo.Common.Net.SchedulerHandler;
import com.ha.cjy.mvpdemo.Constants.InterfaceConstant;
import com.ha.cjy.mvpdemo.Model.Entity.MovieEntity;
import com.ha.cjy.mvpdemo.Model.HttpResultEntity;

import java.util.Map;

/**
 * 数据管理器
 * Created by cjy on 18/1/19.
 */

public class DataManager implements IDataManager{
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

    public void getData(Class cls,DataResponseCallBack callBack) {
        getData(null,cls,callBack);
    }

    @Override
    public void getData(Map<String, Object> params, Class cls, final DataResponseCallBack callBack) {
        //params参数 cls返回的数据model
        if (params != null){

        }
        //请求：从网络、缓存、本地数据库
        HttpClient.getInstance()
                .getApiManager().getMovieList(0,10)
                .compose(new SchedulerHandler().<HttpResultEntity<MovieEntity>>setScheduler())
                .subscribe(new BaseObserver<MovieEntity>() {
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
