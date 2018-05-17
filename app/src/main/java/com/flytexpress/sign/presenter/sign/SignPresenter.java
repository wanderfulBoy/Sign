package com.flytexpress.sign.presenter.sign;

import android.app.Activity;
import android.content.Context;

import com.flytexpress.sign.bean.sign.PKGInfoRequest;
import com.flytexpress.sign.bean.sign.TransferParentInfoResult;
import com.flytexpress.sign.bean.sign.TransferReceiveParam;
import com.flytexpress.sign.bean.sign.TransferReceiveResult;
import com.flytexpress.sign.imp.SignPresenterImp;
import com.flytexpress.sign.model.sign.OnSignListener;
import com.flytexpress.sign.model.sign.SignView;
import com.flytexpress.sign.port.sign.SignPort;
import com.flytexpress.sign.util.GsonUtil;

import cn.trinea.android.common.util.ToastUtils;

/**
 * Created by Administrator on 2017/12/27.
 */

public class SignPresenter implements OnSignListener {
    private Context context;
    private SignView signView;
    private SignPort signPort;
    private Activity mActivity;
    private TransferReceiveResult tranReceResult;

    public SignPresenter(Context context, SignView signView, Activity mActivity) {
        this.context = context;
        this.signView = signView;
        this.signPort = new SignPresenterImp();
        this.mActivity = mActivity;
    }

    public void GetPkgInfo(PKGInfoRequest infoRequest) {
        signView.showprogress();
        signPort.requestPKG(infoRequest, this);
    }

    public void transperReceiveRequest(TransferReceiveParam param) {
        signView.showprogress();
        signPort.transferReceiveiveSummit(param, this);
    }


    @Override
    public void getPKGSuccess(TransferParentInfoResult result) {
        signView.getPKGSuccess(result);
    }

    @Override
    public void summitTransferReceiveSuccess(String res) {
        if (res != null) {
            tranReceResult = GsonUtil.getInstance().getServerBean(res,
                    TransferReceiveResult.class);
            signView.summitInfoSuccess(tranReceResult);
        }
    }

    @Override
    public void failed() {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtils.show(context, "请检查网络连接！");
            }
        });
    }
}
