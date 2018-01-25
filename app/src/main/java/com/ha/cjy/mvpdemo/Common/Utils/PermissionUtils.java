package com.ha.cjy.mvpdemo.Common.Utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

/**
 * 权限工具类
 * Created by cjy on 18/1/24.
 */

public class PermissionUtils {
    //相机权限检测请求码
    public final static String CAMERA = Manifest.permission.CAMERA;
    //存储卡
    public final static String SDCARD = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    //拨打电话权限
    public final static String PHOTO = Manifest.permission.CALL_PHONE;

    public static boolean check(String permission) {
        if (Build.VERSION.SDK_INT < 23) return true;
        //是否拥有权限
        if (ActivityCompat.checkSelfPermission(AppUtils.getContext(), permission) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        return true;
    }

    public static void request(Activity activity, int requestCode, String... permission) {
        //弹出对话框接收权限
        ActivityCompat.requestPermissions(activity, permission, requestCode);
    }
}
