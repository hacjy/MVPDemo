package com.ha.cjy.mvpdemo.Presenter;


import com.ha.cjy.mvpdemo.Base.BasePresenter;
import com.ha.cjy.mvpdemo.Model.Entity.MovieEntity;
import com.ha.cjy.mvpdemo.Common.Net.OnResultListener;

/**
 * 用户页面的业务逻辑
 * Created by cjy on 18/1/19.
 */

public class UserPresenter extends BasePresenter{

    /**
     * 获取电影列表
     * @param start 页数
     * @param count 每页记录数
     */
    public void getMovieList(int start,int count) {
        mView.showLoadingDialog();
        try {
            getDataManager().getMovieList(String.valueOf(start),String.valueOf(count),
                    new OnResultListener<MovieEntity>() {
                @Override
                public void onSuccess(MovieEntity data) {
                    mView.onSuccess(data);
                }

                @Override
                public void onFail(int code, String message) {
                    mView.onFail(code,message);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
