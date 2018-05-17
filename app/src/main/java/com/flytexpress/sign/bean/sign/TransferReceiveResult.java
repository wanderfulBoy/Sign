package com.flytexpress.sign.bean.sign;

import com.flytexpress.sign.bean.base.BaseResult;

public class TransferReceiveResult extends BaseResult {
	public String BatchId;

	// / <summary>
	// / 未处理件数
	// / </summary>
	public int UntreatedQuantities;

	// / <summary>
	// / 是否中途提示
	// / </summary>
	public boolean IsMidwayPrompt;

	public String PostTypeId;

}
