package com.ha.cjy.mvpdemo.UI;

import android.app.Application;

import com.ha.cjy.mvpdemo.Common.Utils.AppUtils;

/**
 * Created by cjy on 18/1/24.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AppUtils.init(this);
    }
}
