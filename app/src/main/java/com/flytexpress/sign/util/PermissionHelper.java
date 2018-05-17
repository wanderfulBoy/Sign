/*
package com.flytexpress.sign.util;

import android.app.Activity;
import android.app.Fragment;

*/
/**
 * Created by Administrator on 2017/12/13.
 *//*


public class PermissionHelper {
    // 1. 传什么参数
    // 1.1. Object Fragment or Activity  1.2. int 请求码   1.3.需要请求的权限  string[]
    private Object mObject;
    private int mRequestCode;
    private String[] mRequestPermission;

    private PermissionHelper(Object object){
        this.mObject = object;
    }

    // 2.已什么的方式传参数
    // 2.1 直接传参数
    public static void  requestPermission(Activity activity, int requestCode, String[] permissions){
        PermissionHelper.with(activity).requestCode(requestCode).
                requestPermission(permissions).request();
    }

    public static void  requestPermission(Fragment fragment, int requestCode, String[] permissions){
        PermissionHelper.with(fragment).requestCode(requestCode).
                requestPermission(permissions).request();
    }

    // 2.2 链式的方式传
    // 传Activity
    public static PermissionHelper with(Activity activity){
        return new PermissionHelper(activity);
    }

    // 传Fragment
    public static PermissionHelper with(Fragment fragment){
        return new PermissionHelper(fragment);
    }

    // 添加一个请求码
    public PermissionHelper requestCode(int requestCode){
        this.mRequestCode = requestCode;
        return this;
    }

    // 添加请求的权限数组
    public PermissionHelper requestPermission(String... permissions){
        this.mRequestPermission = permissions;
        return this;
    }

    */
/**
     * 3.1 真正判断和发起请求权限
     *//*

    public void request() {
        // 3.2 首先判断当前的版本是不是6.0 及以上
        if(!PermissionUtils.isOverMarshmallow()){
            // 3.3 如果不是6.0以上  那么直接执行方法   反射获取执行方法
            // 执行什么方法并不确定 那么我们只能采用注解的方式给方法打一个标记，
            // 然后通过反射去执行。  注解 + 反射  执行Activity里面的callPhone
            PermissionUtils.executeSucceedMethod(mObject,mRequestCode);
            return;
        }
    }
}
*/
