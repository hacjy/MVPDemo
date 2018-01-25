package com.ha.cjy.mvpdemo.Base;

import com.ha.cjy.mvpdemo.Common.Net.ResultObject;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * 对观察者返回数据的管理
 * Created by cjy on 18/1/22.
 */

public abstract class BaseObserver<T> implements Observer<ResponseBody> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(ResponseBody value) {
        if (value == null)
            onFail(-1,"返回空数据");
        onResult(value);
        try {
            String result = value.string();
            ResultObject object = ResultObject.getData(result,ResultObject.class);
            if (object == null)
                onFail(-1,"返回空数据");
            if (object.getCode() == ResultObject.SUCCESS_CODE) {
                onSuccess(ResultObject.toJson(object.getData()));
            }else{
                onFail(object.getCode(),object.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            onFail(ResultObject.ERROR_CODE,e.getMessage());
        }
    }

    @Override
    public void onError(Throwable throwable) {
        int code = ResultObject.ERROR_CODE;
        String message = "";
        if (throwable instanceof ConnectException
                || throwable instanceof UnknownHostException
                || throwable instanceof SocketTimeoutException){
            message = "网络连接失败，请检查网络";
        }
        onFail(code,message);
    }

    @Override
    public void onComplete() {

    }

    public abstract void onSuccess(String data);
    public abstract void onFail(int code,String message);
    public abstract void onResult(ResponseBody responseBody);
}
