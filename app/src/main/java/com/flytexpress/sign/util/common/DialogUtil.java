
package com.flytexpress.sign.util.common;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.graphics.Paint;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.flytexpress.sign.R;
import com.flytexpress.sign.view.CustomProgressDialog;


/**
 * 对话框工具类
 * 
 * @author 余勋杰
 */
public final class DialogUtil
{
	
	/**
	 * 只有确定按钮的对话框
	 * 
	 * @param context
	 * @param title
	 * @param message
	 * @param listener
	 * @return
	 */
	public static Dialog showOKDialog( Context context , CharSequence title , CharSequence message ,
			OnClickListener listener )
	{
		try
		{
			CustomDialog dialog = new CustomDialog( context , title , message ,
					context.getString( R.string.ok ) , listener );
			dialog.show( );

			dialog.setCancelable( false );
			return dialog;
		}
		catch ( Throwable t )
		{
			t.printStackTrace( );
		}
		return null;
	}

	/**
	 * 只有确定按钮的对话框
	 *
	 * @param context
	 * @param title
	 * @param message
	 * @param listener
	 * @return
	 */
	public static Dialog showOKDialog( Context context , int title , int message ,
			OnClickListener listener )
	{
		return showOKDialog( context , context.getString( title ) , context.getString( message ) ,
				listener );
	}

	/**
	 * 只有确定按钮的对话框
	 *
	 * @param context
	 * @param title
	 * @param message
	 * @param listener
	 * @return
	 */
	public static CustomDialog showOKDialog( Context context , CharSequence title , CharSequence message ,
			int layoutRes , OnClickListener listener )
	{
		try
		{
			CustomDialog dialog = new CustomDialog( context , title , message , layoutRes ,
					context.getString( R.string.ok ) , listener , "" , null );
			dialog.show( );

			dialog.setCancelable( false );
			return dialog;
		}
		catch ( Throwable t )
		{
			t.printStackTrace( );
		}
		return null;
	}

	public static CustomDialog showBackDialog( Context context , CharSequence title , CharSequence message ,
			int layoutRes , OnClickListener listener )
	{
		try
		{
			CustomDialog dialog = new CustomDialog( context , title , message , layoutRes ,
					context.getString( R.string.back ) , listener , "" , null );
			dialog.show( );

			dialog.setCancelable( false );
			return dialog;
		}
		catch ( Throwable t )
		{
			t.printStackTrace( );
		}
		return null;
	}

	/**
	 * 有两个按钮的对话框
	 *
	 * @param context
	 * @param title
	 * @param message
	 * @param btnStr1
	 * @param btnStr2
	 * @param listener
	 * @return
	 */
	public static Dialog showTowButtonDialog( Context context , CharSequence title ,
			CharSequence message , CharSequence btnStr1 , CharSequence btnStr2 ,
			OnClickListener listener )
	{
		try
		{
			CustomDialog dialog = new CustomDialog( context , title , message , btnStr1 , listener ,
					btnStr2 , null );

			/*
			 * AlertDialog dialog = new AlertDialog.Builder(context).create();
			 * dialog.setIcon(android.R.drawable.ic_dialog_info);
			 * dialog.setTitle(title); dialog.setMessage(message);
			 * dialog.setButton(btnStr1, listener); dialog.setButton2(btnStr2,
			 * listener);
			 */
			dialog.show( );
			return dialog;
		}
		catch ( Throwable t )
		{
			t.printStackTrace( );
		}
		return null;
	}

	/**
	 * 有两个按钮的对话框
	 *
	 * @param context
	 * @param title
	 * @param message
	 * @param btnStr1
	 * @param btnStr2
	 * @param listener1
	 * @return
	 */
	public static Dialog showTowButtonDialog( Context context , CharSequence title ,
			CharSequence message , CharSequence btnStr1 , CharSequence btnStr2 ,
			OnClickListener listener1 , OnClickListener listener2 )
	{
		try
		{
			CustomDialog dialog = new CustomDialog( context , title , message , btnStr1 ,
					listener1 , btnStr2 , listener2 );
			/*
			 * AlertDialog dialog = new AlertDialog.Builder(context).create();
			 * dialog.setIcon(android.R.drawable.ic_dialog_info);
			 * dialog.setTitle(title); dialog.setMessage(message);
			 * dialog.setButton(btnStr1, listener1); dialog.setButton2(btnStr2,
			 * listener2);
			 */
			dialog.show( );
			return dialog;
		}
		catch ( Throwable t )
		{
			t.printStackTrace( );
		}
		return null;
	}

	/**
	 * 有两个按钮的对话框
	 *
	 * @param context
	 * @param title
	 * @param message
	 * @param btnStr1
	 * @param btnStr2
	 * @param listener
	 * @return
	 */
	public static Dialog showTwoButtonDialog( Context context , int title , int message ,
			int btnStr1 , int btnStr2 , OnClickListener listener )
	{
		return showTowButtonDialog( context , context.getString( title ) ,
				context.getString( message ) , context.getString( btnStr1 ) ,
				context.getString( btnStr2 ) , listener );
	}

