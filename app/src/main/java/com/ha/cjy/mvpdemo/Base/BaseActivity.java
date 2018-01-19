package com.ha.cjy.mvpdemo.Base;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Activity基类
 * -控制加载框的显示与隐藏
 * -交互行为的回调，子类需要重写该方法
 * -返回布局id
 * Created by cjy on 18/1/19.
 */

public abstract class BaseActivity extends AppCompatActivity implements IBaseInterface {
    public BasePresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        initLoadingDialog();
    }

    /**
     * 返回布局id
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 初始化加载框
     */
    private void initLoadingDialog(){
       //TODO
    }

    /**
     * 显示加载框
     */
    @Override
    public void showLoadingDialog(){
        //TODO
    }

    /**
     * 隐藏加载框
     */
    @Override
    public void hideLoadingDialog(){
        //TODO
    }

    /**
     * 交互结果的回调
     * @param msg 消息体，由消息类型和结果返回的数据组成
     */
    @Override
    public void onNetworkResult(Message msg){
        hideLoadingDialog();
        if (msg == null)
            return;
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null)
            mPresenter.destoryView();
        super.onDestroy();
    }

}
