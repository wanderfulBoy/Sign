package com.flytexpress.sign.util;


import com.flytexpress.sign.R;
import com.flytexpress.sign.view.CustomProgressDialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;

public class DialogUtilPro {
	/**
	 * @Title: showProgressDialog
	 * @Description: 仅获取加载对话框，且直接显示
	 * @param context
	 * @param title
	 * @param message
	 * @param listener
	 * @return Dialog
	 */
	public static Dialog showProgressDialog( Context context , String title , String message ,
			OnCancelListener listener )
	{
		try
		{
			CustomProgressDialog dialog = CustomProgressDialog.createDialog( context );
			dialog.setMessage( context.getString( R.string.please_wait ) );
			dialog.setOnCancelListener( listener );
			dialog.show( );
			return dialog;
		}
		catch ( Throwable t )
		{
			t.printStackTrace( );
		}
		return null;
	}
}
