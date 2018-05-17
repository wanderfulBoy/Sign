/*

package com.flytexpress.sign.util;


import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Dialog;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StatFs;
import android.os.Vibrator;
import android.provider.Contacts.People;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.Settings;
import android.text.ClipboardManager;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//import net.iaround.ui.map.AMapLocationUtil;

*/
/**
 * 公用的函数类
 * 
 * @author linyg
 * 
 *//*

@SuppressWarnings ( "deprecation" )
public class CommonFunction
{
	public static final int MSG_TYPE_CHAT_ROOM = 0;
	public static final int MSG_TYPE_PRIVATE_CHAT = 1;
	// public static final int MSG_TYPE_PRIVATE_MSG = 2;
	public static final int MSG_TYPE_COMMENT = 2;
	public static final int MSG_TYPE_NOTICE = 3;
	public static final int PICK_PHOTO_REQ = 0xff11;// 选择照片码
	public static final int TAKE_PHOTO_REQ = 0xff12;// 拍摄照片的请求码
	public static final int BEAUTIFY_PHOTO_REQ = 0xff13;//美化照片的请求码
	public static boolean uiRunning = false; // 标记程序是否已经进入UI运行模式（false表示处于后台service）
	private static FileWriter fw;
	private static Vibrator vibrator;
	private static MediaPlayer mediaPlayer; // mediaPlayer只用于播放来消息提示
	private static Process logcatProcess;
	private static float density;
	
	// 渠道包id
	private static String packageID;
	// 获取库Phone表字段
	private final static String[ ] PHONES_PROJECTION = new String[ ]
		{ Phone.DISPLAY_NAME , Phone.NUMBER };
	// 联系人显示名称
	private final static int PHONES_DISPLAY_NAME_INDEX = 0;
	// 电话号码
	private final static int PHONES_NUMBER_INDEX = 1;
	private static String phoneNumber , contactName;
	static LinkedHashMap< String , String > entity = new LinkedHashMap< String , String >( );
	private static String content;
	
	public static void log( Throwable t )
	{
		if ( Config.DEBUG && t != null )
		{
			t.printStackTrace( );
			// writeLog(t.toString());
			// StackTraceElement[] stack = t.getStackTrace();
			// for (StackTraceElement element : stack) {
			// writeLog(element.toString());
			// }
		}
	}
	
	*/
/**
	 * 5.1版本用于测试网络连接失败情况 写文件，tag为 iAround_new_work_error
	 * 
	 * @param msg
	 *//*

	public static void NetWorkErrorLog( Object... msg )
	{
		if ( Config.DEBUG && msg != null )
		{
			StringBuilder sb = new StringBuilder( );
			int i = 0;
			for ( Object o : msg )
			{
				if ( i > 0 )
				{
					sb.append( ',' );
				}
				sb.append( o == null ? "null" : o.toString( ) );
				i++;
			}
			
			int logStrLength = sb.length( );
			int maxLogSize = 1000;
			for ( i = 0 ; i <= logStrLength / maxLogSize ; i++ )
			{
				int start = i * maxLogSize;
				int end = ( i + 1 ) * maxLogSize;
				end = end > logStrLength ? logStrLength : end;
				Log.v( "iAround_new_work_error" , sb.substring( start , end ) );
			}
			
			writeLog( msg.toString( ) );
		}
	}
	
	public static String StackTraceLog2String( StackTraceElement[ ] items )
	{
		String result = "";
		for ( StackTraceElement item : items )
		{
			result += item.toString( ) + "/n";
		}
		return result;
	}
	
	*/
/** 打印log *//*

	public static void log( String tag , Object... msg )
	{
		if ( Config.DEBUG && msg != null )
		{
			StringBuilder sb = new StringBuilder( );
			int i = 0;
			for ( Object o : msg )
			{
				if ( i > 0 )
				{
					sb.append( ',' );
				}
				sb.append( o == null ? "null" : o.toString( ) );
				i++;
			}
			
			int logStrLength = sb.length( );
			int maxLogSize = 1000;
			for ( i = 0 ; i <= logStrLength / maxLogSize ; i++ )
			{
				int start = i * maxLogSize;
				int end = ( i + 1 ) * maxLogSize;
				end = end > logStrLength ? logStrLength : end;
				if ( tag.equals( "sherlock" ) )
				{
					Log.i( tag , sb.substring( start , end ) );
				}
				else
				{
					Log.v( "iAround_" + tag , sb.substring( start , end ) );
				}
			}
		}
	}
	
	public static void log( Object... msg )
	{
		log( "System.out" , msg );
	}
	
	*/
/**
	 * 通用性手机号是否正确
	 * 
	 * @param String
	 *            手机号
	 * @return boolean 是否正确
	 *//*

	public static boolean isPhoneNumberValid( String phoneNumber )
	{
		boolean isValid = false;
		String expression = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{5})$";
		String expression2 = "^\\(?(\\d{3})\\)?[- ]?(\\d{4})[- ]?(\\d{4})$";
		CharSequence inputStr = phoneNumber;
		Pattern pattern = Pattern.compile( expression );
		Matcher matcher = pattern.matcher( inputStr );
		Pattern pattern2 = Pattern.compile( expression2 );
		Matcher matcher2 = pattern2.matcher( inputStr );
		if ( matcher.matches( ) || matcher2.matches( ) )
		{
			isValid = true;
		}
		return isValid;
	}
	
	*/
/**
	 * 隐藏软键盘
	 * 
	 * @param context
	 * @param view
	 *//*

	public static void hideInputMethod( Context context , View view )
	{
		InputMethodManager imm = ( InputMethodManager ) context
				.getSystemService( Context.INPUT_METHOD_SERVICE );
		if ( imm != null )
		{
			imm.hideSoftInputFromWindow( view.getWindowToken( ) , 0 );
		}
	}
	
	*/
/**
	 * 显示软键盘
	 * 
	 * @param context
	 * @param view
	 *//*

	public static void showInputMethodForQuery( Context context , View view )
	{
		InputMethodManager imm = ( InputMethodManager ) context
				.getSystemService( Context.INPUT_METHOD_SERVICE );
		if ( imm != null )
		{
			imm.showSoftInput( view , 0 );
		}
	}
	
	*/
/**
	 * 计算个性化距离显示
	 * 
	 * @param distance
	 *            距离
	 * @return String 显示内容
	 *//*

	public static String covertSelfDistance( int distance )
	{
		String str = null;
		if ( distance > 1000 )
		{
			double d_distance = distance / 1000d;
			DecimalFormat df = new DecimalFormat( "0.00" );
			String result = df.format( d_distance );
			str = result + "km";
		}
		else
		{
			// DecimalFormat df = new DecimalFormat("0.00");
			// String result = df.format(d_distance);
			// str = d_distance + "m";
			str = distance + "m";
		}
		return str;
	}
	
	*/
/**
	 * 计算个性化距离显示
	 * 
	 * @param distance
	 *            距离
	 * @return String 显示内容
	 *//*

	public static String covertSelfDistance( double distance )
	{
		String str = null;
		if ( distance > 1000 )
		{
			double d_distance = distance / 1000d;
			DecimalFormat df = new DecimalFormat( "0.00" );
			String result = df.format( d_distance );
			str = result + "km";
		}
		else
		{
			// DecimalFormat df = new DecimalFormat("0.00");
			// String result = df.format(d_distance);
			// str = d_distance + "m";
			str = distance + "m";
		}
		return str;
	}
	
	*/
/**
	 * 聊天模块的时间显示格式
	 * 
	 * @param activity
	 * @param serverTime
	 *            服务端下发的时间毫秒, 本地就传0
	 * @return
	 * @time 2011-6-23 下午02:33:42
	 * @author:linyg
	 *//*

	public static String covertToChatTime( SuperActivity activity , long serverTime )
	{
		String strTime = "";
		long time = Common.getInstance( ).serverToClientTime + serverTime;
		Date serverDate = new Date( time );
		Date today = new Date( );
		long todaytime = today.getTime( ) + Common.getInstance( ).serverToClientTime;
		boolean isToday = TimeFormat.IsAfterTodayTime( time );
		if ( isToday )
		{
			SimpleDateFormat sf = new SimpleDateFormat( "HH:mm:ss" );
			long cTime = ( todaytime - time ) / 1000;
			if ( cTime < 86400 )
			{
				// if (today.getDate() == serverDate.getDate()) {
				sf = new SimpleDateFormat( "HH:mm:ss" );
				// strTime = activity.getResString(R.string.today);
				// } else {
				// strTime = activity.getResString(R.string.yesterday);
				// }
				strTime = strTime + sf.format( serverDate );
			}
			else
			{
				sf = new SimpleDateFormat( "MM-dd HH:mm:ss" );
				strTime = "";
			}
		}
		else
		{
			SimpleDateFormat fmt = new SimpleDateFormat( "MM-dd HH:mm:ss" );
			Date date = new Date( );
			date.setTime( time );
			strTime = fmt.format( date.getTime( ) );
		}
		return strTime;
	}
	
	*/
/**
	 * 获取sd卡路径
	 * 
	 * @return
	 *//*

	public static String getSDPath( )
	{
		String sdDir = "";
		boolean sdCardExist = Environment.getExternalStorageState( ).equals(
				Environment.MEDIA_MOUNTED ); // 判断sd卡是否存在
		if ( sdCardExist )
		{
			sdDir = Environment.getExternalStorageDirectory( ) + "/iaround/";// 获取跟目录
			
		}
		else
		{
			sdDir = "/data/data/net.iaround/";
			// sdDir = Environment.getDataDirectory() + "/iAround/";
		}
		
		File file = new File( sdDir );
		if ( !file.exists( ) )
		{
			file.mkdirs( );
			if ( !file.exists( ) )
			{
				sdDir = "/data/data/net.iaround/";
				File tryfile = new File( sdDir );
				if ( !tryfile.exists( ) )
				{
					tryfile.mkdirs( );
				}
			}
		}
		return sdDir;
	}
	
	*/
/**
	 * 计算缩放比例
	 * 
	 * @param string
	 *            filename 图片路径
	 * @param swidth
	 *            目标宽度
	 * @param sheight
	 *            目标高度
	 *//*

	public static Bitmap scalePicture( String filename , int maxWidth , int maxHeight )
	{
		Bitmap bitmap = null;
		try
		{
			Options opts = new Options( );
			opts.inJustDecodeBounds = true;
			BitmapFactory.decodeFile( filename , opts );
			int srcWidth = opts.outWidth;
			int srcHeight = opts.outHeight;
			int desWidth = 0;
			int desHeight = 0;

			// 缩放比例
			double ratio = 0.0;
			if ( srcWidth > srcHeight )
			{
				ratio = srcWidth / maxWidth;
				desWidth = maxWidth;
				desHeight = ( int ) ( srcHeight / ratio );
			}
			else
			{
				ratio = srcHeight / maxHeight;
				desHeight = maxHeight;
				desWidth = ( int ) ( srcWidth / ratio );
			}
			// 设置输出宽度、高度
			Options newOpts = new Options( );
			newOpts.inSampleSize = ( int ) ( ratio ) + 1;
			newOpts.inJustDecodeBounds = false;
			newOpts.outWidth = desWidth;
			newOpts.outHeight = desHeight;
			bitmap = BitmapFactory.decodeFile( filename , newOpts );

		}
		catch ( Throwable e )
		{
			e.printStackTrace( );
		}
		return bitmap;
	}

	*/
/**
	 *
	 * @param bitmap
	 *            原图
	 * @param edgeLength
	 *            希望得到的正方形部分的边长
	 * @return (与服务端的截图方式一致)
	 *//*

	public static Bitmap centerSquareScaleBitmap( Bitmap bitmap , int edgeLength )
	{
		if ( null == bitmap || edgeLength <= 0 )
		{
			return null;
		}

		int widthOrg = bitmap.getWidth( );
		int heightOrg = bitmap.getHeight( );

		int ret = widthOrg > heightOrg ? heightOrg : widthOrg;

		Bitmap cutedBitmap = null;
		if (widthOrg != heightOrg) {
			int left = 0;
			int top = 0;
			if (heightOrg - widthOrg > 300) {
				top = (int) (0.23 * heightOrg);
				if (heightOrg - top < ret)
					top = 0;
			}
			cutedBitmap = Bitmap.createBitmap( bitmap , left , top , ret , ret );
		}

		if(cutedBitmap != null){
			return Bitmap.createScaledBitmap( cutedBitmap , edgeLength , edgeLength,  true );
		}else{
			return Bitmap.createScaledBitmap( bitmap , edgeLength , edgeLength,  true );
		}
	}

	public static Bitmap readBitMap( Context context , int resId , Bitmap.Config config )
	{
		Options opt = new Options( );
		opt.inPreferredConfig = config;
		opt.inPurgeable = true;
		opt.inInputShareable = true;

		InputStream is = context.getResources( ).openRawResource( resId );
		Bitmap decodeStream = BitmapFactory.decodeStream( is , null , opt );
		float width = decodeStream.getWidth( );
		float height = decodeStream.getHeight( );
		float srceenWidth = context.getResources( ).getDisplayMetrics( ).widthPixels;
		float screenHeight = context.getResources( ).getDisplayMetrics( ).heightPixels;
		Matrix matrix = new Matrix( );
		matrix.postScale( Math.max( 1.0f , srceenWidth / 480.0f ) ,
				Math.max( 1.0f , screenHeight / 800.0f ) );
		Bitmap newBitmap = Bitmap.createBitmap( decodeStream , 0 , 0 , ( int ) width ,
				( int ) height , matrix , true );
		if ( newBitmap != decodeStream )
		{
			decodeStream.recycle( );
		}

		return newBitmap;
	}

	*/
/**
	 * 表情详情页titile图处理
	 *
	 * @param context
	 * @param bitmap
	 * @param margin_dp
	 * @return
	 *//*

	public static Bitmap decomPression( Context context , Bitmap bitmap , int margin_dp )
	{
		float width = bitmap.getWidth( );
		float height = bitmap.getHeight( );
		float srceenWidth = context.getResources( ).getDisplayMetrics( ).widthPixels
				- dipToPx( context , margin_dp );
		Matrix matrix = new Matrix( );
		if ( width > srceenWidth )
		{
			matrix.postScale( srceenWidth / width , srceenWidth / width );
		}
		else if ( width < srceenWidth )
		{
			matrix.postScale( 1.0f , 1.0f );
		}

		return Bitmap.createBitmap( bitmap , 0 , 0 , ( int ) width , ( int ) height , matrix ,
				true );
	}

	*/
/**
	 *
	 * @param context
	 * @param bitmap
	 * @param margin_dp
	 * @return
	 *//*

	public static Bitmap decomBackGroundPression( Context context , Bitmap bitmap )
	{
		float width = bitmap.getWidth( );
		float height = bitmap.getHeight( );
		float srceenWidth = getScreenPixWidth( context );
		Matrix matrix = new Matrix( );
		if ( width > srceenWidth )
		{
			matrix.postScale( srceenWidth / width , srceenWidth / width );
		}
		else if ( width < srceenWidth )
		{
			matrix.postScale( srceenWidth / width , srceenWidth / width );
		}
		Bitmap newBitmap = Bitmap.createBitmap( bitmap , 0 , 0 , ( int ) width ,
				( int ) height , matrix , true );
		int width2 = newBitmap.getWidth( );
		int height2 = newBitmap.getHeight( );
		System.out.println( width2 + "w" + height2 );
		if ( newBitmap != bitmap )
		{
			bitmap.recycle( );
		}

		return newBitmap;

	}

	*/
/**
	 * 计算缩放比例
	 *
	 * @param Bitmap
	 *            srcBitmap 原图
	 * @param swidth
	 *            目标宽度
	 * @param sheight
	 *            目标高度
	 *//*

	public static Bitmap scalePicture( Bitmap srcBitmap , int maxWidth , int maxHeight )
	{
		try
		{
			Bitmap bitmap = null;
			int srcWidth = srcBitmap.getWidth( );
			int srcHeight = srcBitmap.getHeight( );
			// 计算缩放率，新尺寸除原始尺寸
			float scaleWidth = ( ( float ) maxWidth ) / srcWidth;
			float scaleHeight = ( ( float ) maxHeight ) / srcHeight;

			// 创建操作图片用的matrix对象
			Matrix matrix = new Matrix( );
			// 缩放图片动作
			matrix.postScale( scaleWidth , scaleHeight );
			// 创建新的图片
			// bitmap = Bitmap.createBitmap( srcBitmap , 0 , 0 , srcWidth ,
			// srcHeight , matrix ,
			// true );
			bitmap = CommonFunction.createBitmap( srcBitmap , 0 , 0 , srcWidth , srcHeight ,
					matrix , true );
			return bitmap;
		}
		catch ( Throwable t )
		{
			t.printStackTrace( );
			System.gc( );
		}
		return srcBitmap;
	}

	*/
/**
	 * 发送邮件
	 *
	 * @param emails
	 *            邮箱列表
	 * @param subject主题
	 * @param text
	 *            内容
	 * @param context
	 * @return
	 * @time 2011-7-5 上午10:54:01
	 * @author:linyg
	 *//*

	public static boolean sendEmail( String emails , String subject , String text ,
			Context context )
	{
		try
		{
			Uri mailToUri = Uri.parse( "mailto:" + emails );
			Intent inte = new Intent( Intent.ACTION_SENDTO , mailToUri );
			inte.putExtra( Intent.EXTRA_SUBJECT , subject );
			// inte.putExtra( android.content.Intent.EXTRA_TEXT , text );
			context.startActivity( inte );
			return true;
		}
		catch ( Throwable t )
		{
			CommonFunction.log( t );
			Toast.makeText( context , R.string.launch_email_client_failed , Toast.LENGTH_SHORT )
					.show( );
		}
		return false;
	}

	*/
/**
	 * 打电话
	 *
	 * @param context
	 * @param phone
	 *            电话号码
	 * @time 2011-7-11 上午10:01:02
	 * @author:linyg
	 *//*

	public static void doPhone( Context context , String phone )
	{
		// Intent.ACTION_CALL 直接拨打
		Intent myIntentDial = new Intent( Intent.ACTION_DIAL , Uri.parse( "tel:" + phone ) );
		context.startActivity( myIntentDial );
	}

	*/
