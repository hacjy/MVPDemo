package com.ha.cjy.mvpdemo.Model.Manager;

import com.ha.cjy.mvpdemo.Model.Entity.UserEntity;

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
    public void getData(Map<String, Object> params, Class cls,DataResponseCallBack callBack) {
        //params参数 cls返回的数据model
        if (params != null){

        }
        //请求：从网络、缓存、本地数据库

        //回调
        UserEntity user = new UserEntity();
        user.userName = "用户名：测试";
        callBack.onSuccess(user);
    }
}
