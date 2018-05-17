package com.flytexpress.sign.model.login;

/**
 * Created by Administrator on 2017/12/6.
 */

public interface OnLoginListener  {
    void OnUserNameError();//用户名错误
    void onPassWordError();//密码错误
    void loginSuccess(String res);//登录成功
    void loginFailed();//登录失败
    void getVersionSuccess(String res);//获取版本号成功
    void getVersionFailed();//获取版本号失败
}