/**
	 * 发短信
	 *
	 * @param context
	 * @param content
	 * @return
	 * @time 2011-6-27 上午11:08:46
	 * @author:linyg
	 *//*

	public static boolean sendSMS( Context context , String content )
	{
		Uri smsToUri = Uri.parse( "smsto:" );// 联系人地址
		Intent mIntent = new Intent( Intent.ACTION_SENDTO , smsToUri );
		mIntent.putExtra( "sms_body" , content );// 短信的内容
		context.startActivity( mIntent );
		return true;
	}

	*/
/**
	 * 获取通讯录头像
	 *
	 * @param context
	 * @param id
	 * @return
	 * @time 2011-10-11 下午02:51:07
	 * @author:linyg
	 *//*

	public static Bitmap getContactPhoto( Context context , long id )
	{
		boolean OldSDK = ( Build.VERSION.SDK_INT < 5 ) ? true : false;
		try
		{
			if ( OldSDK )
			{
				Uri uri = ContentUris.withAppendedId( People.CONTENT_URI , id );
				return People.loadContactPhoto( context , uri , R.drawable.default_face_small ,
						null );
			}
			else
			{
				byte[ ] data = new byte[ 0 ];
				Uri u = Uri.parse( "content://com.android.contacts/data" );
				String where = "raw_contact_id = " + id
						+ " AND mimetype ='vnd.android.cursor.item/photo'";
				Cursor cursor = context.getContentResolver( ).query( u , null , where , null ,
						null );
				if ( cursor.moveToFirst( ) )
				{
					data = cursor.getBlob( cursor.getColumnIndex( "data15" ) );
				}
				cursor.close( );

				if ( data != null && data.length > 0 )
				{
					return BitmapFactory.decodeByteArray( data , 0 , data.length );
				}
			}
		}
		catch ( Exception e )
		{
			return null;
		}
		return null;
	}

	*/
/**
	 * 群发短信
	 *
	 * @param context
	 * @param numbers
	 * @param msgContent
	 * @time 2011-6-3 下午02:45:27
	 * @author:linyg
	 *//*

	public static void sendMessage( Context context , ArrayList< String[ ] > numbers ,
			String msgContent )
	{
		String[ ] numbersArr = new String[ numbers.size( ) ];
		int idx = 0;
		for ( String[ ] number : numbers )
		{
			numbersArr[ idx ] = number[ 1 ];
			idx++;
		}
		Intent i = new Intent( "net.iaround.sendsms" );
		i.putExtra( "numbers" , numbersArr );
		i.putExtra( "msgContent" , msgContent );
		context.sendBroadcast( i );
	}

	*/
/**
	 * 写日志
	 *
	 * @param log
	 * @time 2011-5-31 下午05:45:01
	 * @author:linyg
	 *//*

	public static void writeLog( String log )
	{
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-DD hh:mm:ss" );
		String dateFormat = sdf.format( new Date( ) );
		try
		{
			if ( fw == null )
				fw = new FileWriter( getSDPath( ) + "/test.txt" , true );
			fw.write( dateFormat + ":" + log + "\r\n" );
			fw.flush( );
		}
		catch ( IOException e )
		{
			e.printStackTrace( );
		}
		finally
		{
			if ( fw != null )
			{
				try
				{
					fw.close( );
				}
				catch ( Throwable e )
				{
					e.printStackTrace( );
				}
				fw = null;
			}
		}
	}

	public static void writeExceptionLog( String log )
	{
		String dateFormat = TimeFormat.convertTimeLong2String( new Date( ).getTime( ), Calendar.SECOND );
		try
		{
			if ( fw == null )
				fw = new FileWriter( getSDPath( ) + "/" + dateFormat + "-Exception.txt" , true );
			fw.write( log + "\r\n" );
			fw.flush( );
		}
		catch ( IOException e )
		{
			e.printStackTrace( );
		}
		finally
		{
			if ( fw != null )
			{
				try
				{
					fw.close( );
				}
				catch ( Throwable e )
				{
					e.printStackTrace( );
				}
				fw = null;
			}
		}
	}

	public static void myWriteLog( String tag , String log )
	{
		if(!Config.DEBUG){
			return;
		}

		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-DD hh:mm:ss" );
		String dateFormat = sdf.format( new Date( ) );
		try
		{
			if ( fw == null )
				fw = new FileWriter( getSDPath( ) + "/groupChat.txt" , true );
			fw.write( dateFormat + "--" + tag + ":" + log + "\r\n" );
			fw.flush( );
		}
		catch ( IOException e )
		{
			e.printStackTrace( );
		}
		finally
		{
			if ( fw != null )
			{
				try
				{
					fw.close( );
				}
				catch ( Throwable e )
				{
					e.printStackTrace( );
				}
				fw = null;
			}
		}
	}

	*/
/**
	 * 从相机或相册获取图片的对话框 <br>
	 * <br>
	 *
	 * 图片的返回值需要在Activity的onActivityResult函数中捕获：<br>
	 *
	 * 从相机获取图片的情况：返回图片的完整路径的字符串 <br>
	 * requestCode：PictureCaptureHandle.PICK_PHOTO_FROM_CAMERA <br>
	 * Extra name：PictureCaptureHandle.PICKED_PHOTO_FILE_PATH <br>
	 * <br>
	 * 从相册获取图片的情况：返回图片的URI字符串 <br>
	 * requestCode：PictureCaptureHandle.PICK_PHOTO_FROM_GALLERY <br>
	 * Extra name：PictureCaptureHandle.PICKED_PHOTO_URI <br>
	 *
	 * @param context
	 * @param titleResId
	 *            对话框标题的字符串资源id；若为null，则对话框没有标题
	 * @param targetClass
	 *            获取图片并处理完后，最终需要跳转到的Activity对应的类名；若为null，则跳转到调用该对话框的Activity
	 *
	 * @return
	 *//*

	// public static void pickPictureDialog( final Context context , final
	// boolean returnOriBm ,
	// int titleResId , final ImageEntrance type , final int requestCode )
	// {
	// // 是否可以上传
	// if ( CommonFunction.forbidSay( context ) ||
	// !SendBackManage.checkUploadNum( context ) )
	// {
	// return;
	// }
	//
	// View contentView = LayoutInflater.from( context ).inflate(
	// R.layout.upload_picture_pop ,
	// null );
	// final Dialog builder = new Dialog( context );
	// // 相机
	// contentView.findViewById( R.id.fromCamera ).setOnClickListener( new
	// OnClickListener( )
	// {
	// @Override
	// public void onClick( View v )
	// {
	// builder.dismiss( );
	// Intent i = PictureCaptureHandle.getPickPhotoFromCameraIntent( context ,
	// returnOriBm , returnOriBm );
	// i.putExtra( FocusCreateSecond.TYPE , type.name( ) );
	// ( ( Activity ) context ).startActivityForResult( i , requestCode );
	// }
	// } );
	// // 相册
	// contentView.findViewById( R.id.frompPhoto ).setOnClickListener( new
	// OnClickListener( )
	// {
	// @Override
	// public void onClick( View v )
	// {
	// builder.dismiss( );
	// Intent i = PictureCaptureHandle.getPickPhotoFromAlbumIntent( context ,
	// returnOriBm ,
	// returnOriBm );
	// i.putExtra( FocusCreateSecond.TYPE , type.name( ) );
	// ( ( Activity ) context ).startActivityForResult( i , requestCode );
	// }
	// } );
	// if ( titleResId > 0 )
	// {
	// builder.setTitle( titleResId );
	// // ((TextView)contentView.findViewById(R.id.title)).setText(titleResId);
	// }
	// builder.setContentView( contentView );
	// builder.show( );
	// }

	*/
/**
	 * 获取屏幕分辨率：宽
	 *
	 * @param context
	 * @return
	 *//*

	public static int getScreenPixWidth( Context context )
	{
		DisplayMetrics dm = new DisplayMetrics( );
		if ( ! ( context instanceof Activity ) )
		{
			dm = context.getResources( ).getDisplayMetrics( );
			return dm.widthPixels;
		}

		WindowManager wm = ( ( Activity ) context ).getWindowManager( );
		if ( wm == null )
		{
			dm = context.getResources( ).getDisplayMetrics( );
			return dm.widthPixels;
		}

		wm.getDefaultDisplay( ).getMetrics( dm );
		return dm.widthPixels;
	}

	*/
/**
	 * 获取屏幕分辨率：高
	 *
	 * @param context
	 * @return
	 *//*

	public static int getScreenPixHeight( Context context )
	{
		DisplayMetrics dm = new DisplayMetrics( );
		if ( ! ( context instanceof Activity ) )
		{
			dm = context.getResources( ).getDisplayMetrics( );
			return dm.heightPixels;
		}

		WindowManager wm = ( ( Activity ) context ).getWindowManager( );
		if ( wm == null )
		{
			dm = context.getResources( ).getDisplayMetrics( );
			return dm.heightPixels;
		}

		wm.getDefaultDisplay( ).getMetrics( dm );
		return dm.heightPixels;

	}

	*/
/** 获取状态栏的高度 *//*

	public static int getStatusBarHeight( Context context )
	{
		Class< ? > c = null;
		Object obj = null;
		Field field = null;
		int x = 0;
		int statusBarHeight = 0;
		try
		{
			c = Class.forName( "com.android.internal.R$dimen" );
			obj = c.newInstance( );
			field = c.getField( "status_bar_height" );
			x = Integer.parseInt( field.get( obj ).toString( ) );
			statusBarHeight = context.getResources( ).getDimensionPixelSize( x );
			return statusBarHeight;
		}
		catch ( Exception e )
		{
			e.printStackTrace( );
		}
		return statusBarHeight;
	}

	*/
/** 获取导航栏的高度-针对虚拟按键 *//*

	public static int getNavigationBarHeight( Context context )
	{
		int navigationBarHeight = 0;
		int resourceId = context.getResources( ).getIdentifier( "navigation_bar_height" ,
				"dimen" , "android" );
		if ( resourceId > 0 )
		{
			navigationBarHeight = context.getResources( ).getDimensionPixelSize( resourceId );
		}

		return navigationBarHeight;
	}

	*/
/** 关闭后台服务 *//*

	public static void stopBackService( Context context )
	{
		uiRunning = true;
		Intent i = new Intent( BackService.STOP_SERVICE_BROADCAST_KEY );
		context.sendBroadcast( i );
	}

	*/
/**
	 * 将文本设置到剪切板中
	 *
	 * @param context
	 * @param str
	 * @time 2011-6-27 下午02:53:41
	 * @author:linyg
	 *//*

	public static boolean setClipboard( Context context , String str )
	{
		try
		{
			ClipboardManager clip = ( ClipboardManager ) context
					.getSystemService( Context.CLIPBOARD_SERVICE );

			// 将包含有自定义表情的文本转换
			String text = FaceManager.catFace2Text( context , str );
			clip.setText( text );
			return true;
		}
		catch ( Exception e )
		{
			return false;
		}
	}

	*/
/**
	 * 从剪切板中读取文本
	 *
	 * @param context
	 * @return
	 * @time 2011-6-27 下午02:53:56
	 * @author:linyg
	 *//*

	public static String getClipboard( Context context )
	{
		ClipboardManager clip = ( ClipboardManager ) context
				.getSystemService( Context.CLIPBOARD_SERVICE );
		return clip.getText( ).toString( );
	}

	*/
/**
	 * 显示toast
	 *
	 * @param context
	 * @param text
	 * @param duration
	 * @time 2011-6-28 上午10:11:22
	 * @author:linyg
	 *//*

	public static void showToast( Context context , String text , int duration )
	{
		Toast.makeText( context , text , duration ).show( );
	}

	*/
/**
	 * 将字符串转换成背景颜色
	 *
	 * @param strColor
	 * @return
	 * @time 2011-6-28 下午02:52:56
	 * @author:linyg
	 *//*

	public static int stringToRGBColor( String strColor )
	{
		int red = 0 , green = 0 , blue = 0;
		if ( strColor != null )
		{
			strColor = strColor.replace( "#" , "" );
			if ( strColor.length( ) == 6 )
			{
				try
				{
					String strRed = strColor.substring( 0 , 2 );
					String strGreen = strColor.substring( 2 , 4 );
					String strBlue = strColor.substring( 4 , 6 );

					red = Integer.parseInt( strRed , 16 );
					green = Integer.parseInt( strGreen , 16 );
					blue = Integer.parseInt( strBlue , 16 );
				}
				catch ( Exception e )
				{
					e.printStackTrace( );
				}
			}
		}
		return Color.rgb( red , green , blue );
	}

	public static String thumPicture( String url )
	{
		if ( !TextUtils.isEmpty( url ) )
		{
			int last = url.lastIndexOf( "_s" );
			if ( last > 0 )
			{
				return url;
			}

			if ( url.contains( "." ) )
			{
				try
				{
					String lastStr = url.substring( url.lastIndexOf( "." ) , url.length( ) );
					String startStr = url.substring( 0 , url.lastIndexOf( "." ) );
					return startStr + "_s" + lastStr;
				}
				catch ( Exception e )
				{

				}
			}
			else
			{
				return url + "_s";
			}

		}
		return "";
	}

	public static String sourcePicture( String url )
	{
		if ( !TextUtils.isEmpty( url ) )
		{
			int last = url.lastIndexOf( "_s" );
			if ( last < 0 )
			{
				return url;
			}

			try
			{
				String lastStr = url.substring( url.lastIndexOf( "_s" ) + 2 , url.length( ) );
				String startStr = url.substring( 0 , url.lastIndexOf( "_s" ) );
				return startStr + lastStr;
			}
			catch ( Exception e )
			{

			}
		}
		return "";
	}

	*/
/**
	 * 将字符串列表转换为字符串
	 *
	 * @param strList
	 * @return
	 *//*

	public static String strListToString( ArrayList< String > strList )
	{
		String str = "";

		if ( strList != null )
		{
			for ( int i = 0 ; i < strList.size( ) ; i++ )
			{
				str += strList.get( i );
			}
		}

		return str;
	}

	public static boolean isShakeState( Context context )
	{
		// 震动
		String vibrateKey = SharedPreferenceUtil.VIBRATE;
		SharedPreferenceUtil sp = SharedPreferenceUtil.getInstance( context );
		if ( sp.has( vibrateKey ) )
		{
			return sp.getBoolean( vibrateKey );
		}
		else
		{
			sp.putBoolean( vibrateKey , false );
		}
		return false;

	}

	*/
/** 执行来信声音提示 *//*

	public static void notifyMsgVoice( Context context , int msgType )
	{
		boolean voiceEnable = false;

		String voiceKey = SharedPreferenceUtil.VOICE_ENABLE
				+ Common.getInstance( ).loginUser.getUid( );
		SharedPreferenceUtil sp = SharedPreferenceUtil.getInstance( context );

		if ( sp.has( voiceKey ) )
		{
			voiceEnable = sp.getBoolean( voiceKey );
		}
		else
		{
			sp.putBoolean( voiceKey , true );
			voiceEnable = true; // 默认是全局打开声音
		}

		// 声音
		if ( voiceEnable )
		{
			String voiceSettings = sp.getString( SharedPreferenceUtil.VOICE_SETTINGS
					+ Common.getInstance( ).loginUser.getUid( ) );
			if ( voiceSettings == null || voiceSettings.length( ) <= 0 )
			{
				voiceSettings = "false%true%true%true"*/
/* "false%true%true%true%true" *//*
;
			}
			String[ ] pSettings = voiceSettings.split( "%" );
			voiceEnable = msgType >= 0 && msgType < pSettings.length
					&& Boolean.parseBoolean( pSettings[ msgType ] );
			if ( voiceEnable )
			{
				try
				{
					if ( mediaPlayer != null )
					{
						if ( mediaPlayer.isPlaying( ) )
						{
							return;
						}
						mediaPlayer.release( );
					}
					mediaPlayer = MediaPlayer.create( context , R.raw.notify );
					if ( mediaPlayer != null )
					{
						mediaPlayer.start( );
					}
				}
				catch ( Throwable e )
				{
					CommonFunction.log( e );
				}
			}
		}
	}

	*/
/** 执行来信声音提示 *//*

	public static void notifyMsgVoice( Context context )
	{
		try
		{
			if ( mediaPlayer != null )
			{
				if ( mediaPlayer.isPlaying( ) )
				{
					return;
				}
				mediaPlayer.release( );
			}
			mediaPlayer = MediaPlayer.create( context , R.raw.notify );
			if ( mediaPlayer != null )
			{
				mediaPlayer.start( );
			}
		}
		catch ( Throwable e )
		{
			CommonFunction.log( e );
		}
	}

	*/
/** 执行来信震动提示 *//*

	public static void notifyMsgShake( Context context )
	{
		if ( vibrator == null )
		{
			vibrator = ( Vibrator ) context.getSystemService( SuperActivity.VIBRATOR_SERVICE );
		}
		long[ ] pattern =
			{ 50 , 150 , 50 , 150 , 50 , 150 , 50 }; // off-on-off-on...
		vibrator.vibrate( pattern , -1 );
	}

	*/
/** 释放来信提示的资源 *//*

	public static void release( )
	{
		if ( vibrator != null )
		{
			vibrator.cancel( );
			vibrator = null;
		}
		if ( mediaPlayer != null )
		{
			try
			{
				if ( mediaPlayer.isPlaying( ) )
				{
					mediaPlayer.stop( );
				}
				mediaPlayer.release( );
			}
			catch ( Throwable t )
			{
				CommonFunction.log( t );
			}
			mediaPlayer = null;
		}
	}

	*/
/**
	 * 给编辑框设置表情
	 *
	 * @param context
	 * @param editContent
	 *            编辑框
	 * @param faceTitle
	 *            表情的标识
	 * @param faceImg
	 *            表情的资源ID
	 *//*

	public static void setFace( Context context , EditText editContent , String faceTitle ,
			int faceImg , int max )
	{
		int start = editContent.getSelectionStart( );
		Resources res = context.getResources( );
		float faceSize = res.getDimension( R.dimen.face_height );

		int length = StringUtil.getLengthCN1( editContent.getText( ).toString( ) );
		if ( length + StringUtil.FACE_TAG_NUM <= max )
		{
			String strUnicode = faceStr2Unicode( faceTitle );
			Spannable ss = editContent.getText( ).insert( start , strUnicode );

			Drawable d = FaceManager.getInstance( context ).getDrawableWithFaceKey( context ,
					faceTitle );
			if ( d != null )
			{
				d.setBounds( 0 , 0 , ( int ) faceSize , ( int ) faceSize );
				ImageSpan span = new ImageSpan( d , ImageSpan.ALIGN_BOTTOM );

				ss.setSpan( span , start , start + strUnicode.length( ) ,
						Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
				// 保存最近使用的表情
				CommonFunction.saveLastUsedFace( context , faceTitle );
			}
		}
	}

	*/
