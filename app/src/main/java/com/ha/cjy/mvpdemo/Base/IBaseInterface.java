package com.ha.cjy.mvpdemo.Base;

import android.os.Message;

/**
 * 通用的页面接口
 * -控制加载框的显示与隐藏
 * -交互行为的回调
 * Created by cjy on 18/1/19.
 */

public interface IBaseInterface {
    void showLoadingDialog();
    void hideLoadingDialog();
    void onNetworkResult(Message msg);
}
