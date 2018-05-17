/*

package com.flytexpress.sign.util.popwindow;


import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

import com.flytexpress.sign.util.CommonFunction;


*/
/**
 * PopupWindow实现的新手引导层
 * 
 * @author huyunfeng E-mail:my519820363@gmail.com
 * 
 *//*

public class HPopupView
{
	public final static int SHOW_TOP = 0;
	public final static int SHOW_CENTER = -1;
	public final static int SHOW_BOTTOM = -2;
	
	private View mContent;
	private int mBkColor;
	private int mWindowWindth;
	private int mWindowHeight;
	private View mGuideView;
	private OnDissmissListener mOnDissmissListener;
	
	*/
/**
	 * 创建一个HPopupGuide实例
	 * 
	 * @param activity
	 * @param guideView
	 *            引导View
	 *//*

	public HPopupView( View guideView , int color )
	{
		mContent = guideView;
		mBkColor = color;
		mWindowWindth = guideView.getResources( ).getDisplayMetrics( ).widthPixels;
		mWindowHeight = guideView.getResources( ).getDisplayMetrics( ).heightPixels;
	}
	
	public void showAtLocation( Activity activity , int top )
	{
		ViewGroup root = ( ( ViewGroup ) activity.getWindow( ).getDecorView( ) );
		if ( top <= SHOW_TOP && top != SHOW_BOTTOM && top != SHOW_CENTER )
		{
			mGuideView = mkView( activity , 0 );
		}
		else if ( top == SHOW_BOTTOM || ( top >= mWindowHeight ) )
		{
			mContent.measure( MeasureSpec.UNSPECIFIED , MeasureSpec.UNSPECIFIED );// 计算mGuideView的高度宽度
			mGuideView = mkView( activity , mWindowHeight - mContent.getMeasuredHeight( ) );
		}
		else if ( top == SHOW_CENTER )
		{
			mContent.measure( MeasureSpec.UNSPECIFIED , MeasureSpec.UNSPECIFIED );// 计算mGuideView的高度宽度
			mGuideView = mkView( activity ,
					( mWindowHeight - mContent.getMeasuredHeight( ) ) / 2 );
		}
		else
		{
			mGuideView = mkView( activity , top );
		}
		
		root.addView( mGuideView );
	}
	
	private View mkView( Context context , int top )
	{
		// 创建一个LinearLayout
		LinearLayout root = new LinearLayout( context );
		root.setLayoutParams( new LayoutParams( mWindowWindth , mWindowHeight ) );
		root.setOrientation( LinearLayout.VERTICAL );
		root.setBackgroundColor( mBkColor );
		root.setGravity( Gravity.CENTER );
		
		View contentv = mContent;
		contentv.setLayoutParams( new LayoutParams( ( int ) ( context.getResources( )
				.getDisplayMetrics( ).widthPixels ) , ( int ) ( context.getResources( )
				.getDisplayMetrics( ).heightPixels )
				- CommonFunction.dipToPx( context ,
						CommonFunction.getStatusBarHeight( context ) ) ) );
		root.addView( contentv );
		
		root.setOnTouchListener( new OnTouchListener( )
		{
			@Override
			public boolean onTouch( View v , MotionEvent event )
			{
				// TODO Auto-generated method stub
				v.setVisibility( View.GONE );
				if ( mOnDissmissListener != null )
				{
					mOnDissmissListener.onDissmiss( );
				}
				return false;
			}
		} );
		
		return root;
	}
	
	public void setOnDissmissListener( OnDissmissListener listener )
	{
		mOnDissmissListener = listener;
	}
	
	public interface OnDissmissListener
	{
		public void onDissmiss();
	}
	
	public boolean isShowing( )
	{
		if ( mGuideView != null )
		{
			if ( mGuideView.getVisibility( ) == View.VISIBLE )
			{
				return true;
			}
		}
		return false;
	}
	
	public void dismiss( )
	{
		if ( mGuideView != null )
		{
			mGuideView.setVisibility( View.GONE );
		}
	}
}
*/
