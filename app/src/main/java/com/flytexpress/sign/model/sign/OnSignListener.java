package com.flytexpress.sign.model.sign;

import com.flytexpress.sign.bean.sign.TransferParentInfoResult;

/**
 * Created by Administrator on 2017/12/27.
 */

public interface OnSignListener{
        void getPKGSuccess(TransferParentInfoResult result);//获取PKG成功
        void summitTransferReceiveSuccess(String res);//提交转运信息成功
        void failed();//网络请求失败
}
