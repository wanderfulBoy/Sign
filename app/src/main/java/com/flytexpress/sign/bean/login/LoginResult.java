package com.flytexpress.sign.bean.login;

import com.flytexpress.sign.bean.base.BaseResult;

/**
 * Created by Administrator on 2017/12/6.
 */

public class LoginResult extends BaseResult {
    /// <summary>
    /// 用户登录名
    /// </summary>
    public String LoginName;

    /// <summary>
    /// 用户名
    /// </summary>
    public String UserName;

    /// <summary>
    /// 用户中文名
    /// </summary>
    public String CnName;

    /// <summary>
    /// 用户英文名
    /// </summary>
    public String EnName;

    /// <summary>
    /// 操作人ID
    /// </summary>
    public String OperatorId;

    /// <summary>
    /// 操作人所在的收货点ID
    /// </summary>
    public String ReceiveStationId;

    /// <summary>
    /// 操作人所在的处理中心ID
    /// </summary>
    public String ProcessCenterId;

    /// <summary>
    /// 公司ID
    /// </summary>
    public String CompanyId;

    public String CurrentUserLocation;

    public String RpsToken;//登录返回的token

}
