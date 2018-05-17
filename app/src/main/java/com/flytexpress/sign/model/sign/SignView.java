package com.flytexpress.sign.model.sign;

import com.flytexpress.sign.bean.sign.TransferParentInfoResult;
import com.flytexpress.sign.bean.sign.TransferReceiveResult;
import com.flytexpress.sign.model.BaseView;

/**
 * Created by Administrator on 2017/12/27.
 */

public interface SignView extends BaseView {
    String getPKG();//获取PKG信息
    void getPKGSuccess(TransferParentInfoResult result);//获取PKG信息成功
    void failed();
    void summitInfoSuccess(TransferReceiveResult result);//签收成功
}