/**
	 * 清除本地缓存
	 *
	 * @param context
	 * @param clearType
	 *            1为删除缓存文件和数据库，2仅删除数据库
	 * @time 2011-7-13 上午11:29:58
	 * @author:linyg
	 *//*

	public static boolean clearCache( Context context , int clearType )
	{
		String url = context.getCacheDir( ).getAbsolutePath();
		String url2 = getSDPath();
		switch ( clearType )
		{
			case 1 : // 清除文件和数据库数据
				deleteFolder( url );
				deleteFolder( url2 );
				break;
		}
		// NearContactWorker wDb =
		// DatabaseFactory.getNearContactWorker(context);
		// wDb.onDeleteAll();
		// MessageWorker mDb = DatabaseFactory.getMessageWorker(context);
		// mDb.onDeleteAll();
		// PersonalMessageWorker pDb =
		// DatabaseFactory.getChatMessageWorker(context);
		// pDb.onDeleteAll();
		return true;
	}

	*/
/**
	 * 删除某一目录
	 *
	 * @param String
	 *            路径
	 *//*

	public static boolean deleteFolder( String path )
	{
		File file = new File( path );
		return deleteFolder( file );
	}

	*/
/**
	 * 删除某一目录
	 *
	 * @param File
	 *            删除的路径文件
	 *//*

	public static boolean deleteFolder( File folder )
	{
		boolean result = false;
		try
		{

			String childs[] = folder.list( );
			if ( childs == null || childs.length <= 0 )
			{
				if ( folder.delete( ) )
				{
					result = true;
				}
			}
			else
			{
				for ( int i = 0 ; i < childs.length ; i++ )
				{
					String childName = childs[ i ];
					String childPath = folder.getPath( ) + File.separator + childName;
					File filePath = new File( childPath );
					if ( filePath.exists( ) && filePath.isFile( ) )
					{
						if ( filePath.delete( ) )
						{
							result = true;
						}
						else
						{
							result = false;
							break;
						}
					}
					else if ( filePath.exists( ) && filePath.isDirectory( ) )
					{
						if ( deleteFolder( filePath ) )
						{
							result = true;
						}
						else
						{
							result = false;
							break;
						}
					}
				}
				folder.delete( );
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace( );
			result = false;
		}
		return result;
	}

	*/
/**
	 * 更新输入框的内容缓存
	 *
	 * @param s
	 *            改变后的字符串
	 * @param start
	 *            新字符串开始的位置
	 * @param before
	 *            被替换的字符串的长度
	 * @param count
	 *            新字符串的长度
	 * @param isInputFace
	 *            输入的是否为表情
	 * @param faceFlag
	 *            表情对应的标识
	 * @param editTextCache
	 *            输入框的内容缓存
	 *//*

	public static void updateEditTextCache( CharSequence newStr , int start , int before ,
			int count , boolean isInputFace , String faceFlag ,
			ArrayList< String > editTextCache )
	{
		// 删除
		for ( int i = start ; i < start + before ; i++ )
		{
			editTextCache.remove( start );
			editTextCache.trimToSize( );
		}

		// 添加
		for ( int j = start ; j < start + count ; j++ )
		{
			String str = String.valueOf( newStr.charAt( j ) );

			if ( isInputFace )
			{
				str = faceFlag;
			}

			if ( j > editTextCache.size( ) )
			{
				editTextCache.add( str );
			}
			else if ( editTextCache.size( ) == 0 )
			{
				editTextCache.add( str );
			}
			else
			{
				editTextCache.add( j , str );
			}
		}
	}

	*/
/**
	 * 保存最近使用的表情(最多存放100个表情)
	 *
	 * @param faceKey
	 *            表情的标识
	 *//*

	public static void saveLastUsedFace( Context context , String faceKey )
	{
		SharedPreferenceUtil pre = SharedPreferenceUtil.getInstance( context );
		String faceStr = pre.getString( SharedPreferenceUtil.LAST_USED_FACE );

		if ( faceStr != null && !faceStr.equals( "" ) )
		{
			String[ ] faceList = faceStr.split( "," );

			for ( int i = 0 ; i < faceList.length ; i++ )
			{
				if ( faceKey.equals( faceList[ i ] ) )
				{ // 以保存过此表情，则删除
					faceList[ i ] = "";

					break;
				}
			}

			String newFaceStr = faceKey;
			int num = 1; // 表情的个数
			for ( int j = 0 ; j < faceList.length ; j++ )
			{ // 保存新的表情
				if ( !faceList[ j ].equals( "" ) )
				{
					newFaceStr += ",";
					newFaceStr += faceList[ j ];

					num++;
					if ( 100 == num )
					{ // 最多存放100个表情
						break;
					}
				}
			}
			pre.putString( SharedPreferenceUtil.LAST_USED_FACE , newFaceStr );
		}
		else
		{
			pre.putString( SharedPreferenceUtil.LAST_USED_FACE , faceKey );
		}
	}

	*/
/**
	 * 给ImageView加载图片
	 *
	 * @param context
	 * @param viewGroup
	 *            装载图片的imageView的父容器，如ListView
	 * @param imageView
	 *            装载图片的imageView
	 * @param imageUrl
	 *            图片的URL
	 * @param isIcon
	 *            是否为头像
	 *//*

	public static void loadImageForImageView( ImageView imageView , String imageUrl ,
			boolean isIcon )
	{
		ImageViewUtil.getDefault( ).loadImage( imageUrl , imageView ,
				isIcon ? R.drawable.default_face_small : 0 ,
				isIcon ? R.drawable.default_face_small : 0 );
	}

	public static void setGroupBigIconSize( final ViewGroup layout , ImageView imageView )
	{
		int layoutW = layout.getWidth( );

		ViewGroup.LayoutParams params = layout.getLayoutParams( );
		params.width = layoutW;
		params.height = layoutW;
		layout.setLayoutParams( params );

		params = imageView.getLayoutParams( );
		params.width = layoutW;
		params.height = layoutW;
		imageView.setLayoutParams(params);
	}

	*/
/**
	 * 根据各国的手机号码规则，检测输入
	 *
	 * @param code
	 * @param number
	 * @return
	 * @time 2011-7-22 上午09:41:04
	 * @author:linyg
	 *//*

	public static boolean phoneNumberValid( String code , String number )
	{
		// 手机号固定在5-20范围内
		if ( number.length( ) < 5 || number.length( ) > 20 )
		{
			return false;
		}

		String match = "";
		if ( "86".equals( code ) )
		{// 中国
			if ( number.length( ) != 11 )
			{
				return false;
			}
			else
			{
				match = "^[1]{1}[0-9]{2}[0-9]{8}$";
			}
		}

		// 正则匹配
		if ( !"".equals( match ) )
		{
			return number.matches( match );
		}
		return true;
	}

	*/
/**
	 * 过滤关键字
	 *
	 * @param keyword
	 * @return 返回true则包含敏感字符或者已经被禁言，不让其发布出去；返回false则可以通过
	 * @time 2011-7-27 下午01:43:46
	 * @author:linyg
	 *//*

	public static boolean filterKeyWord( Context context , String keyword )
	{
		if ( forbidSay(context) )
		{
			return true;
		}
		// 非法字符
		String k = KeyWord.getInstance( context ).filterKeyWord( context , keyword );
		if ( !"".equals( k ) )
		{
			showToast( context , context.getString( R.string.have_sensitive_keyword ) + ":"
					+ k , 0 );
			return true;
		}
		return false;
	}


	public static boolean filterHasKeyWord( Context context , String keyword )
	{

		// 非法字符
		String k = KeyWord.getInstance( context ).filterKeyWord( context , keyword );
		if ( !"".equals( k ) )
		{
			showToast( context , context.getString( R.string.have_sensitive_keyword ) + ":"
					+ k , 0 );
			return true;
		}
		return false;
	}


	*/
/**
	 * @Title: getSensitiveKeyword
	 * @Description: 获取字符串中的敏感字符
	 * @param context
	 * @param keyword
	 * @return
	 *//*

	public static String getSensitiveKeyword( Context context , String keyword )
	{
		String k = KeyWord.getInstance( context ).filterKeyWord( context , keyword );
		return k;
	}

	*/
/**
	 * 过滤关键字并将关键字替换为 Emoji表情
	 *
	 * @param keyword
	 * @return 返回的字符已经经过过滤处理，如果返回null或空字符串表示用户被禁言
	 * @author huyunfeng E -mail:my519820363@gmail.com
	 * @version CreateTime：2013- 3-27 上午11:39:13
	 *//*

	public static String filterKeyWordAndReplaceEmoji( Context context , String keyword )
	{
		if ( TextUtils.isEmpty( keyword ) )
		{
			return null;// 空的内容
		}

		// 替换非法字符
		return KeyWord.getInstance( context ).filterKeyWordAndReplaceEmoji( context , keyword );
	}

	*/
/**
	 * 用户是否被禁言
	 *
	 * @param context
	 * @return true被禁言 ，false不被禁言
	 * @time 2011-10-10 下午03:52:03
	 * @author:linyg
	 *//*

	public static boolean forbidSay( Context context )
	{
		// 用户被禁言
		long time = StartModel.getInstance( ).getForbidTime( );
		if ( time != 0 )
		{
			if ( time < 0 )
			{ // 永久禁言
				showToast( context , context.getString( R.string.admin_forever_forbid_say ) ,
						0 );
				return true;
			}
			else if ( time
					- ( Common.getInstance( ).serverToClientTime + System.currentTimeMillis( ) ) > 0 )
			{
				SimpleDateFormat sdf = new SimpleDateFormat( );
				sdf.applyPattern( "yyyy-MM-dd HH:mm" );
				String format_time = sdf.format( time
						+ Common.getInstance( ).serverToClientTime );
				showToast( context , String.format(
						context.getString( R.string.admin_forbid_say ) , format_time ) , 0 );
				return true;
			}
		}
		return false;
	}

	*/
/** 重定向logcat到指定文件中 *//*

	public static void redirectLogcat( )
	{
		if ( Config.DEBUG )
		{
			new Thread( )
			{

				@Override
				public void run( )
				{
					try
					{
						String logFile = getSDPath( ) + "logcat.txt";
						// File log = new File( logFile );
						// if ( log.exists( ) )
						// {
						// log.delete( );
						// }

						Runtime.getRuntime( ).exec( "logcat -c" );

						if ( logcatProcess != null )
						{
							logcatProcess.destroy( );
							logcatProcess = null;
						}
						logcatProcess = Runtime.getRuntime( ).exec(
								"logcat -v time -f " + logFile );
						if ( logcatProcess != null )
						{
							CommonFunction.log( "System.out" , "Logcat process started" );
						}
					}
					catch ( Exception e )
					{
						e.printStackTrace( );
						return;
					}
				}
			}.start( );
		}
	}

	*/
/** 停止logcat重定向 *//*

	public static void stopLogcatRedirect( )
	{
		if ( Config.DEBUG )
		{
			try
			{
				if ( logcatProcess != null )
				{
					logcatProcess.destroy( );
					logcatProcess = null;
					CommonFunction.log( "System.out" , "Logcat process stopped" );
				}
			}
			catch ( Exception e )
			{
				e.printStackTrace( );
			}
		}
	}

	*/
/** 转储logcat *//*

	public static void dumpLogcat( )
	{
		if ( !Config.DEBUG )
		{
			try
			{
				File uncaughtExceptionLogFolder = new File( getSDPath( )
						+ "UncaughtExceptions/" );
				if ( !uncaughtExceptionLogFolder.exists( ) )
				{
					uncaughtExceptionLogFolder.mkdirs( );
				}
				String fileName = TimeFormat.convertTimeLong2String(
						System.currentTimeMillis( ) , Calendar.SECOND )
						+ ".txt";
				String dumpFile = uncaughtExceptionLogFolder.getAbsolutePath( ) + "/"
						+ fileName;
				Process pDump = Runtime.getRuntime( )
						.exec( "logcat -v time -d -f " + dumpFile );
				pDump.waitFor( );
				CommonFunction.log( "System.err" , "Log file has been dump to \"" + dumpFile
						+ "\"" );
			}
			catch ( Exception e )
			{
				e.printStackTrace( );
			}
		}
	}

	*/
/** 计算字符串的字符数（unicode值小于256的算一个字符，大于或等于的算两个字符） *//*

	public static int getStringLength( String str )
	{
		char[ ] stringArray = str.toCharArray( );
		int count = 0;
		for ( char ch : stringArray )
		{
			count += ( ch < 256 ) ? 1 : 2;
		}
		return count;
	}

	*/
/** 个性化显示地址 **//*

	public static String showAddress( String strAddress )
	{
		if ( strAddress == null )
		{
			return "";
		}
		if ( strAddress.startsWith( "," ) )
		{
			return strAddress.replaceAll( "," , "" );
		}

		if ( !",,,".equals( strAddress ) )
		{
			char[ ] strArray = strAddress.toCharArray( );
			if ( strArray.length > 0 && strArray[ 0 ] < 256 )
			{
				return strAddress;
			}
		}
		return strAddress.replaceAll( "," , "" );
	}

	*/
/** 修改媒体音量（increment > 0为增加，increment < 0为减少，每次只会增加或减少1） *//*

	public static void changeMediaVolume( Context context , int increment )
	{
		AudioManager am = ( AudioManager ) context.getSystemService( Context.AUDIO_SERVICE );
		int curVol = am.getStreamVolume( AudioManager.STREAM_MUSIC );

		if ( increment > 0 )
		{
			am.adjustStreamVolume( AudioManager.STREAM_MUSIC , AudioManager.ADJUST_RAISE ,
					AudioManager.FX_FOCUS_NAVIGATION_UP );
		}
		else if ( increment < 0 )
		{
			am.adjustStreamVolume( AudioManager.STREAM_MUSIC , AudioManager.ADJUST_LOWER ,
					AudioManager.FX_FOCUS_NAVIGATION_UP );
		}
		CommonFunction.log( "System.out" , "curVol = " + curVol );
	}

	*/
/**
	 * 计算标准的分辨率（只有标准的服务端才有相应的图片库） 标准:240*320,320*480,480*800 如果没有，则使用默认的320*480
	 *//*

	public static Map< String , Integer > setWidthHeight( int width , int height )
	{
		int mWidth = 0;
		int mHeight = 0;
		if ( width < 320 )
		{
			mWidth = 240;
			mHeight = 320;
		}
		else if ( width == 320 )
		{
			mWidth = 320;
			mHeight = 480;
		}
		else if ( width > 320 )
		{
			mWidth = 480;
			mHeight = 800;
		}

		Map< String , Integer > map = new HashMap< String , Integer >( );
		map.put( "width" , mWidth );
		map.put("height", mHeight);
		return map;
	}

	*/
