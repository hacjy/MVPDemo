package com.ha.cjy.mvpdemo.Model;

import com.ha.cjy.mvpdemo.Model.Entity.MovieEntity;

/**
 * json结构
 * Created by cjy on 18/1/22.
 */

public class HttpResultEntity<T> extends MovieEntity {
    private int code;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
