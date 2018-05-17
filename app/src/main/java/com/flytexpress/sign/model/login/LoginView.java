package com.flytexpress.sign.model.login;

import com.flytexpress.sign.bean.login.LoginResult;
import com.flytexpress.sign.model.BaseView;

/**
 * Created by Administrator on 2017/12/6.
 */

public interface LoginView extends BaseView {
    String getUserName();//获取用户账号（用户名）信息
    String getPassWord();//获取当前密码信息
    void OnSuccesssLogin(LoginResult result);
    void OnFailed(LoginResult result);
}
