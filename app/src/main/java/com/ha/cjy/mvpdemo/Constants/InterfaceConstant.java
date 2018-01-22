package com.ha.cjy.mvpdemo.Constants;

/**
 * 接口常量类，一般定义的是规定的常量，通常是不会进行频繁更改的
 * 接口路径定义
 * Created by cjy on 18/1/19.
 */

public class InterfaceConstant {
    /**
     * 完成，成功，失败，错误
     */
    public static final int COMPLETE = 100;
    public static final int SUCCESS = 0;
    public static final int FAILURE = 300;
    public static final int ERROR = 400;

    //请求地址，以豆瓣前250电影列表接口为例
    public static final String BASE_URL = "https://api.douban.com/v2/movie/";

}