/** 转换魅力值符号数组 *//*

	public static int[ ] getCharismaSymbol( int charisma )
	{
		int[ ] symbol = new int[ 5 ];
		symbol = new int[ ]
			{ 0 , 0 , 0 , 0 , 0 };
		if ( 5 <= charisma && charisma <= 20 )
		{
			symbol = new int[ ]
				{ 1 , 0 , 0 , 0 , 0 };
		}
		else if ( 21 <= charisma && charisma <= 50 )
		{
			symbol = new int[ ]
				{ 1 , 1 , 0 , 0 , 0 };
		}
		else if ( 51 <= charisma && charisma <= 100 )
		{
			symbol = new int[ ]
				{ 1 , 1 , 1 , 0 , 0 };
		}
		else if ( 101 <= charisma && charisma <= 150 )
		{
			symbol = new int[ ]
				{ 1 , 1 , 1 , 1 , 0 };
		}
		else if ( 151 <= charisma && charisma <= 200 )
		{
			symbol = new int[ ]
				{ 1 , 1 , 1 , 1 , 1 };
		}
		else if ( 201 <= charisma && charisma <= 260 )
		{
			symbol = new int[ ]
				{ 2 , 0 , 0 , 0 , 0 };
		}
		else if ( 261 <= charisma && charisma <= 330 )
		{
			symbol = new int[ ]
				{ 2 , 1 , 0 , 0 , 0 };
		}
		else if ( 331 <= charisma && charisma <= 410 )
		{
			symbol = new int[ ]
				{ 2 , 1 , 1 , 0 , 0 };
		}
		else if ( 411 <= charisma && charisma <= 500 )
		{
			symbol = new int[ ]
				{ 2 , 1 , 1 , 1 , 0 };
		}
		else if ( 501 <= charisma && charisma <= 600 )
		{
			symbol = new int[ ]
				{ 2 , 1 , 1 , 1 , 1 };
		}
		else if ( 601 <= charisma && charisma <= 800 )
		{
			symbol = new int[ ]
				{ 2 , 2 , 0 , 0 , 0 };
		}
		else if ( 801 <= charisma && charisma <= 1100 )
		{
			symbol = new int[ ]
				{ 2 , 2 , 1 , 0 , 0 };
		}
		else if ( 1101 <= charisma && charisma <= 1500 )
		{
			symbol = new int[ ]
				{ 2 , 2 , 1 , 1 , 0 };
		}
		else if ( 1501 <= charisma && charisma <= 2000 )
		{
			symbol = new int[ ]
				{ 2 , 2 , 1 , 1 , 1 };
		}
		else if ( 2001 <= charisma && charisma <= 2600 )
		{
			symbol = new int[ ]
				{ 2 , 2 , 2 , 0 , 0 };
		}
		else if ( 2601 <= charisma && charisma <= 3300 )
		{
			symbol = new int[ ]
				{ 2 , 2 , 2 , 1 , 0 };
		}
		else if ( 3301 <= charisma && charisma <= 4000 )
		{
			symbol = new int[ ]
				{ 2 , 2 , 2 , 1 , 1 };
		}
		else if ( 4001 <= charisma && charisma <= 4800 )
		{
			symbol = new int[ ]
				{ 2 , 2 , 2 , 2 , 0 };
		}
		else if ( 4801 <= charisma && charisma <= 5800 )
		{
			symbol = new int[ ]
				{ 2 , 2 , 2 , 2 , 1 };
		}
		else if ( 5801 <= charisma && charisma <= 7000 )
		{
			symbol = new int[ ]
				{ 2 , 2 , 2 , 2 , 2 };
		}
		else if ( 7001 <= charisma && charisma <= 8500 )
		{
			symbol = new int[ ]
				{ 3 , 0 , 0 , 0 , 0 };
		}
		else if ( 8501 <= charisma && charisma <= 10000 )
		{
			symbol = new int[ ]
				{ 3 , 1 , 0 , 0 , 0 };
		}
		else if ( 10001 <= charisma && charisma <= 12000 )
		{
			symbol = new int[ ]
				{ 3 , 1 , 1 , 0 , 0 };
		}
		else if ( 12001 <= charisma && charisma <= 15000 )
		{
			symbol = new int[ ]
				{ 3 , 1 , 1 , 1 , 0 };
		}
		else if ( 15001 <= charisma && charisma <= 20000 )
		{
			symbol = new int[ ]
				{ 3 , 1 , 1 , 1 , 1 };
		}
		else if ( 20001 <= charisma && charisma <= 27000 )
		{
			symbol = new int[ ]
				{ 3 , 2 , 0 , 0 , 0 };
		}
		else if ( 27001 <= charisma && charisma <= 35000 )
		{
			symbol = new int[ ]
				{ 3 , 2 , 2 , 0 , 0 };
		}
		else if ( 35001 <= charisma && charisma <= 44000 )
		{
			symbol = new int[ ]
				{ 3 , 2 , 2 , 2 , 0 };
		}
		else if ( 44001 <= charisma && charisma <= 54000 )
		{
			symbol = new int[ ]
				{ 3 , 2 , 2 , 2 , 2 };
		}
		else if ( 54001 <= charisma && charisma <= 65000 )
		{
			symbol = new int[ ]
				{ 3 , 3 , 0 , 0 , 0 };
		}
		else if ( 65001 <= charisma && charisma <= 80000 )
		{
			symbol = new int[ ]
				{ 3 , 3 , 1 , 0 , 0 };
		}
		else if ( 80001 <= charisma && charisma <= 110000 )
		{
			symbol = new int[ ]
				{ 3 , 3 , 1 , 1 , 0 };
		}
		else if ( 110001 <= charisma && charisma <= 150000 )
		{
			symbol = new int[ ]
				{ 3 , 3 , 1 , 1 , 1 };
		}
		else if ( 150001 <= charisma && charisma <= 200000 )
		{
			symbol = new int[ ]
				{ 3 , 3 , 2 , 0 , 0 };
		}
		else if ( 200001 <= charisma && charisma <= 250000 )
		{
			symbol = new int[ ]
				{ 3 , 3 , 2 , 2 , 0 };
		}
		else if ( 250001 <= charisma && charisma <= 320000 )
		{
			symbol = new int[ ]
				{ 3 , 3 , 2 , 2 , 2 };
		}
		else if ( 320001 <= charisma && charisma <= 400000 )
		{
			symbol = new int[ ]
				{ 3 , 3 , 3 , 0 , 0 };
		}
		else if ( 400001 <= charisma && charisma <= 500000 )
		{
			symbol = new int[ ]
				{ 3 , 3 , 3 , 1 , 0 };
		}
		else if ( 500001 <= charisma && charisma <= 620000 )
		{
			symbol = new int[ ]
				{ 3 , 3 , 3 , 1 , 1 };
		}
		else if ( 620001 <= charisma && charisma <= 800000 )
		{
			symbol = new int[ ]
				{ 3 , 3 , 3 , 2 , 0 };
		}
		else if ( 800001 <= charisma && charisma <= 1000000 )
		{
			symbol = new int[ ]
				{ 3 , 3 , 3 , 2 , 2 };
		}
		else if ( 1000001 <= charisma && charisma <= 1300000 )
		{
			symbol = new int[ ]
				{ 3 , 3 , 3 , 3 , 0 };
		}
		else if ( 1300001 <= charisma && charisma <= 1600000 )
		{
			symbol = new int[ ]
				{ 3 , 3 , 3 , 3 , 1 };
		}
		else if ( 1600001 <= charisma && charisma <= 2000000 )
		{
			symbol = new int[ ]
				{ 3 , 3 , 3 , 3 , 2 };
		}
		else if ( 2000001 <= charisma && charisma <= 2400000 )
		{
			symbol = new int[ ]
				{ 3 , 3 , 3 , 3 , 3 };
		}
		else if ( 2400001 <= charisma && charisma <= 2800000 )
		{
			symbol = new int[ ]
				{ 4 , 0 , 0 , 0 , 0 };
		}
		else if ( 2800001 <= charisma && charisma <= 3200000 )
		{
			symbol = new int[ ]
				{ 4 , 1 , 0 , 0 , 0 };
		}
		else if ( 3200001 <= charisma && charisma <= 3600000 )
		{
			symbol = new int[ ]
				{ 4 , 1 , 1 , 0 , 0 };
		}
		else if ( 3600001 <= charisma && charisma <= 4000000 )
		{
			symbol = new int[ ]
				{ 4 , 1 , 1 , 1 , 0 };
		}
		else if ( 4000001 <= charisma && charisma <= 4500000 )
		{
			symbol = new int[ ]
				{ 4 , 1 , 1 , 1 , 1 };
		}
		else if ( 4500001 <= charisma && charisma <= 5000000 )
		{
			symbol = new int[ ]
				{ 4 , 2 , 0 , 0 , 0 };
		}
		else if ( 5000001 <= charisma && charisma <= 5500000 )
		{
			symbol = new int[ ]
				{ 4 , 2 , 2 , 0 , 0 };
		}
		else if ( 5500001 <= charisma && charisma <= 6000000 )
		{
			symbol = new int[ ]
				{ 4 , 2 , 2 , 2 , 0 };
		}
		else if ( 6000001 <= charisma && charisma <= 6500000 )
		{
			symbol = new int[ ]
				{ 4 , 2 , 2 , 2 , 2 };
		}
		else if ( 6500001 <= charisma && charisma <= 7000000 )
		{
			symbol = new int[ ]
				{ 4 , 3 , 0 , 0 , 0 };
		}
		else if ( 7000001 <= charisma && charisma <= 7500000 )
		{
			symbol = new int[ ]
				{ 4 , 3 , 3 , 0 , 0 };
		}
		else if ( 7500001 <= charisma && charisma <= 8000000 )
		{
			symbol = new int[ ]
				{ 4 , 3 , 3 , 3 , 0 };
		}
		else if ( 8000001 <= charisma && charisma <= 8500000 )
		{
			symbol = new int[ ]
				{ 4 , 3 , 3 , 3 , 3 };
		}
		else if ( 8500001 <= charisma && charisma <= 9000000 )
		{
			symbol = new int[ ]
				{ 4 , 4 , 0 , 0 , 0 };
		}
		else if ( 9000001 <= charisma && charisma <= 9500000 )
		{
			symbol = new int[ ]
				{ 4 , 4 , 1 , 0 , 0 };
		}
		else if ( 9500001 <= charisma && charisma <= 10000000 )
		{
			symbol = new int[ ]
				{ 4 , 4 , 1 , 1 , 0 };
		}
		else if ( 10000001 <= charisma && charisma <= 13000000 )
		{
			symbol = new int[ ]
				{ 4 , 4 , 1 , 1 , 1 };
		}
		else if ( 13000001 <= charisma && charisma <= 16000000 )
		{
			symbol = new int[ ]
				{ 4 , 4 , 2 , 0 , 0 };
		}
		else if ( 16000001 <= charisma && charisma <= 20000000 )
		{
			symbol = new int[ ]
				{ 4 , 4 , 2 , 2 , 0 };
		}
		else if ( 20000001 <= charisma && charisma <= 24000000 )
		{
			symbol = new int[ ]
				{ 4 , 4 , 2 , 2 , 2 };
		}
		else if ( 24000001 <= charisma && charisma <= 28000000 )
		{
			symbol = new int[ ]
				{ 4 , 4 , 3 , 0 , 0 };
		}
		else if ( 28000001 <= charisma && charisma <= 32000000 )
		{
			symbol = new int[ ]
				{ 4 , 4 , 3 , 3 , 0 };
		}
		else if ( 32000001 <= charisma && charisma <= 36000000 )
		{
			symbol = new int[ ]
				{ 4 , 4 , 3 , 3 , 3 };
		}
		else if ( 36000001 <= charisma && charisma <= 40000000 )
		{
			symbol = new int[ ]
				{ 4 , 4 , 4 , 0 , 0 };
		}
		else if ( 40000001 <= charisma && charisma <= 45000000 )
		{
			symbol = new int[ ]
				{ 4 , 4 , 4 , 1 , 0 };
		}
		else if ( 45000001 <= charisma && charisma <= 50000000 )
		{
			symbol = new int[ ]
				{ 4 , 4 , 4 , 1 , 1 };
		}
		else if ( 50000001 <= charisma && charisma <= 55000000 )
		{
			symbol = new int[ ]
				{ 4 , 4 , 4 , 2 , 0 };
		}
		else if ( 55000001 <= charisma && charisma <= 60000000 )
		{
			symbol = new int[ ]
				{ 4 , 4 , 4 , 2 , 2 };
		}
		else if ( 60000001 <= charisma && charisma <= 65000000 )
		{
			symbol = new int[ ]
				{ 4 , 4 , 4 , 3 , 0 };
		}
		else if ( 65000001 <= charisma && charisma <= 70000000 )
		{
			symbol = new int[ ]
				{ 4 , 4 , 4 , 3 , 3 };
		}
		else if ( 70000001 <= charisma && charisma <= 75000000 )
		{
			symbol = new int[ ]
				{ 4 , 4 , 4 , 4 , 0 };
		}
		else if ( 75000001 <= charisma && charisma <= 80000000 )
		{
			symbol = new int[ ]
				{ 4 , 4 , 4 , 4 , 1 };
		}
		else if ( 80000001 <= charisma && charisma <= 85000000 )
		{
			symbol = new int[ ]
				{ 4 , 4 , 4 , 4 , 2 };
		}
		else if ( 85000001 <= charisma && charisma <= 90000000 )
		{
			symbol = new int[ ]
				{ 4 , 4 , 4 , 4 , 3 };
		}
		else if ( 90000001 <= charisma )
		{
			symbol = new int[ ]
				{ 4 , 4 , 4 , 4 , 4 };
		}
		return symbol;
	}

	*/
/** 重连需要重置的数据 **//*

	public static void reconnectResetData( )
	{
		if ( Common.getInstance( ).loginUser != null )
		{
			Common.getInstance( ).setNewFansCount( 0 );
		}
		Common.getInstance( ).setDynamicCount( 0 );
	}

	*/
/**
	 * 返回当前的语言索引：<br>
	 * 0:英文 ，1：中文简体，2：中文繁体（其他语言默认为0：英文）
	 *//*

	public static int getLanguageIndex( Context context )
	{
		String lang = PhoneInfoUtil.getInstance( context ).getLanguage( ) + "_";
		lang += PhoneInfoUtil.getInstance( context ).getSettingLang( ).toLowerCase( );
		int langIndex = 0;
		if ( lang.indexOf( "zh" ) > -1 )
		{
			if ( lang.indexOf( "cn" ) > -1 )
			{
				langIndex = 1;
			}
			else
			{
				langIndex = 2;
			}
		}
		return langIndex;
	}

	*/
/**
	 * 1:英文 ，2：中文简体，3：中文繁体（其他语言默认为1：英文）
	 * ( 在某些机型[ 如HTC 9088 ]上判断有误，推荐使用getLanguageIndex方法 )
	 * @param context
	 * @return
	 *//*

	public static int getLang( Context context )
	{
		int lang = 1;
		String language = PhoneInfoUtil.getInstance( context ).getSettingLang( ).toLowerCase( );
		if ( language != null )
		{
			if ( language.contains( "cn" ) )
			{
				lang = 2;
			}
			else if ( language.contains( "tw" ) || language.contains( "hk" ) )
			{
				lang = 3;
			}
		}
		return lang;
	}

	*/
/**
	 * 判断本地是否安装了该包
	 *
	 * @param context
	 * @param apkid
	 * @param @return
	 * @return boolean
	 *//*

	public static boolean isClientInstalled( Context context , String apkid )
	{
		if ( !TextUtils.isEmpty( apkid ) )
		{
			String lowAppLabel = apkid.trim( ).toLowerCase( );
			if ( lowAppLabel.length( ) > 0 )
			{
				PackageManager pm = context.getPackageManager( );
				List< PackageInfo > packs = pm.getInstalledPackages( 0 );
				for ( PackageInfo pack : packs )
				{
					String appLabel = pack.applicationInfo.packageName.toLowerCase( );
					if ( appLabel.equals( lowAppLabel ) )
					{
						return true;
					}
				}
			}
		}
		return false;
	}

	public static int isClientInstalled( Context context , String apkid , int versionCode )
	{
		if ( apkid != null )
		{
			String lowAppLabel = apkid.trim( ).toLowerCase( );
			if ( lowAppLabel.length( ) > 0 )
			{
				PackageManager pm = context.getPackageManager( );
				List< PackageInfo > packs = pm.getInstalledPackages( 0 );
				for ( PackageInfo pack : packs )
				{
					String appLabel = pack.applicationInfo.packageName.toLowerCase( );
					if ( appLabel.equalsIgnoreCase(lowAppLabel) )
					{
						return pack.versionCode < versionCode ? 0 : 1;
					}
				}
			}
		}
		return -1;
	}

	public static int isFaceInstalled( String faceId )
	{
		if ( faceId != null )
		{
			String facePath = getFacePath( faceId );
			File faceFileCache = new File( facePath );
			if ( faceFileCache != null && faceFileCache.exists( )
					&& faceFileCache.isDirectory( ) && faceFileCache.list( ).length > 0 )
			{
				return 1;
			}
			else
			{
				if ( faceFileCache != null )
				{
					faceFileCache.delete( );
				}
			}
		}
		return -1;
	}

	public static String getFacePath( String faceId )
	{
		return getSDPath( ) + "/face/" + Common.getInstance( ).loginUser.getUid( ) + "/"
				+ faceId;
	}

	*/
/**
	 * 打开本地apk
	 *
	 * @param context
	 * @param apkid
	 * @return void
	 *//*

	public static void launchClient( Context context , String apkid )
	{
		if ( apkid != null && apkid.length( ) > 0 )
		{
			PackageManager pm = context.getPackageManager( );
			Intent intent = pm.getLaunchIntentForPackage( apkid );
			if ( intent != null )
			{
				context.startActivity( intent );
			}
		}
	}

	*/
/**
	 * 获取SD卡剩余空间
	 *
	 * @Title: getAvailaleSize
	 * @return long
	 *//*

	public static long getAvailaleSize( )
	{
		try
		{
			File path = Environment.getExternalStorageDirectory( ); // 取得sdcard文件路径
			StatFs stat = new StatFs( path.getPath( ) );
			long blockSize = stat.getBlockSize( );
			long availableBlocks = stat.getAvailableBlocks( );
			return ( availableBlocks * blockSize ) / 1024 / 1024; // 获取可用大小
		}
		catch ( Exception e )
		{
			return 100;
		}
		// return totalBolcks*blockSize;//获取存储空间
		// (availableBlocks * blockSize)/1024 KIB 单位
		// (availableBlocks * blockSize)/1024 /1024 MIB单位
	}

	*/
/**
	 * 判断字符串是否为空字符串、null或“null”字符串包括所有大小写情况
	 *
	 * @param str
	 * @return 是否为空
	 *//*

	public static boolean isEmptyOrNullStr( String str )
	{
		return TextUtils.isEmpty( str ) || "".equals( str );
	}

	*/
/**
	 *
	 * @param str
	 * @return判断字符串是否是英文
	 *//*

	public static boolean isString( String str )
	{
		boolean matches = str.matches( "^[a-zA-Z]*" );
		return matches;

	}

	public static Bitmap createBitmap( String bmPath , Options opt )
	{
		try
		{
			opt.inPreferredConfig = Bitmap.Config.RGB_565;
			opt.inPurgeable = true;
			opt.inInputShareable = true;
			FileInputStream fis = new FileInputStream( bmPath );
			return BitmapFactory.decodeStream( fis , null , opt );
		}
		catch ( Throwable t )
		{
			log( t );
		}
		return null;
	}

	// 压缩输出文件
	public static String compressImage( String sourcePath ) throws Exception
	{
		File uploadFile = new File( sourcePath );
		String tmpFilePath = "";

		if ( uploadFile.exists( ) )
		{
			tmpFilePath = PathUtil.getPictureDir( ) + CryptoUtil.md5( sourcePath );
			File tmpFile = new File( tmpFilePath );
			tmpFile.createNewFile( );

			Options opts = new Options();
			opts.inJustDecodeBounds = true;
			BitmapFactory.decodeFile( uploadFile.getAbsolutePath( ),opts );
			int h=opts.outHeight;
			int w = opts.outWidth;
			boolean isCompress = false;
			opts.inSampleSize = 1;
			if(opts.outHeight > 1280||opts.outWidth >720)
			{
				isCompress = true;
				w = opts.outWidth*1280/opts.outHeight;
				h = opts.outHeight *720/opts.outWidth;
				if(h < 1280)
				{
					w = 720;
					opts.inSampleSize = opts.outHeight / 1280;
				}
				else
				{
					h = 1280;
					opts.inSampleSize = opts.outWidth / 720;
				}
			}

			opts.inJustDecodeBounds = false;
			Bitmap bitmap = BitmapFactory.decodeFile( uploadFile.getAbsolutePath( ),opts );
			FileOutputStream fos = new FileOutputStream( tmpFile );
			BufferedOutputStream bos = new BufferedOutputStream( fos );
			if(isCompress&& (w!=opts.outWidth || h!=opts.outHeight ))
			{
				Bitmap outBitmap = Bitmap.createScaledBitmap(bitmap,w,h ,true);
				outBitmap.compress( CompressFormat.JPEG , 30 , bos );
				outBitmap.recycle();
			}
			else
			{
				bitmap.compress( CompressFormat.JPEG , 30 , bos );
			}
			bitmap.recycle();
			bos.flush( );
			fos.close( );
			bos.close( );
		}
		else
		{
			return tmpFilePath;
		}

		return tmpFilePath;
	}


	public static Bitmap createBitmap( Bitmap oriBm )
	{
		try
		{
			return Bitmap.createBitmap( oriBm );
		}
		catch ( OutOfMemoryError e )
		{
			System.gc( );
			log( e );
			try
			{
				log( "System.out" , "try to creat the bitmap again" );
				return Bitmap.createBitmap( oriBm );
			}
			catch ( Throwable t )
			{
				log( t );
			}
		}
		catch ( Throwable t )
		{
			log( t );
		}
		finally
		{
			System.gc( );
		}
		return null;
	}

	public static Bitmap createBitmap( Bitmap source , int x , int y , int width , int height ,
			Matrix m , boolean filter )
	{
		try
		{
			return Bitmap.createBitmap( source , x , y , width , height , m , filter );
		}
		catch ( OutOfMemoryError e )
		{
			System.gc( );
			log( e );
			try
			{
				log( "System.out" , "try to creat the bitmap again" );
				return Bitmap.createBitmap( source , x , y , width , height , m , filter );
			}
			catch ( Throwable t )
			{
				log( t );
			}
		}
		catch ( Throwable t )
		{
			log( t );
		}
		finally
		{
			System.gc( );
		}
		return null;
	}

	public static Bitmap createBitmap( int width , int height , Bitmap.Config config )
	{
		try
		{
			return Bitmap.createBitmap( width , height , config );

		}
		catch ( OutOfMemoryError e )
		{
			System.gc( );
			log( e );
			try
			{
				log( "System.out" , "try to creat the bitmap again" );
				return Bitmap.createBitmap( width , height , config );
			}
			catch ( Throwable t )
			{
				log( t );
			}
		}
		catch ( Throwable t )
		{
			log( t );
		}
		finally
		{
			System.gc( );
		}
		return null;
	}

	public static Bitmap createBitmap( Bitmap source , int x , int y , int width , int height )
	{
		try
		{
			return Bitmap.createBitmap( source , x , y , width , height );
		}
		catch ( OutOfMemoryError e )
		{
			System.gc( );
			log( e );
			try
			{
				log( "System.out" , "try to creat the bitmap again" );
				return Bitmap.createBitmap( source , x , y , width , height );
			}
			catch ( Throwable t )
			{
				log( t );
			}
		}
		catch ( Throwable t )
		{
			log( t );
		}
		finally
		{
			System.gc( );
		}
		return null;
	}

	*/
