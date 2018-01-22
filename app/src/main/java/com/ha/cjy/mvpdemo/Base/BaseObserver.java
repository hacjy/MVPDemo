package com.ha.cjy.mvpdemo.Base;

import android.util.Log;

import com.ha.cjy.mvpdemo.Constants.InterfaceConstant;
import com.ha.cjy.mvpdemo.Model.HttpResultEntity;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 对观察者的返回数据的管理
 * Created by cjy on 18/1/22.
 */

public abstract class BaseObserver<T> implements Observer<HttpResultEntity<T>> {


    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(HttpResultEntity<T> value) {
        onSuccess(value.getData());
        if (value == null)
            onFail(-1,"返回空数据");
        int code = value.getCode();
        if (code == InterfaceConstant.SUCCESS){
            onSuccess(value.getData());
        }else{
            onFail(code,value.getMessage());
        }
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void onComplete() {

    }

    public abstract void onSuccess(T data);
    public abstract void onFail(int code,String message);
}
