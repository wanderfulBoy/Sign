package com.flytexpress.sign.imp;

import android.os.Message;
import android.util.Log;

import com.flytexpress.sign.SignApplication;
import com.flytexpress.sign.bean.auth.AuthorizationContract;
import com.flytexpress.sign.bean.sign.PKGInfoRequest;
import com.flytexpress.sign.bean.sign.TransferParentInfoResult;
import com.flytexpress.sign.bean.sign.TransferReceiveParam;
import com.flytexpress.sign.config.MainConfig;
import com.flytexpress.sign.model.sign.OnSignListener;
import com.flytexpress.sign.port.sign.SignPort;
import com.flytexpress.sign.util.GsonUtil;
import com.flytexpress.sign.util.Tools;
import com.flytexpress.sign.util.okhttp.OkHttpClientManager;
import com.flytexpress.sign.util.okhttp.OkHttpUtil;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

import static com.flytexpress.sign.port.login.LoginPort.JSON;

/**
 * Created by Administrator on 2017/12/27.
 */

public class SignPresenterImp implements SignPort {
    private AuthorizationContract auth=new AuthorizationContract();
    @Override
    public void requestPKG(PKGInfoRequest infoRequest, final OnSignListener onSignListener) {
        OkHttpClientManager.postAsyn(MainConfig.URL + "/api/Transfer/GetTransferParentModelAndRoute", new OkHttpClientManager.ResultCallback<TransferParentInfoResult>() {

            @Override
            public void onError(Request request, Exception e) {
                // TODO Auto-generated method stub
                Log.i("TAG","请求PKG接口失败了");
                onSignListener.failed();
            }

            @Override
            public void onResponse(TransferParentInfoResult response) {
                // TODO Auto-generated method stub
                Log.i("TAG","请求PKG接口的response:"+response.Success);
                onSignListener.getPKGSuccess(response);

            }
        }, new OkHttpClientManager.Param[]{
                new OkHttpClientManager.Param("", infoRequest.Pkg),
                new OkHttpClientManager.Param("Pkg", infoRequest.Pkg),
                new OkHttpClientManager.Param("ReceiveStationId",infoRequest.ReceiveStationId),
        });
    }

    @Override
    public void transferReceiveiveSummit(TransferReceiveParam param, final OnSignListener onSignListener) {
        RequestBody body = RequestBody.create(JSON, GsonUtil.getInstance()
                .getStringFromJsonObject(param));
        Log.i("TAG", GsonUtil.getInstance()
                .getStringFromJsonObject(param));
        Request request = new Request.Builder()
                .url(MainConfig.URL + "/api/TransferScanReceive/CustomerSign")
                .addHeader("RpsToken", SignApplication.getInstance().getRpsToken())
                .addHeader("SecurityToken", Tools.computeSercet(auth, SignApplication.getInstance().getContext())+"")
                .post(body)
                .build();
        OkHttpUtil.enqueue(request, new Callback() {

            @Override
            public void onResponse(Response response) throws IOException {
                // TODO Auto-generated method stub
                String res = response.body().string();
                Log.i("TAG","签收结果"+res);
                onSignListener.summitTransferReceiveSuccess(res);
            }

            @Override
            public void onFailure(Request arg0, IOException arg1) {
                // TODO Auto-generated method stub
                onSignListener.failed();//网络请求失败了
            }
        });
    }
}
