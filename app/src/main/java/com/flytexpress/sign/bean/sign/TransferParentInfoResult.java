package com.flytexpress.sign.bean.sign;

import com.flytexpress.sign.bean.base.BaseResult;

/**
 * Created by Administrator on 2017/12/27.
 */

public class TransferParentInfoResult extends BaseResult {

    public TransferParentModel TransferParentModel;
    public String PostTypeName ;

    public class TransferParentModel {
        // / PKG编号
        // / </summary>
        public String Pkg;

        // / <summary>
        // / 始发地ID
        // / </summary>
        public String FromDeliverAddressId;

        // / <summary>
        // / 目的地ID
        // / </summary>
        public String ToDeliverAddressId;

        // / <summary>
        // / 邮递方式ID
        // / </summary>
        public String PostTypeId;

        // / <summary>
        // / 发起重量
        // / </summary>
        public double LaunchWeight;

        // / <summary>
        // / 签收重量
        // / </summary>
        public double DeliveredWeight;

        // / <summary>
        // / 转运数量
        // / </summary>
        public int Quantities;

        // / <summary>
        // / 转运状态 (默认0=未识别)
        // / </summary>
        public int TransferStatus;

        // / <summary>
        // / 操作时间
        // / </summary>
        public String OperateTime;
    }
}
