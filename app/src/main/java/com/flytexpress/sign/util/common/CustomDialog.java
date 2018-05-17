
package com.flytexpress.sign.util.common;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.flytexpress.sign.R;


/**
 * @ClassName: CustomDialog
 * @Description: 自定义普通Dialog(包含一个按钮或两个按钮)
 * @author zhonglong kylin17@foxmail.com
 * @date 2014-1-6 下午3:16:11
 * 
 */
public class CustomDialog extends Dialog
{
	
	private Context mContext;
	
	private TextView mTitleName;
	private TextView mDialogContent;
	private TextView mBtnPositive;
	private ImageView mBtnDivider;
	private TextView mBtnNegative;
	private View dialogView ;
	
	public CustomDialog(Context context , CharSequence title , CharSequence content ,
                        CharSequence positiveBtnText , View.OnClickListener positiveClick )
	{
		super( context , R.style.NewDialog );
		this.mContext = context;
		initViews( title , content , positiveBtnText , "" );
		setListeners( positiveClick , null );
	}
	
	public CustomDialog(Context context , CharSequence title , CharSequence content ,
                        CharSequence positiveBtnText , View.OnClickListener positiveClick ,
                        CharSequence negativeBtnText , View.OnClickListener negativeClick )
	{
		super( context , R.style.NewDialog );
		this.mContext = context;
		initViews( title , content , positiveBtnText , negativeBtnText );
		setListeners( positiveClick , negativeClick );
	}
	
	public CustomDialog(Context context , CharSequence title , CharSequence content ,
                        int layoutRes, CharSequence positiveBtnText , View.OnClickListener positiveClick ,
                        CharSequence negativeBtnText , View.OnClickListener negativeClick )
	{
		super( context , R.style.NewDialog );
		this.mContext = context;
		initViews( title , content , layoutRes, positiveBtnText , negativeBtnText );
		setListeners( positiveClick , negativeClick );
	}
	
	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
	}
	
	private void initViews( CharSequence title , CharSequence content ,
			CharSequence positiveBtnText , CharSequence negativeBtnText )
	{
		 dialogView = LayoutInflater.from( mContext ).inflate( R.layout.dialog_simple ,
				null );
		mTitleName = ( TextView ) dialogView.findViewById( R.id.dialog_title );
		mTitleName.setText( title );
		mDialogContent = ( TextView ) dialogView.findViewById( R.id.dialog_content );
		mDialogContent.setText( content.toString( ));
		mBtnPositive = ( TextView ) dialogView.findViewById( R.id.dialog_positive );
		mBtnPositive.setText( positiveBtnText );
		mBtnDivider = ( ImageView ) dialogView.findViewById( R.id.dialog_btn_devider );
		mBtnNegative = ( TextView ) dialogView.findViewById( R.id.dialog_negative );
		
		if ( TextUtils.isEmpty( negativeBtnText ) )
		{
			mBtnNegative.setVisibility( View.GONE );
			mBtnDivider.setVisibility( View.GONE );
		}
		else
		{
			mBtnNegative.setText( negativeBtnText );
		}
		
		if ( TextUtils.isEmpty( positiveBtnText ) )
		{
			mBtnPositive.setVisibility( View.GONE );
			mBtnDivider.setVisibility( View.GONE );
		}
		else
		{
			mBtnPositive.setText( positiveBtnText );
		}
		setContentView( dialogView );
		
		reSetDialogSize( );
	}
	
	private void initViews( CharSequence title , CharSequence content , int layoutRes,
			CharSequence positiveBtnText , CharSequence negativeBtnText )
	{
		 dialogView = LayoutInflater.from( mContext ).inflate( layoutRes,
				null );
		mTitleName = ( TextView ) dialogView.findViewById( R.id.dialog_title );
		mTitleName.setText( title );
		mDialogContent = ( TextView ) dialogView.findViewById( R.id.dialog_content );
		mDialogContent.setText( content.toString( ));
		mBtnPositive = ( TextView ) dialogView.findViewById( R.id.dialog_positive );
		mBtnPositive.setText( positiveBtnText );
		mBtnDivider = ( ImageView ) dialogView.findViewById( R.id.dialog_btn_devider );
		mBtnNegative = ( TextView ) dialogView.findViewById( R.id.dialog_negative );

			//其他字体加粗
			TextPaint paint = mBtnNegative.getPaint();
			paint.setFakeBoldText(true); 
		if ( TextUtils.isEmpty( negativeBtnText ) )
		{
			mBtnNegative.setVisibility( View.GONE );
			mBtnDivider.setVisibility( View.GONE );
		}
		else
		{
			mBtnNegative.setText( negativeBtnText );
		}
		
		if ( TextUtils.isEmpty( positiveBtnText ) )
		{
			mBtnPositive.setVisibility( View.GONE );
			mBtnDivider.setVisibility( View.GONE );
		}
		else
		{
			mBtnPositive.setText( positiveBtnText );
		}
		setContentView( dialogView );
		
		reSetDialogSize( );
	}
	
	private void reSetDialogSize( )
	{
		WindowManager m = getWindow( ).getWindowManager( );
		Display d = m.getDefaultDisplay( );
		WindowManager.LayoutParams p = getWindow( ).getAttributes( ); // 获取对话框当前的参数值
		p.height = WindowManager.LayoutParams.WRAP_CONTENT;
		p.width = ( int ) ( d.getWidth( ) * 0.8 );
//		p.gravity = Gravity.RIGHT;//dialog默认居中，设置方向在这里设置
		getWindow( ).setAttributes( p ); // 设置生效
	}
	
	private void setListeners( View.OnClickListener positiveClick ,
			View.OnClickListener negativeClick )
	{
		mBtnPositive.setOnClickListener( new OnDialogBtnClickListener( positiveClick ) );
		mBtnNegative.setOnClickListener( new OnDialogBtnClickListener( negativeClick ) );
	}
	
	class OnDialogBtnClickListener implements View.OnClickListener
	{
		
		private View.OnClickListener mOnClickListener;
		
		public OnDialogBtnClickListener( View.OnClickListener listener )
		{
			this.mOnClickListener = listener;
		}
		
		@Override
		public void onClick( View v )
		{
			if ( this.mOnClickListener != null )
			{
				mOnClickListener.onClick( v );
			}
			dismiss( );
		}
	}
	
	public View getDialogView()
	{
		return dialogView;
	}
	
	/**
	 * @Title: setPositiveButton
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param btnText
	 * @param onclickListener
	 */
	public void setPositiveButton( CharSequence btnText , View.OnClickListener onclickListener )
	{
		mBtnPositive.setText( btnText );
		mBtnPositive.setOnClickListener( new OnDialogBtnClickListener( onclickListener ) );
	}
	
	public void setNegativeButton( CharSequence btnText , View.OnClickListener onclickListener )
	{
		mBtnNegative.setText( btnText );
		mBtnNegative.setOnClickListener( new OnDialogBtnClickListener( onclickListener ) );
	}
}