/** 旋转图片,并保存 *//*

	public static String rotaingImage( String path )
	{
		String outputPath = PathUtil.getImageRotatePath( path );

		try
		{
			Bitmap bitmap = BitmapFactory.decodeFile( path );
			int degree = PhotoCropActivity.readPictureDegree( path );
			// if (degree == 0) {
			// outputPath = path;
			// } else {
			//
			// }

			File outputFile = new File( outputPath );
			if ( !outputFile.exists( ) )
			{
				outputFile.createNewFile( );

				bitmap = PhotoCropActivity.rotaingImageView( degree , bitmap );

				FileOutputStream os = new FileOutputStream( outputFile );
				bitmap.compress( CompressFormat.JPEG , 100 , os );
				os.flush( );
				os.close( );
			}

		}
		catch ( Exception e )
		{
			e.printStackTrace( );
			outputPath = path;
		}

		return outputPath;
	}

	public static String isRotatedFileExsit( String path )
	{
		String outputPath = PathUtil.getImageRotatePath( path );
		File outputFile = new File( outputPath );
		if ( outputFile.exists( ) )
		{
			return outputPath;
		}
		return "";
	}

	*/
/** 判断邮箱地址是否有效限 *//*

	public static boolean isEmailAddValid( String address )
	{
		if ( address != null && address.length( ) > 0 )
		{
			char[ ] cAddress = address.toCharArray( );
			for ( char c : cAddress )
			{
				if ( c > 127 )
				{
					return false;
				}
			}

			Pattern p = Pattern.compile( "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*" );
			Matcher m = p.matcher( address );
			return m.matches( );
		}
		return false;
	}

	public static final String full2HalfChange( String QJstr )
			throws UnsupportedEncodingException
	{
		if ( TextUtils.isEmpty( QJstr ) )
		{
			return QJstr;
		}

		StringBuffer outStrBuf = new StringBuffer( "" );
		String Tstr = "";
		byte[ ] b = null;
		for ( int i = 0 ; i < QJstr.length( ) ; i++ )
		{
			Tstr = QJstr.substring( i , i + 1 );
			// 全角空格转换成半角空格
			if ( Tstr.equals( " " ) )
			{
				outStrBuf.append( "" );
				continue;
			}
			b = Tstr.getBytes( "unicode" );
			// 得到 unicode 字节数据
			if ( b[ 2 ] == -1 )
			{
				b[ 3 ] = ( byte ) ( b[ 3 ] + 32 );
				b[ 2 ] = 0;
				outStrBuf.append( new String( b , "unicode" ) );
			}
			else
			{
				outStrBuf.append( Tstr );
			}
		} // end for.
		return outStrBuf.toString( ).trim( );
	}

	*/
/**
	 * 判断密码是否有效
	 * <p>
	 * 0 -- 非法；1 -- 正确； 2 -- 不一致
	 *//*

	public static int isPasswordValid( String password , String repeated )
	{
		if ( password != null )
		{
			int len = password.length( );
			if ( len >= 6 && len <= 16 )
			{
				char[ ] cPsw = password.toCharArray( );
				boolean wrongChar = false;
				for ( char c : cPsw )
				{
					if ( c >= 128 )
					{ // 找到非ascii码
						wrongChar = true;
						break;
					}
				}
				if ( !wrongChar )
				{
					return password.equals( repeated ) ? 1 : 2;
				}
			}
		}
		return 0;
	}

	*/
/**
	 * 清楚数据
	 *
	 * @param context
	 * @return void
	 *//*

	public static void clearData( Context context )
	{
		ChatPersonalModel.getInstance( ).reset( );
		FocusModel.getInstance( ).reset( );
		FriendModel.getInstance( context ).reset( );
		MessageModel.getInstance( ).reset( );
		SpaceModel.getInstance( context ).reset( );
		SpaceModelNew.getInstance( context ).reset( );
		UserBufferHelper.reset( );
		StartModel.getInstance( ).reset( );
		PayModel.getInstance( ).reset( );
		KeyWord.getInstance( context ).reset( );
		GroupChatListModel.getInstance( ).reset( );
		GroupModel.getInstance( ).reset( );
		DynamicModel.getInstent( ).reset( );
		TopicModel.getInstance( ).reset( );
		PostbarModel.getInstance( ).reset( );
		// BaiduLocationUtil.getInstance( context ).removeLister( );
		AMapLocationUtil.getInstance( context ).removeListener( );
		GoogleLocationUtil.getInstance( context ).removeLister( );

		ConnectorManage.getInstance( context ).reset( );
		Common.getInstance( ).reset( );

		FaceCenterModel.getInstance( context ).reset( );
	}

	public static int advParseInt( String intStr , int defaultValue )
	{
		int value = defaultValue;
		try
		{
			value = Integer.parseInt( intStr );
		}
		catch ( Throwable t )
		{
		}
		return value;
	}

	*/
/**
	 * 显示用户列表右边图标
	 *
	 * @param layout
	 * @param weiboList
	 * @param context
	 * @return void
	 *//*

	public static void showRightIcon( LinearLayout layout , ArrayList< WeiboState > weiboes ,
			Context context )
	{
		if ( layout == null )
		{
			return;
		}

		layout.removeAllViews( );

		if ( weiboes == null )
		{
			return;
		}

		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams( dipToPx( context , 23 ) ,
				dipToPx( context , 13 ) );
		lp.rightMargin = 5;

		// 列表右边图标
		ArrayList< WeiboState > weiboList = weiboes;
		if ( weiboList != null && weiboList.size( ) > 0 )
		{
			for ( WeiboState weibo : weiboList )
			{
				if ( weibo != null )
				{
					ImageView weiboIcon = null;
					switch ( weibo.getType( ) )
					{
						case SinaWeiboUtil.ID : // 新浪微博
							if ( weibo.getVerifiedType( ) == 0 )
							{ // 个人加v
								weiboIcon = new ImageView( context );
								weiboIcon.setImageResource( R.drawable.weibo_sina_v );
							}
							else if ( weibo.getVerifiedType( ) == 2 )
							{ // 团体加v
								weiboIcon = new ImageView( context );
								weiboIcon.setImageResource( R.drawable.weibo_sina_bv );
							}
							else if ( weibo.getVerifiedType( ) == 220 )
							{ // 微博达人
								weiboIcon = new ImageView( context );
								weiboIcon.setImageResource( R.drawable.weibo_sina_dr );
							}
							else if ( weibo.getVerifiedType( ) == -1 )
							{ // 普通用户
								weiboIcon = new ImageView( context );
								weiboIcon.setImageResource( R.drawable.weibo_sina );
							}
							break;
						case 99 : // 游戏达人图标
							weiboIcon = new ImageView( context );
							weiboIcon.setImageResource( R.drawable.game_level1 );
							break;
					}

					if ( weiboIcon != null )
					{
						layout.addView( weiboIcon , lp );
					}
				}
			}
		}
	}

	public static void showRightIcon( LinearLayout layout , User user , Context context )
	{
		showRightIcon( layout , user.getWeibo( ) , context );
	}

	*/
/**
	 * 用户列表显示“weibos”字段的图标
	 *
	 * @author tanzy
	 * *//*

	public static void showWeibosIcon( ImageView[ ] weiboIcons ,
			ArrayList< WeiboState > weiboes , int jobId , Context context )
	{
		*/
/*
		 * weiboIcons数组，0：圈主，1：职业，2：微博，3：游戏，4：真心话，5：辣椒
		 * 现将所有imageview设置为GONE，遍历weiboes时显示对应位置的view
		 *//*


		int showWeiboType = 0;// 要显示的微博类型ID
		int verifiedType = -1;// 当是新浪微博时需判断认证类型

		if ( weiboIcons == null || weiboIcons.length == 0 )
			return;
		for ( int i = 0 ; i < weiboIcons.length ; i++ )
		{
			weiboIcons[ i ].setVisibility( View.GONE );
		}

		for ( int i = 0 ; i < weiboes.size( ) ; i++ )
		{
			WeiboState ws = weiboes.get( i );
			switch ( ws.getType( ) )
			{
				case 12 :// 新浪微博
				case 25 :// QQ空间
				case 1 :// 腾讯微博
				case 24 :// facebook
				case 23 :// twitter
				{// 根据权重判断应显示那个图标
					if ( showWeiboType == 0 )
						showWeiboType = ws.getType( );
					else
					{
						if ( WeiboesMaps.weiboWeight.get( ws.getType( ) ) > WeiboesMaps.weiboWeight
								.get( showWeiboType ) )
							showWeiboType = ws.getType( );
						if ( showWeiboType == 12 )
							verifiedType = ws.getVerifiedType( );
						else
							verifiedType = -1;
					}
				}
					break;
				case 981 :// 圈主
					//6.0去掉圈子标识
					//weiboIcons[ 0 ].setVisibility( View.VISIBLE );
					break;
				case 982 :// 辣椒
					//weiboIcons[ 5 ].setVisibility( View.VISIBLE );
					break;
				case 992 :// 真心话
					//weiboIcons[ 4 ].setVisibility( View.VISIBLE );
					break;
				case 991 :// 游戏
					//weiboIcons[ 3 ].setVisibility( View.VISIBLE );
					break;
				default :
					break;
			}
		}

		// 职业
		int jobImageID = 0;
		int langIndex = getLanguageIndex( context );// 判断语言
		if ( langIndex == 1 )
			jobImageID = WeiboesMaps.jobToImage_simple.get( jobId ) == null ? 0
					: WeiboesMaps.jobToImage_simple.get( jobId );
		else if ( langIndex == 2 )
			jobImageID = WeiboesMaps.jobToImage_tradition.get( jobId ) == null ? 0
					: WeiboesMaps.jobToImage_tradition.get( jobId );
		else
			jobImageID = WeiboesMaps.jobToImage_english.get( jobId ) == null ? 0
					: WeiboesMaps.jobToImage_tradition.get( jobId );

		if ( jobImageID != 0 )
		{
			weiboIcons[ 1 ].setVisibility( View.VISIBLE );
			weiboIcons[ 1 ].setImageResource( jobImageID );
		}
		else
		{
			weiboIcons[ 1 ].setVisibility( View.GONE );
		}

		// 微博（6.0不再显示）
		*/
/*int weiboImageID = 0;
		switch ( showWeiboType )
		{
			case 12 :// 新浪微博
			{
				switch ( verifiedType )
				{
					case 0 :// 加v
						weiboImageID = R.drawable.weibos_sina_v;
						break;
					case 220 :// 达人
						weiboImageID = R.drawable.weibos_sina_daren;
						break;
					case 2 :// 企业
						weiboImageID = R.drawable.weibos_sina_company;
						break;
					case -1 :// 普通用户
						weiboImageID = R.drawable.weibos_sina_normal;
						break;
					default :// 普通用户
						weiboImageID = R.drawable.weibos_sina_normal;
						break;
				}
			}
				break;
			case 25 :// QQ空间
				weiboImageID = R.drawable.weibos_qzone;
				break;
			case 1 :// 腾讯微博
				weiboImageID = R.drawable.weibos_qq_weibo;
				break;
			case 24 :// facebook
				weiboImageID = R.drawable.weibos_facebook;
				break;
			case 23 :// twitter
				weiboImageID = R.drawable.weibos_twitter;
				break;
		}
		if ( weiboImageID != 0 )
		{
			weiboIcons[ 2 ].setVisibility( View.VISIBLE );
			weiboIcons[ 2 ].setImageResource( weiboImageID );
		}
		else
			weiboIcons[ 2 ].setVisibility( View.GONE );*//*


	}

	*/
/**
	 * 显示用户列表右边图标
	 *
	 * @param layout
	 * @param weiboList
	 * @param context
	 * @return void
	 *//*

	public static void showRightIcon( LinearLayout layout , GroupSearchUser user ,
			Context context )
	{
		if ( user.user != null )
		{
			showRightIcon( layout , User.parseWeiboStr( user.user.weibo ) , context );
		}
	}

	*/
/**
	 * 现在关注状态的标示
	 *
	 * @param imageview
	 * @param context
	 *//*

	public static void showAttentionIcon( ImageView imageview , Context context )
	{
		imageview.setVisibility( View.VISIBLE );
		imageview.setImageResource( R.drawable.relation_all_heart );
	}

	*/
/**
	 * 当域名无法解析时，则使用下发替换的方案。 由服务端下发可被路由的域名，然后由客户端做替换（目前使用在图片访问、视频音频播放）
	 *
	 * @param originalUrl
	 * @return String
	 *//*

	public static String replaceUrl( String originalUrl )
	{
		if ( !isEmptyOrNullStr( Config.sPictureUrlBak ) && !isEmptyOrNullStr( originalUrl ) )
		{
			String tmpUrl = originalUrl.substring( 0 , originalUrl.indexOf( "/" , 8 ) );// 排除"http://"
			if ( tmpUrl.contains( "iaround" ) )
			{ // 避免浏览其它url的域名时，被强制替换
				int index = tmpUrl.length( ) - tmpUrl.lastIndexOf( "." ) + "iaround".length( );
				String strArr = tmpUrl.substring( tmpUrl.length( ) - index , tmpUrl.length( ) );
				originalUrl = originalUrl.replace( strArr , Config.sPictureUrlBak );
			}
		}
		return originalUrl;
	}

	public static Bitmap createBitmap( InputStream is )
	{
		Options opt = new Options( );
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inPurgeable = true;
		opt.inInputShareable = true;
		opt.inSampleSize = 2;
		return BitmapFactory.decodeStream( is , null , opt );
	}

	public static Bitmap createBitmapFillWindow( String path , Context context )
	{
		int reqWidth = ( int ) ( context.getResources( ).getDisplayMetrics( ).widthPixels * 1.2 );
		int reqHeight = ( int ) ( context.getResources( ).getDisplayMetrics( ).heightPixels * 1.2 );

		final Options opt = new Options( );
		opt.inJustDecodeBounds = true;
		BitmapFactory.decodeFile( path , opt );
		opt.inSampleSize = calculateInSampleSize( opt , reqWidth , reqHeight );
		opt.inJustDecodeBounds = false;
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inPurgeable = true;
		opt.inInputShareable = true;

		return BitmapFactory.decodeFile( path , opt );
	}

	public static int calculateInSampleSize( Options options , int reqWidth ,
			int reqHeight )
	{
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if ( height > reqHeight || width > reqWidth )
		{
			if ( width > height )
			{
				inSampleSize = Math.round( ( float ) height / ( float ) reqHeight );
			}
			else
			{
				inSampleSize = Math.round( ( float ) width / ( float ) reqWidth );
			}
		}
		return inSampleSize;
	}

	*/
/** 使用c层代码早图片 *//*

	public static Bitmap createBitmap( String path ) throws FileNotFoundException
	{
		FileInputStream fis = new FileInputStream( path );
		return createBitmap( fis );
	}

	*/
/**
	 * 获取系统的总内存（单位Byte）
	 *
	 * @return
	 *//*

	public static long getTotalMemory( )
	{
		try
		{
			FileReader localFileReader = new FileReader( "/proc/meminfo" );
			BufferedReader localBufferedReader = new BufferedReader( localFileReader );
			String memTotal = localBufferedReader.readLine( );
			localFileReader.close( );

			String regEx = "\\d{1,}";
			Pattern p = Pattern.compile( regEx );
			Matcher m = p.matcher( memTotal );
			if ( m.find( ) )
			{
				return ( Long.parseLong( m.group( 0 ) ) * 1024 );
			}
		}
		catch ( Throwable e )
		{
			log( e );
		}
		return -1;
	}

	*/
/** dip转px *//*

	public static int dipToPx( Context context , int dip )
	{
		if ( density <= 0 )
		{
			density = context.getResources( ).getDisplayMetrics( ).density;
		}
		return ( int ) ( dip * density + 0.5f );
	}

	*/
/** px转dip *//*

	public static int pxToDip( Context context , int px )
	{
		if ( density <= 0 )
		{
			density = context.getResources( ).getDisplayMetrics( ).density;
		}
		return ( int ) ( ( px - 0.5f ) / density );
	}

	*/