	/**
	 * 有一个按钮的对话框
	 *
	 * @param context
	 * @param title
	 * @param message
	 * @param btnStr
	 * @param listener
	 * @return
	 */
	public static Dialog showOneButtonDialog( Context context , String title , String message ,
			String btnStr , OnClickListener listener )
	{
		try
		{
			CustomDialog dialog = new CustomDialog( context , title , message , btnStr , listener );
			/*
			 * AlertDialog dialog = new AlertDialog.Builder(context).create();
			 * dialog.setIcon(android.R.drawable.ic_dialog_info);
			 * dialog.setTitle(title); dialog.setMessage(message);
			 * dialog.setButton(btnStr, listener);
			 */
			dialog.show( );
			dialog.setCancelable( false );
			return dialog;
		}
		catch ( Throwable t )
		{
			t.printStackTrace( );
		}
		return null;
	}

	public static Dialog showTwoButtonDialog( Context context , String title , String message ,
			String button1 , String button2 , OnClickListener listerner1 ,
			OnClickListener listener2 )
	{
		try
		{
			CustomDialog dialog = new CustomDialog( context , title , message , button1 ,
					listerner1 , button2 , listener2 );
			dialog.show( );
			return dialog;
		}
		catch ( Exception e )
		{
			e.printStackTrace( );
		}
		return null;
	}

	public static Dialog showTwoButtonDialog( Context context , String title , String message ,
			String button1 , String button2 , OnClickListener listener )
	{
		try
		{
			CustomDialog dialog = new CustomDialog( context , title , message , button1 , listener ,
					button2 , listener );
			/*
			 * AlertDialog dialog = new AlertDialog.Builder(context).create();
			 * dialog.setIcon(android.R.drawable.ic_dialog_info);
			 * dialog.setTitle(title); dialog.setMessage(message);
			 * dialog.setButton(button1, listener); dialog.setButton2(button2,
			 * listener);
			 */
			dialog.show( );
			return dialog;
		}
		catch ( Throwable t )
		{
			t.printStackTrace( );
		}
		return null;
	}

	public static Dialog showOKCancelDialog( Context context , String title , String message ,
			OnClickListener listener )
	{
		return showTwoButtonDialog( context , title , message ,
				context.getString( R.string.cancel ) , context.getString( R.string.ok ) , null ,
				listener );
	}

	/**
	 * 有确定和取消的对话框
	 *
	 * @param context
	 * @param title
	 * @param message
	 * @param listener1
	 * @return
	 */
	public static Dialog showOKCancelDialog( Context context , String title , String message ,
			OnClickListener listener1 , OnClickListener listener2 )
	{
		return showTwoButtonDialog( context , title , message ,
				context.getString( R.string.cancel ) , context.getString( R.string.ok ) ,
				listener2 , listener1 );
	}

	/**
	 * 有确定和取消的对话框
	 *
	 * @param context
	 * @param title
	 * @param message
	 * @param listener1
	 * @return
	 */
	public static Dialog showOKCancelDialog( Context context , int title , int message ,
			OnClickListener listener1 , OnClickListener listener2 )
	{
		return showOKCancelDialog( context , context.getString( title ) ,
				context.getString( message ) , listener1 , listener2 );
	}

	public static Dialog showOKCancelDialog( Context context , int title , int message ,
			OnClickListener listener )
	{
		return showOKCancelDialog( context , context.getString( title ) ,
				context.getString( message ) , listener , null );
	}

	/**
	 * @Description:显示两个按钮上下分布的对话框
	 * @author tanzy
	 * @date 2014-9-28
	 */
	/*public static Dialog showUpDownTwoButtonDialog( Context context , String title ,
			String content , String upText , String downText , OnClickListener upClick ,
			OnClickListener downClick ,  boolean upAble , boolean downAble , boolean cancelable )
	{
		try
		{
			UpDownTwoButtomDialog dialog = new UpDownTwoButtomDialog( context , title ,
					content , upText , downText , upAble , downAble , upClick , downClick );
			dialog.show( );
			dialog.setCancelable( cancelable );
			return dialog;
		}
		catch ( Throwable t )
		{
			t.printStackTrace( );
		}
		return null;
	}*/

	/*public static Dialog showUpDownTwoButtonDialog( Context context , int title , int content ,
			int upText , int downText , OnClickListener upClick , OnClickListener downClick ,
			boolean upAble , boolean downAble , boolean cancelable )
	{
		return showUpDownTwoButtonDialog( context , context.getString( title ) ,
				context.getString( content ) , context.getString( upText ) ,
				context.getString( downText ) , upClick , downClick , upAble , downAble ,
				cancelable );
	}*/

	/**
	 * 滚动条对话框
	 *
	 * @param context
	 * @param title
	 * @param message
	 * @param listener
	 * @return
	 */
	public static Dialog getProgressDialog( Context context , String title , String message ,
			OnCancelListener listener )
	{
		try
		{
			CustomProgressDialog dialog = CustomProgressDialog.createDialog( context );
			dialog.setMessage( message );
			dialog.setOnCancelListener( listener );

			return dialog;
		}
		catch ( Throwable t )
		{
			t.printStackTrace( );
		}
		return null;
	}

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

