package com.ha.cjy.mvpdemo.Presenter;


import com.ha.cjy.mvpdemo.Base.BasePresenter;
import com.ha.cjy.mvpdemo.Model.Entity.MovieEntity;
import com.ha.cjy.mvpdemo.Model.Manager.DataResponseCallBack;

/**
 * 用户页面的业务逻辑
 * Created by cjy on 18/1/19.
 */

public class UserPresenter extends BasePresenter{

    @Override
    public void getData() {
        mView.showLoadingDialog();
        try {
            getDataManager().getData(Class.forName("com.ha.cjy.mvpdemo.Model.Entity.UserEntity"), new DataResponseCallBack<MovieEntity>() {

                @Override
                public void onSuccess(MovieEntity data) {
                    mView.onSuccess(data);
                }

                @Override
                public void onFail(int code, String message) {
                    mView.onFail(code,message);
                }
            });
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
