package com.flytexpress.sign.imp;

import android.util.Log;

import com.flytexpress.sign.SignApplication;
import com.flytexpress.sign.bean.auth.AuthorizationContract;
import com.flytexpress.sign.bean.login.LoginBean;
import com.flytexpress.sign.config.MainConfig;
import com.flytexpress.sign.port.login.LoginPort;

import com.flytexpress.sign.model.login.OnLoginListener;
import com.flytexpress.sign.util.GsonUtil;
import com.flytexpress.sign.util.Tools;
import com.flytexpress.sign.util.okhttp.OkHttpUtil;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Administrator on 2017/12/6.
 */

public class LoginPresenterImp implements LoginPort {
    public final OnLoginListener listener = null;
    private String res;
    private AuthorizationContract auth=new AuthorizationContract();

    @Override
    public void login(LoginBean loginBean, final OnLoginListener onLoginListener) {
        //具体处理逻辑，跟后台交互,根据onLoginListener反馈
//        Log.i("TAG","开始处理了");
        /*synchronized (Thread.currentThread()) {
            try {
                Thread.currentThread().wait(700);
                onLoginListener.loginFailed();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
        RequestBody body = RequestBody.create(JSON, GsonUtil.getInstance()
                .getStringFromJsonObject(loginBean));
        Log.i("TAG", GsonUtil.getInstance().getStringFromJsonObject(loginBean));
        Request request = new Request.Builder()
                .url(MainConfig.URL + "/api/RpsLogin/Login")
                .addHeader("SecurityToken",Tools.computeSercet(auth, SignApplication.getInstance().getContext())+"")
                .post(body).build();
        OkHttpUtil.enqueue(request, new Callback() {

            @Override
            public void onResponse(Response response) throws IOException {
                // TODO Auto-generated method stub
                res = response.body().string();
                Log.i("TAG", "登录返回结果:" + res);
                if (!Tools.isEmpty(res)) {
                    onLoginListener.loginSuccess(res);
                }
                try {
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Request arg0, IOException arg1) {
                // TODO Auto-generated method stub
                onLoginListener.loginFailed();
                res = "1001";//代表错误，请求网络失败
                Log.i("TAG", "登录错误信息：" + arg1.getMessage());
            }
        });
    }

    @Override
    public void cancel() {//取消网络请求，防止内存泄露

    }

    @Override
    public void update(final OnLoginListener onLoginListener) {//获取当前服务器端保存的版本号
        RequestBody formBody = new FormEncodingBuilder().add("", "").build();
        Request request = new Request.Builder()
                .url(MainConfig.URL + "/api/BasicData/GetVersionOutside").post(formBody).build();
        OkHttpUtil.enqueue(request, new Callback() {

            @Override
            public void onResponse(Response response) throws IOException {
                // TODO Auto-generated method stub
                String res = response.body().string();
                Log.i("TAG", "请求版本号结果" + res);
                if (res != null) {
                    onLoginListener.getVersionSuccess(res);
                } else {
                    onLoginListener.loginFailed();
                }
//				JSONObject arr;

                    /*BaseServerBean Basebean = GsonUtil.getInstance()
                            .getServerBean(res, BaseServerBean.class);
                    if (Basebean != null) {
                        Version data = GsonUtil.getInstance().getServerBean(
                                res, Version.class);
                        if (data != null && data.Version != null) {
                            XLog.i("TAG", "新版本来了" + data.Version);
                            newVersion = data.Version;
                            Message msg = Message.obtain();
                            msg.what = SHOW_DATA;
                            msg.obj = newVersion;
                            handler.sendMessage(msg);
                        }*/

            }


            @Override
            public void onFailure(Request arg0, IOException arg1) {
                // TODO Auto-generated method stub
//				Log.i("TAG", "错误信息："+arg1.getMessage());
                onLoginListener.loginFailed();

            }
        });
    }
}