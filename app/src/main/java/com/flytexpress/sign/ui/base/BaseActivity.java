package com.flytexpress.sign.ui.base;

import android.Manifest;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.flytexpress.sign.R;
import com.flytexpress.sign.util.Status.SystemBarTintManager;

import java.util.List;

import cn.trinea.android.common.util.ToastUtils;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 主要是实现沉浸状态栏和一次性获取权限的问题
 */
public class BaseActivity extends Activity implements EasyPermissions.PermissionCallbacks {
    private SystemBarTintManager tintManager;
    protected static final int NET_FAILURE = 1001;//所有的网络请求失败
    public static final int RC_CAMERA_AND_WIFI = 80001;
    //所要申请的权限
    String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CALL_PHONE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.READ_PHONE_STATE,Manifest.permission.CAMERA};
//  protected static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_base);
        initStatus();
        initAuth();
    }

    private void initAuth() {

        if (EasyPermissions.hasPermissions(this, perms)) {
//            ToastUtils.show(this, "已经获取权限哦！");
        } else {
            //...
            EasyPermissions.requestPermissions(this, "需要获取基础权限，请同意以上操作！",
                    RC_CAMERA_AND_WIFI, perms);
//            ToastUtils.show(this, "你有拒绝的权限哦");
        }
    }

    public void initStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
///           window.setStatusBarColor(Color.TRANSPARENT);
            tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintColor(getResources().getColor(R.color.colorAccent));
//            tintManager.setStatusBarTintDrawable(getResources().getDrawable(R.mipmap.z_common_title_bg));
//            tintManager.setStatusBarAlpha(0);//设置透明色
            tintManager.setStatusBarTintEnabled(true);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0 全透明实现
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);//calculateStatusColor(Color.WHITE, (int) alphaValue)
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        ToastUtils.show(this,"获取权限成功了");
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        ToastUtils.show(this,"获取权限失败了");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //把申请权限的回调交由EasyPermissions处理
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
}
