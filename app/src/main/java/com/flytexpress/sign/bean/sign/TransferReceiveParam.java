package com.flytexpress.sign.bean.sign;

import com.flytexpress.sign.bean.base.BaseParam;
import com.flytexpress.sign.bean.base.RpsBaseParam;

/**
 * Created by Administrator on 2017/12/28.
 */

public class TransferReceiveParam extends RpsBaseParam {
    public String pkg;

    public float DeliveredWeight;

    public String PostTypeId;
    public String SignNameByte;//数据流

    // / <summary>
    // / 是否校验邮递方式
    // / </summary>
    public boolean IsCheckPostType;

    public String BatchId;// 批次号 2017 1106 tianjia
    public boolean IsMidwayPrompt; // /是否切换批次再签收 属于 是否之前没有转运扫描过/             2017 1106 tianjia
}
