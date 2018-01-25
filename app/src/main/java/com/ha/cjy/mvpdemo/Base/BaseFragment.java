package com.ha.cjy.mvpdemo.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ha.cjy.mvpdemo.Common.Utils.ToastUtils;
import com.ha.cjy.mvpdemo.Common.Views.LoadingDialog;

/**
 * Fragment基类
 * -控制加载框的显示与隐藏
 * -交互行为的回调，子类需要重写该方法
 * -返回布局id
 * Created by cjy on 18/1/24.
 */

public abstract class BaseFragment extends Fragment implements IBaseInterface {
    public BasePresenter mPresenter;
    private LoadingDialog mLoadingDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(getLayoutId(), null);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
            mLoadingDialog = new LoadingDialog(getActivity());
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
        //失败的提示
        ToastUtils.show(message);
    }

    @Override
    public void onDestroy() {
        if (mLoadingDialog != null) {
            hideLoadingDialog();
            mLoadingDialog = null;
        }
        if (mPresenter != null)
            mPresenter.destoryView();

        super.onDestroy();
    }
}