/**
	 * 判断是否为模拟器执行
	 *
	 * @param context
	 * @return
	 *//*

	public boolean isEmulator( Context context )
	{
		if ( Config.DEBUG )
		{
			return false;
		}

		String android_id = Settings.Secure.getString( context.getContentResolver( ) ,
				Settings.Secure.ANDROID_ID );
		boolean emulator = isEmptyOrNullStr( android_id )
				|| "google_sdk".equals( Build.PRODUCT ) || "sdk".equals( Build.PRODUCT );
		return emulator;
	}

	*/
/** 显示“心”形标识 *//*

	public static void showRelationIcon( View parentView , LinearLayout layout , User user ,
			Context context )
	{
		// System.out.println("==="+parentView.getClass()+"==="+user.getRelationship()+"==="+user.getNickname());
		if ( layout == null )
		{
			return;
		}
		layout.removeAllViews( );
		int resId = 0;
		// if ( parentView instanceof MyFriendFansView )
		// {
		// if ( user.isMyFriend( ) )
		// { // 好友
		// resId = R.drawable.relation_all_heart;
		// }
		// else
		// {
		// return;
		// }
		// }
		// else if ( parentView instanceof MyNearContact )
		// {
		// if ( user.isMyFriend( ) )
		// { // 好友
		// resId = R.drawable.relation_all_heart;
		// }
		// else if ( user.isMyFans( ) )
		// { // 粉丝
		// resId = R.drawable.relation_right_heart;
		// }
		// else if ( user.isMyFollowing( ) )
		// { // 关注
		// resId = R.drawable.relation_left_heart;
		// }
		// }
		if ( resId != 0 )
		{
			ImageView relationIcon = new ImageView( context );
			relationIcon.setImageResource( resId );
			int dp_23 = ( int ) ( context.getResources( ).getDimension( R.dimen.dp_1 ) * 23 );
			int dp_13 = ( int ) ( context.getResources( ).getDimension( R.dimen.dp_1 ) * 13 );
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams( dp_23 , dp_13 );
			lp.rightMargin = 5;
			layout.addView( relationIcon , lp );
		}
	}

	*/
/**
	 * 过滤掉 \r 换行 \n回车
	 *
	 * @param str
	 * @return
	 *//*

	public static String replaceBlank( String str )
	{
		String dest = "";
		if ( str != null )
		{
			Pattern p = Pattern.compile( "\\r+|\n+" );
			Matcher m = p.matcher( str );
			dest = m.replaceAll( " " );// .replaceAll(" +", " ");
		}
		return dest;
	}

	*/
/**
	 * 取代多余的换行
	 *
	 * @param str
	 * @return
	 *//*

	public static String replaceLineFeed( String str )
	{
		String dest = "";
		if ( str != null )
		{
			Pattern p = Pattern.compile( "\\n{2,}" );
			Matcher m = p.matcher( str );
			dest = m.replaceAll( "\n" );
		}
		return dest;
	}

	*/
/** 获取指定View的截图 *//*

	public static Bitmap captureView( View view ) throws Throwable
	{
		view.setDrawingCacheEnabled( true );
		view.buildDrawingCache( );
		Bitmap bmShare = view.getDrawingCache( );
		if ( bmShare == null )
		{
			// 使用反射，强制将mViewFlags设置为正确的数值
			Field mViewFlagsField = View.class.getDeclaredField( "mViewFlags" );
			mViewFlagsField.setAccessible( true );
			mViewFlagsField.set( view , Integer.valueOf( 402685952 ) );
			bmShare = view.getDrawingCache( );
		}
		return bmShare;
	}

	*/
/**
	 * 创建指定bitmap的倒影bitmap
	 *
	 * @param bitmap
	 *            原始的图片
	 * @param refMargin
	 *            倒影和原图之间的距离
	 * @param refLeng
	 *            倒影的长度
	 *//*

	public static Bitmap getInvertedImage( Bitmap bitmap , int refMargin , int refLeng )
	{
		int width = bitmap.getWidth( );
		int bmHeight = bitmap.getHeight( );
		int height = bmHeight + refMargin + refLeng;
		Bitmap bm = Bitmap.createBitmap( width , height , Bitmap.Config.ARGB_8888 );

		Canvas canvas = new Canvas( );
		canvas.setBitmap( bm );
		Paint paint = new Paint( );
		canvas.drawBitmap( bitmap , 0 , 0 , paint );

		if ( refLeng > 0 )
		{
			Matrix matrix = new Matrix( );
			matrix.preScale( 1 , -1 );
			Bitmap refBm = Bitmap.createBitmap( bitmap , 0 , bmHeight - refLeng , width ,
					refLeng , matrix , false );
			canvas.drawBitmap( refBm , 0 , bmHeight + refMargin , paint );
			refBm.recycle( );

			paint.setAntiAlias( true );
			LinearGradient gradient = new LinearGradient( 0 , bmHeight + refMargin , 0 ,
					height , 0xff000000 , 0x0000000 , Shader.TileMode.CLAMP );
			paint.setShader( gradient );
			paint.setXfermode( new PorterDuffXfermode( PorterDuff.Mode.DST_IN ) );
			canvas.drawRect( 0 , bmHeight + refMargin , width , height , paint );
		}

		return bm;
	}

	public static Bitmap getImageReflectionPart( Bitmap bitmap , int refLeng )
	{

		if ( bitmap == null )
			return null;
		int width = bitmap.getWidth( );
		int bmHeight = bitmap.getHeight( );

		try
		{


			Bitmap bm = Bitmap.createBitmap( width , refLeng , Bitmap.Config.ARGB_8888 );
			Canvas canvas = new Canvas( );
			canvas.setBitmap( bm );
			Paint paint = new Paint( );

			Matrix matrix = new Matrix( );
			matrix.preScale( 1 , -1 );
			Bitmap refBm = CommonFunction.createBitmap( bitmap , 0 , 0 , width , bmHeight ,
					matrix , true );
			canvas.drawBitmap( refBm , 0 , 0 , paint );
			refBm.recycle( );

			paint.setAntiAlias( true );
			LinearGradient gradient = new LinearGradient( 0 , 0 , 0 , refLeng , 0xc0000000 ,
					0x00000000 , Shader.TileMode.CLAMP );
			paint.setShader( gradient );
			paint.setXfermode( new PorterDuffXfermode( PorterDuff.Mode.DST_IN ) );
			canvas.drawRect( 0 , 0 , width , refLeng , paint );

			return bm;
		}
		catch ( Exception e )
		{
			CommonFunction.log( e );
		}
		return null;
	}

	*/
/**
	 * 生成阴影
	 *
	 * @param bitmap
	 * @param shadow
	 * @return
	 *//*

	public static Bitmap getShadowBitmap( Bitmap bitmap , int shadow )
	{
		if ( shadow <= 0 )
		{
			return bitmap;
		}

		BlurMaskFilter filter = new BlurMaskFilter( shadow , BlurMaskFilter.Blur.OUTER );
		Paint shadowPaint = new Paint( );
		shadowPaint.setMaskFilter( filter );

		int[ ] offsetXY = new int[ ]
			{ shadow , shadow };
		return bitmap.extractAlpha( shadowPaint , offsetXY );
	}

	*/
/**
	 * 获取图片的分辨率
	 *
	 * @param path
	 * @return
	 * @throws Throwable
	 *//*

	public static int[ ] getBitmapSize( String path ) throws Throwable
	{
		Options opt = new Options( );
		opt.inJustDecodeBounds = true;
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inPurgeable = true;
		opt.inInputShareable = true;
		FileInputStream fis = new FileInputStream( path );
		BitmapFactory.decodeStream( fis , null , opt );
		int[ ] size = new int[ ]
			{ opt.outWidth , opt.outHeight };
		return size;
	}

	public static int getAge( long birthDayMilsec )
	{
		Calendar birthCal = Calendar.getInstance( );
		birthCal.setTimeInMillis( birthDayMilsec );
		int birthYear = birthCal.get( Calendar.YEAR );
		int birthMonth = birthCal.get( Calendar.MONTH );
		int birthDay = birthCal.get( Calendar.DAY_OF_YEAR );

		Calendar curCal = Calendar.getInstance( );
		curCal.setTimeInMillis( System.currentTimeMillis( )
				+ Common.getInstance( ).serverToClientTime );
		int curYear = curCal.get( Calendar.YEAR );
		int curMonth = curCal.get( Calendar.MONTH );
		int curDay = curCal.get( Calendar.DAY_OF_YEAR );

		int age = curYear - birthYear;
		if ( age <= 0 )
		{
			return 0;
		}

		// 月份还没到
		if ( curMonth < birthMonth )
		{
			age--;
			if ( age <= 0 )
			{
				return 0;
			}
			return age;
		}

		// 月份已经超过了
		if ( curMonth > birthMonth )
		{
			return age;
		}

		// 日期还没到
		if ( curDay < birthDay )
		{
			age--;
			if ( age <= 0 )
			{
				return 0;
			}
		}

		return age;
	}

	public static void quit( Context context )
	{
		APKDownloadService.stopAllTask( context );

		NotificationManager nm = ( NotificationManager ) context
				.getSystemService( Context.NOTIFICATION_SERVICE );
		nm.cancelAll( );

		ConnectLogin.getInstance( context ).logout( context , 1 );

		ActivityManager am = ( ActivityManager ) context
				.getSystemService( Context.ACTIVITY_SERVICE );
		try
		{
			Method killBackgroundProcesses = am.getClass( ).getDeclaredMethod(
					"killBackgroundProcesses" , String.class );
			killBackgroundProcesses.setAccessible( true );
			killBackgroundProcesses.invoke( am , context.getPackageName( ) );
		}
		catch ( Exception e )
		{
			CommonFunction.log( e );
		}

		Intent intent = new Intent( Intent.ACTION_MAIN );
		intent.addCategory( Intent.CATEGORY_HOME );

		intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
		context.startActivity( intent );

		System.exit( 0 );
	}

	public static void showGuidView( Activity context )
	{
		showGuidView( context , 0 , null );
	}

	public static void showGuidView( Activity context , int code )
	{
		showGuidView( context , code , null );
	}

	// 判断是否需要显示引导页面
	public static void showGuidView( Activity context , int code , Bundle params )
	{
		if ( context == null )
		{
			return;
		}

		// 第一次安装打开引导
		SharedPreferenceUtil sp = SharedPreferenceUtil.getInstance( context );
		// 当前的版本versionCode;
		int versionCode = 0;
		try
		{
			PackageManager pm = context.getPackageManager( );
			PackageInfo pinfo = pm.getPackageInfo( context.getPackageName( ) ,
					PackageManager.GET_CONFIGURATIONS );
			versionCode = pinfo.versionCode;
		}
		catch ( Throwable e )
		{
			log( e );
		}

		if ( code <= 0 )
		{
			if ( ( !sp.has( SharedPreferenceUtil.VERSION_CODE ) || ( sp
					.getInt( SharedPreferenceUtil.VERSION_CODE ) < versionCode ) ) )
			{
				int type = BaseHttp.checkNetworkType( context );
				// 定时检测网络连接情况
				if ( type == BaseHttp.TYPE_NET_WORK_DISABLED )
				{
					Intent loginMainIntent = new Intent( context , LoginMainActivity.class );
					context.startActivity( loginMainIntent );
					context.finish( );
				}
				else
				{

					CommonFunction.log(
							"--->SharedPreferenceUtil" ,
							versionCode + ":SharedPreferenceUtil:"
									+ sp.getInt( SharedPreferenceUtil.VERSION_CODE ) );
					String userKey = "_" + Common.getInstance( ).loginUser.getUid( );
					sp.putBoolean( SharedPreferenceUtil.SIDEBAR_GUIDE_SHOWN + userKey , false );
					sp.putBoolean( SharedPreferenceUtil.NEARBY_GUIDE_SHOWN + userKey , false );
					sp.putInt( SharedPreferenceUtil.VERSION_CODE , versionCode );

					// Intent guidIntent = new Intent(context,
					// GuideActivity.class);
					Intent guidIntent = new Intent( context , GuideActivity.class );
					guidIntent.putExtra( "showmain" , true );
					context.startActivity( guidIntent );
				}
			}
			else
			{
				Intent intent = new Intent( context , MainActivity.class );
				context.startActivity( intent );
				return;
			}
		}
		else if ( code == 1 && params != null )
		{
			( ( BaseApplication ) context.getApplication( ) ).setReturnStart( true );
			// 跳转到主页
			Intent intent = new Intent( context , MainActivity.class );
			intent.putExtra( "ChatData" , params.getSerializable( "ChatData" ) );
			intent.putExtra( "tab" , 2 );
			context.startActivity( intent );
			return;
		}
		else if ( code == 2 && params != null )
		{
			( ( BaseApplication ) context.getApplication( ) ).setReturnStart( true );
			// 跳转到主页
			Intent intent = new Intent( context , MainActivity.class );
			intent.putExtra( "SpaceData_uid" , params.getSerializable( "SpaceData_uid" ) );
			intent.putExtra( "tab" , 2 );
			context.startActivity( intent );
			return;
		}
	}

	public static void showUpdateDialog( final Context activity )
	{
		if ( Common.getInstance( ).versionFlag == 0 )
		{
			DialogUtil.showOKDialog( activity , R.string.check_update ,
					R.string.this_is_the_latest_version , null );
		}
		else
		{
			String msg = null;
			if ( !TextUtils.isEmpty( Common.getInstance( ).versionContent ) )
			{
				msg = Common.getInstance( ).versionContent;
			}
			else
			{
				msg = activity.getString( R.string.version_description );
			}

			OnClickListener onclickListener = new OnClickListener( )
			{

				@Override
				public void onClick( View v )
				{
					// 是否强制升级
					boolean isFocusUpdate = Common.getInstance( ).versionFlag == 2 ? true
							: false;
					if ( !PathUtil.isSDcardExist( ) )// sd卡不存在
					{
						String noSDCardMsg = activity
								.getString( R.string.download_new_version_no_sdcard );
						CommonFunction.showToast( activity , noSDCardMsg , 0 );
						return;
					}

					if ( !DownloadNewVersionTask.getInstance( activity , isFocusUpdate )
							.isDownloading( ) )
					{
						// 当前没有正在下载
						DownloadNewVersionTask.getInstance( activity , isFocusUpdate ).reset( );
						DownloadNewVersionTask.getInstance( activity , isFocusUpdate )
								.execute( Common.getInstance( ).currentSoftUrl );
					}
				}
			};

			CustomDialog updateDialog = null;
			// 强制升级
			if ( Common.getInstance( ).versionFlag == 2 )
			{
				updateDialog = new CustomDialog( activity ,
						activity.getString( R.string.version_update ) , msg ,
						activity.getString( R.string.immediate_update ) , onclickListener );
				updateDialog.setCancelable( false );
			}
			else
			{
				updateDialog = new CustomDialog( activity ,
						activity.getString( R.string.version_update ) , msg ,
						activity.getString( R.string.wait_update ) , null ,
						activity.getString( R.string.immediate_update ) , onclickListener );
			}

			try
			{
				updateDialog.show( );
			}
			catch ( Exception e )
			{
				e.printStackTrace( );
			}
		}
	}

	*/
/** 回收不使用的bitmap *//*

	public static void recyledBitmap( Bitmap bitmap )
	{
		try
		{
			if ( bitmap != null )
			{
				if ( !bitmap.isRecycled( ) )
				{
					bitmap.recycle( );
					bitmap = null;
				}
			}
			System.gc( );
		}
		catch ( Throwable e )
		{
			e.printStackTrace( );
		}
	}

	*/
/**
	 * 解析出url参数中的键值对 如 "index.php?a=user&m=update" 解析出a:user,m:update,以map方式返回
	 *
	 * @param url
	 *            url地址
	 * @return map 参数
	 *//*

	public static Map< String , String > paramURL( String url )
	{
		Map< String , String > mapRequest = new HashMap< String , String >( );
		String strUrlParam = null;
		String[ ] arrSplit = null;
		url = url.trim( );
		arrSplit = url.split( "[?]" );
		if ( url.length( ) > 1 )
		{
			if ( arrSplit.length > 1 )
			{
				if ( arrSplit[ 1 ] != null )
				{
					strUrlParam = arrSplit[ 1 ];
				}
			}
		}

		if ( strUrlParam == null )
		{
			return mapRequest;
		}

		String[ ] paramSplit = null;
		// 每个键值为一组
		paramSplit = strUrlParam.split( "[&]" );
		for ( String strSplit : paramSplit )
		{
			String[ ] arrSplitEqual = null;
			arrSplitEqual = strSplit.split( "[=]" );

			// 解析出键值
			if ( arrSplitEqual.length > 1 )
			{
				// 正确解析
				mapRequest.put( arrSplitEqual[ 0 ] , arrSplitEqual[ 1 ] );
			}
			else
			{
				if ( arrSplitEqual[ 0 ] != "" )
				{
					// 只有参数没有值
					mapRequest.put( arrSplitEqual[ 0 ] , "" );
				}
			}
		}
		return mapRequest;
	}

	// 方法名:getAttributes </br> 详述:返回一个Attributes
	public static int[ ] getAttributes( Context context , Double width , Double heigth )
	{
		WindowManager m = ( ( Activity ) context ).getWindowManager( );
		Display d = m.getDefaultDisplay( ); // 获取屏幕宽、高用//
		int heigths = ( int ) ( d.getHeight( ) * heigth ); // 高度设置为屏幕的0.6
		int widths = ( int ) ( d.getWidth( ) * width ); // 宽度设置为屏幕的0.95
		int[ ] newArrribute = new int[ ]
			{ widths , heigths };
		return newArrribute;
	}

	public static boolean isShowExpressionSelectGuide( Context context )
	{
		SharedPreferenceUtil sp = SharedPreferenceUtil.getInstance( context );
		return !sp.getBoolean( SharedPreferenceUtil.SHOW_EXPRESSION_SELECT_GUIDE );
	}

	public static void setNoShowExpressionSelectGuide( Context context )
	{
		SharedPreferenceUtil sp = SharedPreferenceUtil.getInstance( context );
		sp.putBoolean( SharedPreferenceUtil.SHOW_EXPRESSION_SELECT_GUIDE , true );
	}

	public static boolean isShowExpressionAddGuide( Context context )
	{
		SharedPreferenceUtil sp = SharedPreferenceUtil.getInstance( context );
		return !sp.getBoolean( SharedPreferenceUtil.SHOW_EXPRESSION_ADD_GUIDE );
	}

	public static void setNoShowExpressionAddGuide( Context context )
	{
		SharedPreferenceUtil sp = SharedPreferenceUtil.getInstance( context );
		sp.putBoolean( SharedPreferenceUtil.SHOW_EXPRESSION_ADD_GUIDE , true );
	}

	public static boolean isShowSpaceInfoGuide( Context context )
	{
		SharedPreferenceUtil sp = SharedPreferenceUtil.getInstance( context );
		return !sp.getBoolean( SharedPreferenceUtil.SHOW_SPACE_INFO_GUIDE );
	}

	public static boolean isShowGroupChatForbidGuide( Context context )
	{
		SharedPreferenceUtil sp = SharedPreferenceUtil.getInstance( context );
		return !sp.getBoolean( SharedPreferenceUtil.SHOW_GROUP_CHAT_FORBID_GUIDE );
	}

	public static void setNoShowSpaceInfoGuide( Context context )
	{
		SharedPreferenceUtil sp = SharedPreferenceUtil.getInstance( context );
		sp.putBoolean( SharedPreferenceUtil.SHOW_SPACE_INFO_GUIDE , true );
	}

	public static void setNoShowGroupChatForbidGuide( Context context )
	{
		SharedPreferenceUtil sp = SharedPreferenceUtil.getInstance( context );
		sp.putBoolean( SharedPreferenceUtil.SHOW_GROUP_CHAT_FORBID_GUIDE , true );
	}

	*/
