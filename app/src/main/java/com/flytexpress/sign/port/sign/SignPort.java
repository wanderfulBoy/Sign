package com.flytexpress.sign.port.sign;

import com.flytexpress.sign.bean.sign.PKGInfoRequest;
import com.flytexpress.sign.bean.sign.TransferReceiveParam;
import com.flytexpress.sign.model.sign.OnSignListener;

/**
 * Created by Administrator on 2017/12/27.
 */

public interface SignPort {
    void requestPKG(PKGInfoRequest infoRequest, OnSignListener onSignListener);
    void transferReceiveiveSummit(TransferReceiveParam param,OnSignListener onSignListener);
}
