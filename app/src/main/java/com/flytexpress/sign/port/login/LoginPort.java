package com.flytexpress.sign.port.login;

import com.flytexpress.sign.bean.login.LoginBean;

import com.flytexpress.sign.model.login.OnLoginListener;
import com.squareup.okhttp.MediaType;

/**
 * Created by Administrator on 2017/12/6.
 */

public interface LoginPort {
    public final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    void login(LoginBean loginBean, OnLoginListener onLoginListener);
    void cancel();//取消model的操作（例如网络请求，防止内存泄露
    void update(OnLoginListener onLoginListener);//版本更新
}
