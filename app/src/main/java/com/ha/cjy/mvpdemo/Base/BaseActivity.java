package com.ha.cjy.mvpdemo.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ha.cjy.mvpdemo.Common.Views.LoadingDialog;

/**
 * Activity基类
 * -控制加载框的显示与隐藏
 * -交互行为的回调，子类需要重写该方法
 * -返回布局id
 * Created by cjy on 18/1/19.
 */

public abstract class BaseActivity extends AppCompatActivity implements IBaseInterface {
    public BasePresenter mPresenter;
    private LoadingDialog mLoadingDialog;

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
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(this);
        }
    }

    /**
     * 显示加载框
     */
    @Override
    public void showLoadingDialog(String msg){
        if (mLoadingDialog != null)
            mLoadingDialog.show(msg);
    }

    /**
     * 显示加载框
     */
    @Override
    public void showLoadingDialog(){
        showLoadingDialog("正在加载中");
    }

    /**
     * 隐藏加载框
     */
    @Override
    public void hideLoadingDialog(){
        if (mLoadingDialog != null)
            mLoadingDialog.close();
    }

    /**
     * 交互结果的回调
     * @param data 数据
     */
    public void onSuccess(Object data){
        hideLoadingDialog();
    }

    @Override
    public void onFail(int code, String message) {
        hideLoadingDialog();
        //TODO 失败的提示
    }

    @Override
    protected void onDestroy() {
        if (mLoadingDialog != null) {
            hideLoadingDialog();
            mLoadingDialog = null;
        }
        if (mPresenter != null)
            mPresenter.destoryView();

        super.onDestroy();
    }

}