/**
	 * 获取包id
	 *
	 * @param context
	 * @param metaKey
	 * @return
	 *//*

	public static String getPackageMetaData( Context context )
	{
		if ( !isEmptyOrNullStr( packageID ) )
		{
			return packageID;
		}
		packageID = String.valueOf( getMetaData( context , "UMENG_CHANNEL" ) );
		if ( !isEmptyOrNullStr( packageID ) )
		{
			Log.v( "iaround" , "Channel_ID:" + packageID );
			return packageID;
		}
		return "11203";
	}

	*/
/**
	 * 读取meta-data数据
	 *
	 * @param context
	 * @param metaKey
	 * @return
	 *//*

	public static Object getMetaData( Context context , String metaName )
	{
		ApplicationInfo appInfo;
		try
		{
			appInfo = context.getPackageManager( ).getApplicationInfo(
					context.getPackageName( ) , PackageManager.GET_META_DATA );
			Object metaValue = appInfo.metaData.get( metaName );
			return metaValue;
		}
		catch ( NameNotFoundException e )
		{
			e.printStackTrace( );
		}
		return "";
	}

	*/
/**
	 * 设置选中效果
	 *
	 * @param context
	 *
	 * @param mImageIds
	 *            数组，第一个为常规，第二个为选中
	 *//*

	public static StateListDrawable getSelectedDrawable( Context context , int[ ] mImageIds )
	{
		StateListDrawable sd = new StateListDrawable( );
		Drawable normal = mImageIds[ 0 ] == -1 ? null : context.getResources( ).getDrawable(
				mImageIds[ 0 ] );
		Drawable pressed = mImageIds[ 1 ] == -1 ? null : context.getResources( ).getDrawable(
				mImageIds[ 1 ] );
		Drawable focus = mImageIds[ 1 ] == -1 ? null : context.getResources( ).getDrawable(
				mImageIds[ 1 ] );
		sd.addState( new int[ ]
			{ android.R.attr.state_enabled , android.R.attr.state_focused } , focus );
		sd.addState( new int[ ]
			{ android.R.attr.state_pressed , android.R.attr.state_enabled } , pressed );
		sd.addState( new int[ ]
			{ android.R.attr.state_focused } , focus );
		sd.addState( new int[ ]
			{ android.R.attr.state_pressed } , pressed );
		sd.addState( new int[ ]
			{ android.R.attr.state_enabled } , normal );
		sd.addState( new int[ ] { } , normal );
		return sd;
	}

	*/
/**
	 * 判断是否在应用程序中
	 *
	 * @param context
	 * @return
	 *//*

	public static boolean isTopActivity( Context context )
	{
		String packageName = context.getPackageName( );
		ActivityManager activityManager = ( ActivityManager ) context
				.getSystemService( Context.ACTIVITY_SERVICE );
		List< RunningTaskInfo > tasksInfo = activityManager.getRunningTasks( 1 );
		if ( tasksInfo.size( ) > 0 )
		{
			ComponentName topActivity = tasksInfo.get( 0 ).topActivity;
			if ( packageName.equals( topActivity.getPackageName( ) ) )
			{
				return true;
			}
			if ( topActivity.getClassName( ).contains( "net.iaround" ) )
			{
				return true;
			}
		}
		return false;
	}

	*/
/**
	 * 将本应用的栈堆放至前台
	 * *//*

	public static void moveTaskToFront( Activity activity , Context context )
	{
		ActivityManager am;
		am = ( ActivityManager ) context.getSystemService( Context.ACTIVITY_SERVICE );
		am.moveTaskToFront( activity.getTaskId( ) , ActivityManager.MOVE_TASK_NO_USER_ACTION );
	}

	public static LinearLayout.LayoutParams setLinearLayoutAttribute( View view , float width ,
			float height )
	{
		int screenPixWidth = getScreenPixWidth( view.getContext( ) );
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT , LayoutParams.MATCH_PARENT );
		if ( width != 0 )
		{
			params.width = ( int ) ( screenPixWidth * width );
		}
		if ( height != 0 )
		{
			params.height = ( int ) ( screenPixWidth * height );
		}
		view.setLayoutParams( params );
		return params;
	}

	public static RelativeLayout.LayoutParams setRelativeLayoutAttribute( View view ,
			float width , float height )
	{
		int screenPixWidth = getScreenPixWidth( view.getContext( ) );
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT , LayoutParams.WRAP_CONTENT );
		if ( width != 0 )
		{
			params.width = ( int ) ( screenPixWidth * width );
		}
		if ( height != 0 )
		{
			params.height = ( int ) ( screenPixWidth * height );
		}
		view.setLayoutParams( params );
		return params;
	}

	*/
/**
	 * @Title: isX86CPU
	 * @Description: 是否为x86CPU机型
	 * @return boolean
	 *//*

	public static boolean isX86CPU( )
	{
		try
		{
			String cpuABI = "";
			cpuABI = Build.CPU_ABI;
			if ( cpuABI.contains( "x86" ) )
			{
				return false;
			}
			return true;
		}
		catch ( Exception e )
		{
			e.printStackTrace( );
			return false;
		}
	}

	*/
/**
	 * Saves the extraction model to the file.
	 *//*

	public static void saveModel( String path , List< ? extends Serializable > clazz )
			throws Exception
	{
		BufferedOutputStream bufferedOut = new BufferedOutputStream( new FileOutputStream(
				path ) ); // 文件路径
		ObjectOutputStream out = new ObjectOutputStream( bufferedOut );
		out.writeObject( clazz );
		out.flush( );
		out.close( );
	}

	// 从文件中加载信息到对象,可以这么做:
	*/
/**
	 * Loads the extraction model from the file.
	 *//*

	public static ArrayList< ? extends Serializable > loadModel( String path )
	{
		ArrayList< ? extends Serializable > class1 = new ArrayList< Serializable >( );
		try
		{
			File f = new File( path );
			if ( f.exists( ) && f.length( ) != 0 )
			{
				FileInputStream fs = new FileInputStream( f );
				BufferedInputStream inStream = new BufferedInputStream( fs ); // 文件路径
				ObjectInputStream in = new ObjectInputStream( inStream );
				class1 = ( ArrayList< ? extends Serializable > ) in.readObject( );
				in.close( );
			}
			return class1;
		}
		catch ( Exception e )
		{
			e.printStackTrace( );
		}
		return class1;

	}

	public static File isMkdir( String path )
	{
		File file = new File( path );
		if ( !file.exists( ) )
		{
			file.mkdir( );
		}
		return file;
	}

	public static String isFile( String path )
	{
		File file = new File( path );
		if ( !file.exists( ) )
		{
			try
			{
				file.createNewFile( );
			}
			catch ( IOException e )
			{
				e.printStackTrace( );
			}
		}
		return path;
	}

	public static boolean isFileExist( String path )
	{
		File file = new File( path );

		return file.exists( );
	}

	*/
/**
	 * @Title: toastMsg
	 * @Description: Toast一条消息
	 * @param context
	 * @param sMsg
	 *            String msg
	 *//*

	public static void toastMsg( Context context , String sMsg )
	{
		Toast.makeText( context , sMsg , Toast.LENGTH_SHORT ).show( );
	}

	*/
/**
	 * @Title: toastMsg
	 * @Description: Toast一条消息
	 * @param context
	 * @param rMsg
	 *            int msg
	 *//*

	public static void toastMsg( Context context , int rMsg )
	{
		Toast.makeText( context , rMsg , Toast.LENGTH_SHORT ).show( );
	}

	*/
/**
	 * 把传进来的EditText的光标移动到最后
	 *
	 * @param editView
	 *//*

	public static void MoveCursorToLast( EditText editView )
	{
		Spannable contentSpan = editView.getEditableText( );
		String editText = editView.getText( ).toString( );
		Selection.setSelection( contentSpan , editText.length( ) );
	}

	*/
/**
	 * 获取当前时间是否为免打扰时时间段
	 *
	 * @return
	 *//*

	public static boolean IsNotDisturbTime( Context context )
	{
		SharedPreferenceUtil sp = SharedPreferenceUtil.getInstance( context );
		long uid = Common.getInstance( ).loginUser.getUid( );
		String dndKey = SharedPreferenceUtil.DND_SETTING + uid;
		String startTimeKey = SharedPreferenceUtil.REC_START_TIME + uid;
		String endTimeKey = SharedPreferenceUtil.REC_END_TIME + uid;

		boolean dnd_flag = sp.getBoolean( dndKey );
		int start_time = sp.getInt( startTimeKey );
		int end_time = sp.getInt( endTimeKey );

		Date now = new Date( TimeFormat.getCurrentTimeMillis( ) );
		Date startDate = TimeFormat.getTime( start_time );
		Date endDate = TimeFormat.getTime( end_time );
		if ( start_time >= end_time )// 如果开始时间比结束时间小，那么结束时间为第二天的时间
		{
			endDate.setHours( end_time + 24 );
		}

		if ( dnd_flag )// 不打扰
		{
			if ( startDate.before( now ) && now.before( endDate ) )
			{
				return true;
			}
			else
				return false;
		}
		else
		{
			return false;
		}

	}

	*/
/**
	 * @Title: parseFansNumber
	 * @Description: 个人资料的粉丝数转换，参照吴宇提供的5.1产品功能文档
	 * @param context
	 * @param fansNum
	 * @return String 粉丝≥500即显示，对于≥500，并且＜10,000的，用回精确数字，其它保持5.1规则。 5.2产品功能文档修改
	 *//*

	public static String parseFansNumber( Context context , int fansNum )
	{
		Resources resources = context.getResources( );
		String fansStr = resources.getString( R.string.fans ) + ": ";
		if ( getLanguageIndex( context ) == 0 )
		{// 英文版
			if ( fansNum >= 100000000 )
			{
				fansStr += "100 " + resources.getString( R.string.million );
			}
			else if ( fansNum >= 1000 )
			{
				int temp = ( int ) Math.floor( fansNum * 0.001 );
				fansStr += String.valueOf( temp ) + " "
						+ resources.getString( R.string.thousand );
			}
			else
			{
				fansStr += String.valueOf( fansNum );
			}
		}
		else
		{
			if ( fansNum >= 100000000 )
			{
				fansStr += "10000" + resources.getString( R.string.ten_thousand );
			}
			else if ( fansNum >= 10000 )
			{
				int temp = ( int ) Math.floor( fansNum * 0.0001 );
				fansStr += String.valueOf( temp )
						+ resources.getString( R.string.ten_thousand );
			}
			// else if ( fansNum >= 1000 )
			// {
			// int temp = ( int ) Math.floor( fansNum * 0.001 );
			// fansStr += String.valueOf( temp ) + resources.getString(
			// R.string.thousand );
			// }
			else
			{
				fansStr += String.valueOf( fansNum );
			}
		}

		return fansStr;
	}

	*/
/**
	 * 用于服务器下发的字段是null时，JSONObject的get和opt方法无法识别null
	 *
	 * @param json
	 * @param key
	 * @return
	 *//*

	public static String jsonOptString( JSONObject json , String key )
	{

		if ( json == null || json.isNull( key ) )
		{
			return "";
		}
		try
		{
			return json.getString( key );
		}
		catch ( JSONException e )
		{
			e.printStackTrace( );
		}
		return "";
	}

	public static Drawable TextToDrawable( String s )
	{
		Bitmap bitmap = Bitmap.createBitmap( 200 , 250 , Bitmap.Config.ARGB_8888 );
		Canvas canvas = new Canvas( bitmap );
		Paint paint = new Paint( );
		paint.setTextSize( 65 );
		paint.setTextAlign( Align.LEFT );
		paint.setColor( Color.WHITE );

		FontMetrics fm = paint.getFontMetrics( );
		canvas.drawText( s , 0 , 145 + fm.top - fm.ascent , paint );
		canvas.save( );
		Drawable drawableright = new BitmapDrawable( bitmap );
		drawableright.setBounds( 0 , 0 , drawableright.getMinimumWidth( ) ,
				drawableright.getMinimumHeight( ) );
		return drawableright;
	}

	*/
/**
	 * 得到手机通讯录联系人信息
	 *
	 * @return
	 **//*

	public static String getPhoneNumber( Context context )
	{
		entity.clear( );
		ContentResolver resolver = context.getContentResolver( );
		// 获取手机本地通讯录
		Cursor phoneCursor = resolver.query( Phone.CONTENT_URI , PHONES_PROJECTION , null ,
				null , null );
		if ( phoneCursor != null )
		{
			while ( phoneCursor.moveToNext( ) )
			{
				// 得到手机号码
				phoneNumber = phoneCursor.getString( PHONES_NUMBER_INDEX );
				if ( CommonFunction.isEmptyOrNullStr( phoneNumber ) )
				{
					continue;
				}
				// 去掉中英文状态的符号
				phoneNumber = phoneNumber.replaceAll( " " , "" ).replaceAll( "\\-" , "" )
						.replaceAll( "\\(" , "" ).replaceAll( "\\)" , "" )
						.replaceAll( "\\." , "" );
				phoneNumber = isChinesePunctuation( phoneNumber );

				// 号码为空 小于7位 不上传
				if ( phoneNumber == null || phoneNumber.length( ) == 0 )
				{
					continue;
				}
				else
				{
					if ( phoneNumber.length( ) < 7 )
					{
						continue;
					}

				}
				// 得到联系人名称
				contactName = phoneCursor.getString( PHONES_DISPLAY_NAME_INDEX );

				// 去掉乱码
				contactName = isMessyCode( contactName );

				if ( contactName.length( ) == 0 )
				{
					contactName = phoneNumber;

				}

				entity.put( phoneNumber , contactName );
			}
			phoneCursor.close( );
		}

		// 保存通讯录联系人数
		Common.getInstance( ).setContactCount( entity.size( ) );

		// 组装联系人内容
		ContactNumberBean bean = new ContactNumberBean( );
		Iterator< String > keys = entity.keySet( ).iterator( );
		while ( keys.hasNext( ) )
		{
			ContactNumber phone = new ContactNumber( );
			phone.phone = ( String ) keys.next( );
			phone.name = entity.get( phone.phone );
			phone.invisible = "n";
			bean.phones.add( phone );
		}

		content = GsonUtil.getInstance( ).getStringFromJsonObject( bean );
		return content;
	}

	*/
/**
	 * @Description: 获取通讯录的LinkedHashMap,手机号对名字
	 * @author tanzy
	 * @date 2014-9-25
	 *//*

	public static LinkedHashMap< String , String > getContactMap( Context context )
	{
		LinkedHashMap< String , String > map = new LinkedHashMap< String , String >( );
		String phoneNumber;
		String contactName;
		ContentResolver resolver = context.getContentResolver( );
		// 获取手机本地通讯录
		Cursor phoneCursor = resolver.query( Phone.CONTENT_URI , PHONES_PROJECTION , null ,
				null , null );
		if ( phoneCursor != null )
		{
			while ( phoneCursor.moveToNext( ) )
			{
				// 得到手机号码
				phoneNumber = phoneCursor.getString( PHONES_NUMBER_INDEX );
				if ( isEmptyOrNullStr( phoneNumber ) )
				{
					continue;
				}
				// 去掉中英文状态的符号
				phoneNumber = phoneNumber.replaceAll( " " , "" ).replaceAll( "\\-" , "" )
						.replaceAll( "\\(" , "" ).replaceAll( "\\)" , "" )
						.replaceAll( "\\." , "" );
				phoneNumber = isChinesePunctuation( phoneNumber );

				if ( isEmptyOrNullStr( phoneNumber ) )
				{
					continue;
				}
				else
				{
					if ( phoneNumber.length( ) < 7 )
					{
						continue;
					}

				}
				// 得到联系人名称
				contactName = phoneCursor.getString( PHONES_DISPLAY_NAME_INDEX );
				// 去掉乱码
				contactName = isMessyCode( contactName );

				if ( contactName.length( ) == 0 )
				{
					contactName = phoneNumber;

				}

				map.put( phoneNumber , contactName );
			}
			phoneCursor.close( );
		}

		// 保存通讯录联系人数
		Common.getInstance( ).setContactCount( map.size( ) );

		return map;
	}

	*/
