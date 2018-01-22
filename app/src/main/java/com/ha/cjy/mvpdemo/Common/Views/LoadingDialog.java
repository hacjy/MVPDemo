package com.ha.cjy.mvpdemo.Common.Views;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ha.cjy.mvpdemo.R;

/**
 * 进度加载框
 * Created by cjy on 18/1/22.
 */

public class LoadingDialog {
    private LVCircularRing mLoadingView;
    private Dialog mLoadingDialog;
    private TextView mLoadingText;

    public LoadingDialog(Context context) {
        // 首先得到整个View
        View view = LayoutInflater.from(context).inflate(
                R.layout.loading_dialog_view, null);
        // 获取整个布局
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.dialog_view);
        // 页面中的LoadingView
        mLoadingView = (LVCircularRing) view.findViewById(R.id.lv_circularring);
        // 页面中显示文本
        mLoadingText = (TextView) view.findViewById(R.id.loading_text);

        // 创建自定义样式的Dialog
        if (mLoadingDialog == null) {
            mLoadingDialog = new Dialog(context, R.style.loading_dialog);
        }
        // 设置返回键无效
        mLoadingDialog.setCancelable(false);
        mLoadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
    }

    public void show(String msg){
        // 显示文本
        mLoadingText.setText(msg);
        show();
    }

    private void show(){
        if (mLoadingDialog != null) {
            mLoadingDialog.show();
            mLoadingView.startAnim();
        }
    }

    public void close(){
        if (mLoadingDialog!=null) {
            mLoadingView.stopAnim();
            mLoadingDialog.dismiss();
        }
    }
}

