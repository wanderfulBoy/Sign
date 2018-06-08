package com.flytexpress.sign.bean.update;

import com.flytexpress.sign.bean.base.BaseBean;

public class Version extends BaseBean {
	public String Version;
	public long ServerUtcMilliseconds;//添加的服务器返回时间戳，用于客户端校对时间
}
