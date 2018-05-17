package com.flytexpress.sign.presenter.login;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.flytexpress.sign.R;
import com.flytexpress.sign.bean.login.LoginBean;
import com.flytexpress.sign.bean.login.LoginResult;
import com.flytexpress.sign.bean.update.Version;
import com.flytexpress.sign.config.MainConfig;
import com.flytexpress.sign.imp.LoginPresenterImp;
import com.flytexpress.sign.model.login.LoginView;
import com.flytexpress.sign.port.login.LoginPort;
import com.flytexpress.sign.presenter.BasePresenter;

import com.flytexpress.sign.model.login.OnLoginListener;
import com.flytexpress.sign.util.BaseServerBean;
import com.flytexpress.sign.util.GsonUtil;
import com.flytexpress.sign.util.Tools;

import androidkun.com.versionupdatelibrary.entity.VersionUpdateConfig;

/**
 * Created by Administrator on 2017/12/6.
 */

public class LoginPresenter implements OnLoginListener, BasePresenter {
    private LoginView loginView;
    private LoginPort loginPort;
    private Context context;
    private LoginResult logindata;
    private Activity mAcitivity;
    private Version versionData;
    private String nowVersion;

    public LoginPresenter(LoginView loginView, Context context, Activity mActivity) {
        this.loginView = loginView;
        loginPort = new LoginPresenterImp();
        this.context = context;
        this.mAcitivity = mActivity;
    }

    String response;

    public void login(LoginBean loginBean) {
        loginView.showprogress();
        loginPort.login(loginBean, this);
    }

    public void updateVersion(String version,int isVisible) {
        if(isVisible==1){
            loginView.showprogress();
        }
        nowVersion = version;
        loginPort.update(this);
    }


    @Override
    public void OnUserNameError() {
        loginView.showToast("账号信息错误，请检查账号信息");
    }

    @Override
    public void onPassWordError() {
        loginView.showToast("密码信息错误，请重新输入密码");
    }

    @Override
    public void loginSuccess(String res) {
        response = res;
        mAcitivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loginView.hideprogress();
                if (response != "1001") {
                    BaseServerBean Basebean = GsonUtil.getInstance()
                            .getServerBean(response, BaseServerBean.class);
                    if (Basebean != null) {
                        logindata = GsonUtil.getInstance().getServerBean(response,
                                LoginResult.class);
                        if (logindata != null) {

                            if (logindata.Success == true) {
                                loginView.OnSuccesssLogin(logindata);
                            } else {
                                loginView.OnFailed(logindata);
                            }

                        }
                    }
                } else {
                    loginView.failed();
                }
            }
        });
    }

    @Override
    public void loginFailed() {
        loginView.hideprogress();
        mAcitivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loginView.showToast("请检查网络连接！");
            }
        });

    }

    @Override
    public void getVersionSuccess(String res) {
        BaseServerBean Basebean = GsonUtil.getInstance()
                .getServerBean(res, BaseServerBean.class);
        if (Basebean != null) {
            versionData = GsonUtil.getInstance().getServerBean(
                    res, Version.class);
            if (versionData != null && versionData.Version != null) {
                Log.i("TAG", "现在的版本号：" + versionData.Version + "\n安装app的版本号：" + nowVersion);
                mAcitivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (!versionData.Version.equals(nowVersion)) {
                            //需要下载apk了
                            loginView.hideprogress();
                            loginView.showToast("已经检测到新版本号，正在后台下载中！");
                            startVersionUpdate(MainConfig.APKURL+ "/HandApk/FlytExpressSign.apk", "", "");
                        } else {
                            loginView.hideprogress();
                            loginView.showToast("已经是最新版本啦！");
                        }
                    }
                });
            } else {
                loginView.hideprogress();
                loginView.showToast("请检查网络连接！");
            }
        }
    }


    @Override
    public void getVersionFailed() {
        loginView.failed();
    }

    @Override
    public void failed(Object o) {

    }

    /**
     * 自动下载apk接口
     *
     * @param url                      （下载url接口地址）
     * @param savePath（保存在sd卡的路径，可以为空）
     * @param version                  (即将下载的版本号)
     */
    private void startVersionUpdate(String url, String savePath, String version) {
        VersionUpdateConfig.getInstance()//获取配置实例
                    .setContext(context)//设置上下文
                .setDownLoadURL(url)//设置文件下载链接
//              .setNewVersion(version)//设置即将下载的APK的版本号,避免重复下载
//              .setFileSavePath(savePath)//设置文件保存路径（可不设置）
                .setNotificationIconRes(R.mipmap.logo_flytexpress_1)//设置通知图标
                .setNotificationSmallIconRes(R.mipmap.logo_flytexpress_1)//设置通知小图标
                .setNotificationTitle("签生活版本升级啦！")//设置通知标题
                .startDownLoad();//开始下载
    }

    @Override
    public void destroy() {
        loginPort.cancel();
    }
}
