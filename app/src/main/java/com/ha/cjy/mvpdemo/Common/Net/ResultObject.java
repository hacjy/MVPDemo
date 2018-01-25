package com.ha.cjy.mvpdemo.Common.Net;

import android.text.TextUtils;
import android.util.Log;

import com.ha.cjy.mvpdemo.Common.Utils.GsonUtils;
import com.ha.cjy.mvpdemo.Model.Entity.MovieEntity;

/**
 * 网络请求返回的json结构
 * Created by cjy on 18/1/22.
 */

public class ResultObject{
    public static final int SUCCESS_CODE = 200;
    public static final int FAILURE_CODE = 400;
    public static final int ERROR_CODE = 500;

    /**
     * 消息码和消息
     */
    private int code;
    private String message;
    /**
     * 数据
     */
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        if (message == null)
            message = "";
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static <T> T getData(String dataJson, Class<T> clazz) {
        try {
            if (TextUtils.isEmpty(dataJson))
                return clazz.newInstance();
            else {
                return (T) GsonUtils.fromJson(dataJson, clazz);
            }
        } catch (Exception e) {
            Log.e("ResultObject", "getData>>", e);
            try {
                return clazz.newInstance();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            return null;
        }
    }

    public static String toJson(Object object){
        String jsonStr = "";
        try {
            jsonStr = GsonUtils.toJson(object);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonStr;
    }
}