	/**
	 * 滚动条对话框
	 *
	 * @param context
	 * @param title
	 * @param message
	 * @param listener
	 * @return
	 */
	public static Dialog showProgressDialog( Context context , int title , int message ,
			OnCancelListener listener )
	{
		return showProgressDialog( context , context.getString( title ) ,
				context.getString( message ) , listener );
	}


	/**
	 * 错误消息对话框
	 *
	 * @param context
	 * @param title
	 * @param message
	 * @param listener
	 * @return
	 */
	public static Dialog showErrorDialog( Context context , String title , String message ,
			OnClickListener listener )
	{
		Dialog dialog = showOKDialog( context , title , message , listener );
		return dialog;
	}

	/**
	 * 错误消息对话框
	 *
	 * @param context
	 * @param title
	 * @param message
	 * @param listener
	 * @return
	 */
	public static Dialog showErrorDialog( Context context , int title , int message ,
			OnClickListener listener )
	{
		return showErrorDialog( context , context.getString( title ) ,
				context.getString( message ) , listener );
	}

	/**
	 * 自定义内容的对话框
	 *
	 * @param context
	 * @param title
	 * @param content
	 * @return
	 */
	public static Dialog showCustomDialog( Context context , String title , View content )
	{
		try
		{
			Dialog dialog = new Dialog( context );
			dialog.setTitle( title );
			dialog.setContentView( content );
			dialog.show( );
			return dialog;
		}
		catch ( Throwable t )
		{
			t.printStackTrace( );
		}
		return null;
	}

	/**
	 * 自定义内容的对话框 可控制大小
	 *
	 * @param context
	 * @param title
	 * @param content
	 * @return
	 */
	public static Dialog showDialog( Context context , String title , View content )
	{
		try
		{
			Dialog dialog = new Dialog( context );
			dialog.setTitle( title );
			dialog.setContentView( content );
			Window window = dialog.getWindow( );
			WindowManager manager = ( ( Activity ) context ).getWindowManager( );
			Display d = manager.getDefaultDisplay( ); // 获取屏幕宽、高用
			WindowManager.LayoutParams lp = window.getAttributes( );
			// lp.height = (int) (d.getHeight() * 0.5); // 高度设置为屏幕的0.6
			// lp.width = (int) (d.getWidth()); // 宽度设置为屏幕的0.95
			window.setAttributes( lp );
			dialog.show( );
			return dialog;
		}
		catch ( Throwable t )
		{
			t.printStackTrace( );
		}
		return null;
	}

	/**
	 * 自定义内容的对话框
	 *
	 * @param context
	 * @param title
	 * @param content
	 * @return
	 */
	public static Dialog showCustomDialog( Context context , int title , View content )
	{
		return showCustomDialog( context , context.getString( title ) , content );
	}

	/**
	 * 是否取消上传的对话框
	 *
	 * @param context
	 * @param title
	 * @param message
	 * @param okListener
	 * @return
	 */
	public static Dialog showCancelUploadDialog( Context context , String title , String message ,
			OnClickListener okListener )
	{
		try
		{
			CustomDialog dialog = new CustomDialog( context , title , message ,
					context.getString( R.string.cancel ) , null , context.getString( R.string.ok ) ,
					okListener );
			/*
			 * AlertDialog.Builder builder = new AlertDialog.Builder(context);
			 * builder.setIcon(android.R.drawable.ic_dialog_info);
			 * builder.setTitle(title); builder.setMessage(message);
			 * builder.setPositiveButton(R.string.ok, okListener);
			 * builder.setNegativeButton(R.string.cancel, null);
			 *
			 * AlertDialog dialog = builder.create();
			 */
			dialog.show( );

			return dialog;
		}
		catch ( Throwable t )
		{
			t.printStackTrace( );
		}
		return null;
	}







	public static CustomDialog showCheckDialog( Context context , CharSequence title , CharSequence message ,
			int layoutRes , CharSequence okbtnTitle ,OnClickListener listener, CharSequence cancelbtnTitle )
	{
		try
		{
			CustomDialog dialog = new CustomDialog( context , title , message , layoutRes ,
					 okbtnTitle , listener ,  cancelbtnTitle , null );
			dialog.show( );
			
			dialog.setCancelable( true );
			return dialog;
		}
		catch ( Throwable t )
		{
			t.printStackTrace( );
		}
		return null;
	}
	
	/**
	 * 滚动条对话框 ,6.0新增  加载框统一用
	 * 
	 * @param context
	 * @param message
	 * @return
	 */
	/*public static Dialog showProgressDialog_New( Context context )
	{
		return showProgressDialog_New( context , context.getString( R.string.pull_to_refresh_refreshing_label )  );
	}*/
	
	/**
	 * @Title: showProgressDialog
	 * @Description: 仅获取加载对话框，不直接显示,6.0新增
	 * @param context
	 * @param message
	 * @return Dialog
	 */
	public static Dialog showProgressDialog_New( Context context , String message)
	{
		try
		{
			CustomProgressDialog dialog = CustomProgressDialog.createDialog( context );
			dialog.setMessage( message );
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
