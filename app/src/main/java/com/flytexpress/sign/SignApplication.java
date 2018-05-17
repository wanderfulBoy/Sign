package com.flytexpress.sign;

import android.app.Activity;
import android.app.Application;
import android.content.res.Configuration;

import com.flytexpress.sign.util.Tools;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/12/13.
 */

public class SignApplication extends Application {//基础的application，程序开始执行的地方
    private static SignApplication application;
    private String RpsToken ;//登录之后产生的token，这里使用不会出问题，即使销毁，也没有影响

    public String getRpsToken() {//全局的获取RPSToken的方法
        if(Tools.isEmpty(RpsToken)){
           return "";
        }
        return RpsToken;
    }

    public void setRpsToken(String rpsToken) {
        RpsToken = rpsToken;
    }

    private ArrayList<Activity> activityList = new ArrayList<>();

    public static SignApplication getInstance() {
        return application;
    }

    /**
     * 添加到ArrayList<Activity>
     *
     * @param activity：Activity
     */
    public void addActivity(Activity activity) {//用于添加activity到list里面
        activityList.add(activity);
    }

    /**
     * 退出所有的Activity
     */
    public void finishAllActivity() {//finish掉所有的activity
        for (Activity activity : activityList) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    @Override
    public void onCreate() {//创建时调用
        super.onCreate();
        application=this;//产生对象
        initCrash();
    }

    private void initCrash() {
        CrashReport.initCrashReport(getApplicationContext(), "fa55483956", false);
//        CrashReport.testJavaCrash();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {//配置改变时，应用程序对象不会被终止和重启。如果应用程序使用到的值需要在配置改变时重新加载，则可以通过重写此方法实现。
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {//内存不足是清除内存
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }
}
