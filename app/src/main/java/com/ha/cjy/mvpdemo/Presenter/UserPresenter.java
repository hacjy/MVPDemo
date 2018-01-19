package com.ha.cjy.mvpdemo.Presenter;

import android.os.Message;

import com.ha.cjy.mvpdemo.Base.BasePresenter;
import com.ha.cjy.mvpdemo.Constants.InterfaceConstant;
import com.ha.cjy.mvpdemo.Model.Entity.UserEntity;
import com.ha.cjy.mvpdemo.Model.Manager.DataResponseCallBack;

/**
 * 用户页面的业务逻辑
 * Created by cjy on 18/1/19.
 */

public class UserPresenter extends BasePresenter{

    @Override
    public void getData() {
        mView.showLoadingDialog();

        final Message msg = new Message();
        try {
            getDataManager().getData(Class.forName("com.ha.cjy.mvpdemo.Model.Entity.UserEntity"), new DataResponseCallBack<UserEntity>() {
                @Override
                public void onSuccess(UserEntity data) {
                    msg.what = InterfaceConstant.SUCCESS;
                    msg.obj = data;
                }

                @Override
                public void onFail() {
                    msg.what = InterfaceConstant.FAILURE;
                }

                @Override
                public void onError() {
                    msg.what = InterfaceConstant.ERROR;
                }
            });
            mView.onNetworkResult(msg);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