/**
	 * 判断字符是否是中文
	 *
	 * @param c
	 *            字符
	 * @return 是否是中文
	 *//*

	public static boolean isChinese( char c )
	{
		Character.UnicodeBlock ub = Character.UnicodeBlock.of( c );
		if ( ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS )
		{
			return true;
		}
		return false;
	}

	// 是否中文符号
	public static boolean ChinesePunctuation( char c )
	{
		Character.UnicodeBlock ub = Character.UnicodeBlock.of( c );
		if ( ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION )
		{
			return true;
		}
		return false;
	}

	// 去掉输入法的中文符号
	public static String isChinesePunctuation( String strName )
	{
		char[ ] ch = strName.trim( ).toCharArray( );
		for ( int i = 0 ; i < ch.length ; i++ )
		{
			char c = ch[ i ];
			if ( !Character.isLetterOrDigit( c ) )
			{
				if ( isChinese( c ) )
				{
					strName = strName.replace( c + "" , "" );
				}
			}
		}
		return strName;

	}

	// 过滤乱码，如表情
	public static String isMessyCode( String strName )
	{
		String result = strName;
		Pattern p = Pattern.compile( "\\s*|\t*|\r*|\n*" );
		Matcher m = p.matcher( strName );
		String after = m.replaceAll( "" );
		String temp = after.replaceAll( "[\\p{p}\\p{Punct}]" , "" );
		char[ ] ch = temp.trim( ).toCharArray( );
		for ( int i = 0 ; i < ch.length ; i++ )
		{
			char c = ch[ i ];
			if ( !Character.isLetterOrDigit( c ) )
			{
				if ( !isChinese( c ) )
				{
					result = result.replace( c + "" , "" );
				}
			}
		}
		return result;
	}

	public static long lastClickTime;

	public static boolean isFastDoubleClick( )
	{
		long time = System.currentTimeMillis( );
		if ( time - lastClickTime < 1500 )
		{
			return true;
		}
		lastClickTime = time;
		return false;
	}

	*/
/**
	 * 判断从第三方应用返回到遇见时是否要重启
	 *
	 * @param context
	 *            ，最好传入getBaseContext( )
	 * @return true需要重启，false不需要
	 *//*

	public static boolean isRestartForShare( Context context )
	{
		if ( CloseAllActivity.getInstance( ).getSize( ) <= 1 )
		{
			CloseAllActivity.getInstance( ).backToMainActivity( );

			Intent i = context.getPackageManager( ).getLaunchIntentForPackage(
					context.getPackageName( ) );
			i.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK );
			context.startActivity( i );
			return true;
		}

		return false;
	}

	*/
/**
	 * 获取uri后面所带的特定名称的参数，没有则返回空字符串
	 * *//*

	public static String getUriParameter( String uri , String name )
	{
		String data = "";
		CommonFunction.log( "sherlock" , "the uri to get Parameter == " + uri );
		if ( !isEmptyOrNullStr( uri ) && !isEmptyOrNullStr( name ) )
		{
			String[ ] cut = uri.split( "[?]" );
			if ( cut.length < 2 )
				return data;
			String ps = cut[ 1 ];
			String[ ] p = ps.split( "&" );
			for ( int i = 0 ; i < p.length ; i++ )
			{
				String[ ] a = p[ i ].split( "=" );
				if ( name.equals( a[ 0 ] ) )
				{
					data = a[ 1 ];
					return data;
				}
			}
		}
		return data;
	}

	public static StringFromTo getHotGroupTopic( String source )
	{

		StringFromTo subFromString = new StringUtil.StringFromTo( 0 , 0 );
		Pattern p = Pattern.compile( "\\#\\w+\\#" , Pattern.UNICODE_CASE );// 匹配#
																			// #

		// 开始编译
		final Matcher m = p.matcher( source );
		int i = 0;
		int start = 0;
		int end = 0;
		while ( m.find( ) )
		{
			String icon = m.group( );

			start = source.indexOf( icon , start );
			end = start + icon.length( );
			if ( start > 0 && source.length( ) > end )
			{
				// 过滤表情
				if ( source.charAt( start - 1 ) == '[' && source.charAt( end ) == ']' )
				{
				}
				else
				{
					subFromString.start = start;
					subFromString.end = end;
					subFromString.subStr = icon;
					break;
				}
			}
			else
			{
				subFromString.start = start;
				subFromString.end = end;
				subFromString.subStr = icon;
				break;
			}

			i++;
			m.groupCount( );
		}
		return subFromString;
	}

	// unicode转成字符串
	public static String uniconde2Str( String str )
	{
		String result = "";
		for ( int i = 0 ; i < str.length( ) ; i++ )
		{
			char c = str.charAt( i );
			int v = ( int ) c;
			if ( v <= 0x024f || v >= 0x2000 && v <= 0x206F || v >= 0x2E80 && v <= 0x2EFF
					|| v >= 0x3000 && v <= 0x303F || v >= 0x3200 && v <= 0x4DB5 || v >= 0x4E00
					&& v <= 0x9FFF || v >= 0xF900 && v <= 0xFAFF || v >= 0xFE30 && v <= 0xFE4F
					|| v >= 0xFF00 && v <= 0xFFEF )
			{
				result += c;
			}
			else
			{
				result += "\\u" + Integer.toHexString( v );
			}
		}
		return result;
	}

	public static String faceStr2Unicode( String str )
	{
		String result = "";

		if ( str.startsWith( FaceManager.catFlag ) || str.contains( "back" ) )
		{

			result = str;

			return result;
		}
		else
		{

			if ( str.length( ) % 4 == 0 )
			{
				int i = 0;
				while ( i < str.length( ) - 1 )
				{

					String hexstr = str.substring( i , i + 4 );
					int v = Integer.parseInt( hexstr , 16 );
					result += ( char ) v;
					i = i + 4;
				}

				return result;
			}
			else if ( ( str.length( ) - 2 ) % 4 == 0 )
			{
				int i = 0;
				String ascHexStr = str.substring( i , i + 2 );
				int v = Integer.parseInt( ascHexStr , 16 );
				result += ( char ) v;
				i = i + 2;
				while ( i < str.length( ) - 1 )
				{

					String hexstr = str.substring( i , i + 4 );
					v = Integer.parseInt( hexstr , 16 );
					result += ( char ) v;
					i = i + 4;
				}

				return result;
			}
			else
			{
				return str;
			}
		}
	}

	// 字符串的转成unicode
	public static String Str2Unicode( String str )
	{
		String result = "";
		int i = 0;
		while ( i < str.length( ) - 1 )
		{
			if ( str.substring( i , i + 2 ).equals( "\\u" ) )
			{
				String hexstr = str.substring( i + 2 , i + 6 );
				int v = Integer.parseInt( hexstr , 16 );
				result += ( char ) v;
				i = i + 6;
			}
			else
			{
				result += str.charAt( i );
				i = i + 1;
			}
		}
		result += str.substring( i );
		return result;
	}

	*/
/**
	 * 更改表情文件后缀名，下载后安装后立即更改 目的是为了不让表情图片出现在手机相册里
	 *
	 * @param path
	 *            路径
	 * @param from
	 *            原始的后缀名
	 * @param to
	 *            改名的后缀
	 *
	 *//*

	@SuppressWarnings ( "unused" )
	public static void reFaceFileName( String path )
	{
		File f = new File( path );
		File[ ] fs = f.listFiles( );
		if ( fs.length > 0 )
		{
			for ( int i = 0 ; i < fs.length ; ++i )
			{
				File f2 = fs[ i ];
				if ( f2.isDirectory( ) )
				{
					reFaceFileName( f2.getPath( ) );
				}
				else
				{
					String from = "";
					String name = f2.getName( );
					if ( name.contains( "." ) )
					{
						String[ ] subStr = name.split( "\\." );
						if ( subStr.length == 2 )
						{
							for ( String string : subStr )
							{
								from = "." + subStr[ 1 ];
							}
						}
					}
					if ( from.equals( ".txt" ) )
					{
						continue;
					}
					else if ( from.equals( ".gif" ) )
					{
						String to = PathUtil.getDynamicFacePostfix( );
						if ( name.endsWith( from ) )
						{
							f2.renameTo( new File( f2.getParent( ) + "/"
									+ name.substring( 0 , name.indexOf( from ) ) + to ) );
						}
					}
					else if ( from.equals( ".png" ) )
					{
						String to = PathUtil.getStaticFacePostfix( );
						if ( name.endsWith( from ) )
						{
							f2.renameTo( new File( f2.getParent( ) + "/"
									+ name.substring( 0 , name.indexOf( from ) ) + to ) );
						}
					}
					else
					{
						if ( !from.equals( ".static" ) && !from.equals( ".dynamic" ) )
						{
							String to = PathUtil.getDynamicFacePostfix( );
							if ( name.endsWith( from ) )
							{
								f2.renameTo( new File( f2.getParent( ) + "/"
										+ name.substring( 0 , name.indexOf( from ) ) + to ) );
							}
						}
					}

				}
			}
		}
	}

	public static void openApp( Activity activity , String packageName )
	{
		if ( TextUtils.isEmpty( packageName ) || activity == null )
		{
			return;
		}

		PackageManager pm = activity.getApplicationContext( ).getPackageManager( );
		Intent i = pm.getLaunchIntentForPackage( packageName );
		if ( i != null )
		{
			activity.startActivity( i );
		}
		else
		{
			Toast.makeText( activity , R.string.game_center_openapp_fail , Toast.LENGTH_LONG )
					.show( );
		}
	}

	*/
/**
	 * 获取对应属性的文本————格式（英-简-繁） 如 ： you | 你 |  妳
	 *
	 * @param mContext
	 * @param  str
	 * @return String
	 *//*

	public static String getLangText(Context mContext ,  String str )
	{
		int Lang = getLanguageIndex( mContext );
		if ( str != null && !str.equals( "" ) ){
		   if ( str.contains( "|" ) ){
			String[ ] split = str.split( "\\|" );
			if ( split.length == 3 )
			{
				return split[ Lang ];
			}
		}}
		return str;
	}

	*/
/*
	 * 应用切换到后台之后返回时候显示启动广告
	 *//*

	public static void showScreanAd(Context mContext)
	{
		if(CloseAllActivity.appContext!=null)
		{
			if(CloseAllActivity.appContext == mContext )
			{
				if(CloseAllActivity.isShowedBgAd ||!Common.getInstance( ).isUserLogin)
				{
					//启动过一次或者未登录不显示广告
					return ;
				}
				SharedPreferenceUtil sp = SharedPreferenceUtil.getInstance( mContext );
				int iRecommended = sp.getInt(SharedPreferenceUtil.RECOMMENDED_AVAILABLE, 0);
				if(iRecommended ==1)
				{
				if ( sp.contains( SharedPreferenceUtil.START_PAGE_AD_URL )
						&& !TextUtils.isEmpty( sp.getString( SharedPreferenceUtil.START_PAGE_AD_URL ) ) )
				{
					String url = sp.getString( SharedPreferenceUtil.START_PAGE_AD_URL );

					if ( PathUtil.isExistMD5UserCacheFile( url ) )
					{
						new FullScreamDialog(mContext, url).show();
						CloseAllActivity.isShowedBgAd = true;
					}

				}
			}

			}
			else
			{
				CloseAllActivity.appContext = mContext ;
			}
		}
		else
		{
			CloseAllActivity.appContext = mContext ;
		}
	}

	*/
/**
	 * 给遇见评分的dialog
	 * @param mContext
	 *//*

     public static void showMarkMeDialog(final Context mContext){
		final Dialog creditDialog = new Dialog( mContext , R.style.MyDialog );
		View view = LayoutInflater.from( mContext ).inflate( R.layout.z_mark_me_dialog_view , null );
		int dp_284 = ( int ) ( 284 * mContext.getResources( ).getDisplayMetrics( ).density );
		creditDialog.setContentView( view , new LinearLayout.LayoutParams( dp_284 , LinearLayout.LayoutParams.WRAP_CONTENT ) );
		creditDialog.show( );
		creditDialog.setCanceledOnTouchOutside(false);
		view.findViewById( R.id.take_credit ).setOnClickListener( new OnClickListener( )
		{//给好评
			@Override
			public void onClick( View v )
			{
				takeCredit(mContext);
				creditDialog.dismiss( );
			}
		} );

		view.findViewById( R.id.to_feedback ).setOnClickListener( new OnClickListener( )
		{//去反馈意见
			@Override
			public void onClick( View v )
			{
				Intent intent = new Intent( mContext , FeedBackActivity.class );
				mContext.startActivity( intent );
				creditDialog.dismiss( );
			}
		} );

		view.findViewById( R.id.dismiss ).setOnClickListener( new OnClickListener( )
		{//关闭
			@Override
			public void onClick( View v )
			{
				creditDialog.dismiss( );
			}
		} );
	}

     */
/**
      * 跳到某渠道评分
      * @param mContext
      *//*

	public static void takeCredit(Context mContext) {
		String url = SharedPreferenceUtil.getInstance(mContext).getString(
				SharedPreferenceUtil.COMMENT_URL);
		String packageName = SharedPreferenceUtil.getInstance(mContext).getString(
				SharedPreferenceUtil.COMMENT_PACKAGENAME);
		CommonFunction.log("shifengxiong", "apply setting " + packageName);
		if (isClientInstalled(mContext, packageName)) {
			// CommonFunction.openApp( this , packageName );
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setPackage(packageName);
			intent.setData(Uri.parse("market://details?id=" + mContext.getPackageName()));
			mContext.startActivity(intent);
		} else {
			Uri uri = Uri.parse("market://details?id=" + mContext.getPackageName());
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			mContext.startActivity(intent);
		}
	}


	*/
/**
	 * 标记启动遇见并且成功登录的次数
	 * @param context
	 *//*

	public static void markOpenCount(Context context){
		if (Common.getInstance().getStartAndLoginSuccess() == 1) {
			 SharedPreferenceUtil sp = SharedPreferenceUtil.getInstance(context);
			 String markCountKey = SharedPreferenceUtil.MARK_OPEN_COUNT;
			 String markVersionCode = SharedPreferenceUtil.MARK_VERSION_CODE;

			// 当前的版本versionCode;
			int versionCode = 0;
			try {
				PackageManager pm = context.getPackageManager();
				PackageInfo pinfo = pm.getPackageInfo(context.getPackageName(),
						PackageManager.GET_CONFIGURATIONS);
				versionCode = pinfo.versionCode;
			} catch (Throwable e) {
				log(e);
			}

			if ( ( !sp.has( markVersionCode ) || ( sp.getInt( markVersionCode ) < versionCode ) ) ) {
				sp.putInt(markCountKey, 1 );
				sp.putInt( markVersionCode , versionCode );
			}else {
				if (sp.contains(markCountKey)) {
					int lastCount = sp.getInt(markCountKey);
					lastCount = lastCount + 1 ;
					sp.putInt(markCountKey, lastCount );
				}else {
					sp.putInt(markCountKey, 1 );
				}
			}

			if (sp.getInt(markCountKey) == 5) {
				new Handler().postDelayed(new Runnable(){
				    public void run() {
				    	Activity topActivity = CloseAllActivity.getInstance()
				    			.getTopActivity();
				    	if (topActivity != null && !MainActivity.getIsLogot()) {
				    		showMarkMeDialog(topActivity);
				    	}
				    }
				 }, 5*1000);

			}

			Common.getInstance().setStartAndLoginSuccess(0);
		}
	}

	public static byte[] decodeBitmap(String path ,Context context) {
        Options opts = new Options();
        opts.inJustDecodeBounds = true;// 设置成了true,不占用内存，只获取bitmap宽高
        BitmapFactory.decodeFile(path, opts);
        int maxSampleSize = Math.min( getScreenPixWidth( context )*getScreenPixHeight( context )*4 , opts.outHeight * opts.outWidth ) ;

        log( "shifengxiong","maxSampleSize ==" +maxSampleSize);
        opts.inSampleSize = computeSampleSize(opts, -1, maxSampleSize);
        opts.inJustDecodeBounds = false;// 这里一定要将其设置回false，因为之前我们将其设置成了true
        opts.inPurgeable = true;
        opts.inInputShareable = true;
        opts.inDither = false;
        opts.inPurgeable = true;
        opts.inPreferredConfig = Bitmap.Config.RGB_565;
        opts.inTempStorage = new byte[16 * 1024];
        FileInputStream is = null;
        Bitmap bmp = null;
        ByteArrayOutputStream baos = null;
        try {
            is = new FileInputStream(path);
            bmp = BitmapFactory.decodeFileDescriptor(is.getFD(), null, opts);
//            double scale = getScaling(opts.outWidth * opts.outHeight,
//                    getScreenPixHeight( context ) * getScreenPixWidth( context ));
//            Bitmap bmp2 = Bitmap.createScaledBitmap(bmp,
//                    (int) (opts.outWidth * scale),
//                    (int) (opts.outHeight * scale), true);


            baos = new ByteArrayOutputStream();
            bmp.compress(CompressFormat.JPEG, 50, baos);
            bmp.recycle();
//            bmp2.recycle();
            return baos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.gc();
        }
        return baos.toByteArray();
    }

    private static double getScaling(int src, int des) {
        */
/**
         * 48 目标尺寸÷原尺寸 sqrt开方，得出宽高百分比 49
         *//*

        double scale = Math.sqrt((double) des / (double) src);
        return scale;
    }

    public static int computeSampleSize(Options options,
            int minSideLength, int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(options, minSideLength,
                maxNumOfPixels);

        int roundedSize;
        if (initialSize <= 8) {
            roundedSize = 1;
            while (roundedSize < initialSize) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }

        return roundedSize;
    }

    private static int computeInitialSampleSize(Options options,
            int minSideLength, int maxNumOfPixels) {  
        double w = options.outWidth;  
        double h = options.outHeight;  
  
        int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math  
                .sqrt(w * h / maxNumOfPixels));  
        int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(  
                Math.floor(w / minSideLength), Math.floor(h / minSideLength));  
  
        if (upperBound < lowerBound) {  
            return lowerBound;  
        }  
  
        if ((maxNumOfPixels == -1) && (minSideLength == -1)) {  
            return 1;  
        } else if (minSideLength == -1) {  
            return lowerBound;  
        } else {  
            return upperBound;  
        }  
    }  
    
    
	public static ArrayList< String > subFaceString( EditText mEditText , String content ,
			int length)
	{
		ArrayList< String > result = new ArrayList< String >( );
		String tmp = content;
		for ( ; tmp.length( ) > length ; )
		{
			int tmpSize = tmp.length( ) > length + 5 ? length + 5 : tmp.length( );
			String newTmp = tmp.substring( 0 , tmpSize );
			mEditText.setText( newTmp );
			mEditText.setSelection( newTmp.length( ) );
			for ( ; mEditText.getText( ).length( ) > length ; )
			{
				KeyEvent keyEventDown = new KeyEvent( KeyEvent.ACTION_DOWN ,
						KeyEvent.KEYCODE_DEL );
				mEditText.onKeyDown( KeyEvent.KEYCODE_DEL , keyEventDown );
			}
			newTmp = mEditText.getText( ).toString( );
			result.add( newTmp );
			mEditText.getText( ).clear( );
			tmp = tmp.substring( newTmp.length( ) );
		}
		result.add( tmp );
		return result;
	}
	
}
*/
