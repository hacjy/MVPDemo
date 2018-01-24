package com.ha.cjy.mvpdemo.Common.Net;

import android.os.Message;

/**
 * 请求返回结果的回调
 * Created by cjy on 18/1/19.
 */

public interface OnResultListener<T> {
    void onSuccess(T data);
    void onFail(int code, String message);
}
