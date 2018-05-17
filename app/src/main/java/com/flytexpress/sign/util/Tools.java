package com.flytexpress.sign.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.speech.RecognizerIntent;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

public class Tools {
	private static Tools tools;
	public static Tools getInstance( Context context )
	{
		if ( tools == null )
		{
			tools = new Tools();
		}             
		return tools;
	}

	public static void showToast(Context context, String str) {
		Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 判断当前手机是否联网
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetwork(Context context) {
		try {
			ConnectivityManager cwjManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			Boolean flag = cwjManager.getActiveNetworkInfo().isAvailable();
			return flag;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 1:得到版本�?2:得到版本�?
	 * 
	 * @return
	 */
	public static String getVersionInfo(Context context, int choice) {
		String versionName = "";
		int versionCode = 0;
		PackageInfo info = null;
		try {
			info = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		if (info != null) {
			if (choice == 1) {
				versionName = info.versionName;
			} else if (choice == 2) {
				versionCode = info.versionCode;
			}
		} else {
			versionName = "1.0";
			versionCode = 100;
		}
		if (choice == 1) {
			return versionName;
		} else if (choice == 2) {
			return versionCode + "";
		}
		return versionName;
	}

	public static boolean isTablet(Context context) {
		return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
	}

	/**
	 * 传个包名进去就可以启动相应的程序了�?
	 * 
	 * @param context
	 * @param packName
	 */
	public static void startSoft(Context context, String packName) {
		Intent intent = new Intent();
		intent = context.getPackageManager()
				.getLaunchIntentForPackage(packName);
		context.startActivity(intent);
	}

	/**
	 * 某个范围内取随机�?
	 */
	public static int getRandom(int a, int b) {
		int temp = 0;
		try {
			if (a > b) {
				temp = new Random().nextInt(a - b);
				return temp + b;
			} else {
				temp = new Random().nextInt(b - a);
				return temp + a;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp + a;
	}

	/**
	 * 判断给定字符串是否空白串�?空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
	 * 
	 * @param input
	 * @return boolean
	 */
	public static boolean isEmpty(String input) {
		if (input == null || "".equals(input))
			return true;

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
				return false;
			}
		}
		return true;
	}

	/**
	 * 手机设置静音
	 */
	public static void setVolume(Context context) {
		AudioManager mAudioManager = (AudioManager) context
				.getSystemService(Context.AUDIO_SERVICE);
		mAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
	}

	/**
	 * 判断手机号码格式是否正确
	 */
	public static boolean isMobileNum(String mobiles) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(17[0-9])|(18[0-9])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		System.out.println(m.matches() + "---");
		return m.matches();

	}

	public static boolean isTrackingNumber(String number) {// 判断是否为跟踪号
		// 英文开头、中间数字，英文结尾
		/*
		 * String num = ("[0-9]+"); // 全数字 String str = "^[A-Za-z]+$";// 全英文
		 * String strNum = "[a-zA-Z]+[0-9]*";// 英文开头后面全数字 String numStr =
		 * "^[\\d]+[a-zA-Z]+$";// 数字开头后面全英文， String strNumStr =
		 * "^[a-zA-Z]+(\\d+)[a-zA-Z]+$";// 英文数字英文 String
		 * numStrnum="^[\\d]+[a-zA-Z]+$+(//d+)";
		 */
		String total = "([0-9]+)|(^[A-Za-z]+$)|([a-zA-Z]+[0-9]*)|(^[\\d]+[a-zA-Z]+$)|(^[a-zA-Z]+(\\d+)[a-zA-Z]+$|^[\\d]+[a-zA-Z]+$+(//d+))";
		// String strNumStr1="[a-z^A-Z]+([-]|[\\d])+[a-z^A-Z]+$";
		Pattern p = Pattern.compile(total);
		Matcher m = p.matcher(number);
		return m.matches();
	}

	public static boolean isSellcodeNumber(String number) {// 判断是否为
		// 英文开头、中间数字，英文结尾
		/*
		 * String num = ("[0-9]+"); // 全数字 String str = "^[A-Za-z]+$";// 全英文
		 * String strNum = "[a-zA-Z]+[0-9]*";// 英文开头后面全数字 String numStr =
		 * "^[\\d]+[a-zA-Z]+$";// 数字开头后面全英文， String strNumStr =
		 * "^[a-zA-Z]+(\\d+)[a-zA-Z]+$";// 英文数字英文
		 */String total = "^[\\d]+(0|1)+[0-9]+$";
		// String strNumStr1="[a-z^A-Z]+([-]|[\\d])+[a-z^A-Z]+$";
		Pattern p = Pattern.compile(total);
		Matcher m = p.matcher(number);
		return m.matches();
	}

	public static boolean isOrderTel(String orderTel) {// 判断是否为订单号
		// 英文开头、中间数字，英文结尾
		// String reg = "^(A|F)[0-9]{11}[a-zA-Z0-9]{4}$";
		String total = "^(A|F){1}[0-9]{11}[a-zA-Z0-9]{4}$";
		// String strNumStr1="[a-z^A-Z]+([-]|[\\d])+[a-z^A-Z]+$";
		Pattern p = Pattern.compile(total);
		Matcher m = p.matcher(orderTel);
		return m.matches();
	}

	public static String formatDateCurrentTime(long time, String... dateFormat) {
		Date date = new Date(time);
		DateFormat df = null;
		if (dateFormat != null && dateFormat.length > 0)
			df = new SimpleDateFormat(dateFormat[0]);
		else
			df = new SimpleDateFormat("MM月dd日HH:mm");
		return df.format(date);
	}
	
	public static String getNowTime() { 
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
			return df.format(new Date());
		}

	public static void shareFriends(Context context, String text) {// 分享接口
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_SUBJECT, "");
		intent.putExtra(Intent.EXTRA_TEXT, text);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(Intent.createChooser(intent, "分享"));
	}

	// 判断String是否可以转int
	public static boolean isValidateNumber(String str)
			throws NumberFormatException {
		if (str == null) {
			return false;
		}
		try {
			Pattern p = Pattern.compile("[\\d]+");
			Matcher m = p.matcher(str);
			return m.matches();
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
	}

	// 邮箱格式是否正确
	public static boolean isEmail(String email) {
		String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);

		return m.matches();
	}

	public static String getTime() {
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy-MM-dd:HH:ss");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String str = formatter.format(curDate);
		return str;

	}

	// 判断有没有SD�?
	public static boolean hasSdcard() {
		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}

	public void getDialog(String title, String message, String bu1, String bu2,
			Context context) {
		AlertDialog isExit = new AlertDialog.Builder(context).create();
		isExit.setTitle("系统提示:");
		isExit.setMessage("确定退出吗？");
		isExit.setButton(AlertDialog.BUTTON_POSITIVE, "确定", listener);
		isExit.setButton(AlertDialog.BUTTON_NEGATIVE, "取消", listener);
		isExit.show();
	}

	DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			switch (which) {
			case AlertDialog.BUTTON_POSITIVE:// "确认"按钮�?��程序
				/*
				 * Intent intent = new Intent(Intent.ACTION_MAIN);
				 * intent.addCategory(Intent.CATEGORY_HOME);
				 * intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				 * startActivity(intent);
				 * android.os.Process.killProcess(android.os.Process.myPid());
				 */

				break;
			case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框
				break;

			default:
				break;
			}
		}
	};

	/**
	 * 获取当前版本的名称
	 * 
	 * @param context
	 * @return
	 */
	public static String getVersionName(Context context) {
		try {
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(
					context.getPackageName(), 0);
			return packageInfo.versionName;

		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 从asset路径下读取对应文件转String输出
	 * 
	 * @param mContext
	 * @return
	 */
	public static String getJson(Context mContext, String fileName) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		AssetManager am = mContext.getAssets();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					am.open(fileName)));
			String next = "";
			while (null != (next = br.readLine())) {
				sb.append(next);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sb.delete(0, sb.length());
		}
		return sb.toString().trim();
	}

	public static String getSDPath() {
		File sdDir = null;
		boolean sdCardExist = Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
		if (sdCardExist) {
			sdDir = Environment.getExternalStorageDirectory();// 获取跟目录
		}
		return sdDir.toString();
	}

	/**
	 * 将实体类转换成json字符串对象 注意此方法需要第三方gson jar包
	 * 
	 * @param obj
	 *            对象
	 * @return map
	 */
	public static String toJson(Object obj, int method) {
		// TODO Auto-generated method stub
		if (method == 1) {

			// 字段是首字母小写，其余单词首字母大写
			Gson gson = new Gson();
			String obj2 = gson.toJson(obj);
			return obj2;
		} else if (method == 2) {

			// FieldNamingPolicy.LOWER_CASE_WITH_DASHES 全部转换为小写，并用空格或者下划线分隔

			// FieldNamingPolicy.UPPER_CAMEL_CASE 所以单词首字母大写
			Gson gson2 = new GsonBuilder().setFieldNamingPolicy(
					FieldNamingPolicy.UPPER_CAMEL_CASE).create();
			String obj2 = gson2.toJson(obj);
			return obj2;
		}
		return "";
	}

	// map集合转换为json对象
	public static JSONObject getJSON(Map map) {
		Iterator iter = map.entrySet().iterator();
		JSONObject holder = new JSONObject();

		while (iter.hasNext()) {
			Map.Entry pairs = (Map.Entry) iter.next();
			String key = (String) pairs.getKey();
			Map m = (Map) pairs.getValue();
			JSONObject data = new JSONObject();

			try {
				Iterator iter2 = m.entrySet().iterator();
				while (iter2.hasNext()) {
					Map.Entry pairs2 = (Map.Entry) iter2.next();
					data.put((String) pairs2.getKey(),
							(String) pairs2.getValue());
				}
				holder.put(key, data);
			} catch (JSONException e) {
				Log.e("Transforming", "There was an error packaging JSON", e);
			}
		}

		return holder;
	}

	public static Intent getInstalledAppIntent(Context ctx, String uri) {// 用户打开一个app
		PackageManager pm = ctx.getPackageManager();
		try {
			pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
			Intent launchIntent = ctx.getPackageManager()
					.getLaunchIntentForPackage(uri);
			return launchIntent;
		} catch (NameNotFoundException e) {
			return null;
		}

	}

	/**
	 * 判断手机是否支持语音识别功能
	 */
	public static Boolean issupportVoice(Context context) {
		PackageManager pm = context.getPackageManager();
		List<ResolveInfo> list = pm.queryIntentActivities(new Intent(
				RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
		if (list.size() != 0) {
			return true;
		} else {
			return false;
		}
	}

	public static void pullKeywordTop(final Activity activity,
			final int lyRootID, final int vID, final int svID) {
		ViewGroup ly = (ViewGroup) activity.findViewById(lyRootID);
		// 获取屏幕高度，根据经验，输入法弹出高度一般在屏幕1/3到1/2之间
		final int defaultHeight = ((WindowManager) activity
				.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
				.getHeight();
		final int mKeyHeight = defaultHeight / 4;
		ly.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
			@Override
			public void onLayoutChange(View v, int left, int top, int right,
					int bottom, int oldLeft, int oldTop, int oldRight,
					int oldBottom) {
				// 获取根布局前后高度差
				int height = oldBottom - bottom;
				ScrollView sv = (ScrollView) activity.findViewById(svID);
				if (height > mKeyHeight) {// 当高度差大于屏幕1/4，认为是输入法弹出变动，可能会有特殊机型会失败
					final int lybottom = bottom;
					sv.post(new Runnable() {// 用post防止有时输入法会自动滚动覆盖我们手动滚动
						@Override
						public void run() {
							ScrollView runSv = (ScrollView) activity
									.findViewById(svID);
							// 获取要滚动至的控件到屏幕顶部高度
							View v = (View) activity.findViewById(vID);
							int[] loca = new int[2];
							v.getLocationOnScreen(loca);
							// 这种通知栏高度获取方法必须在布局构建完毕后才能生效，否则获取为0
							Rect frame = new Rect();
							activity.getWindow().getDecorView()
									.getWindowVisibleDisplayFrame(frame);
							int statusBarHeight = frame.top;
							// 要滚动的距离=控件距屏幕顶部距离+控件高度-输入法弹出后的activity高度-通知栏高度
							int scrollHeight = loca[1] + v.getHeight()
									- lybottom - statusBarHeight;
							if (scrollHeight > 0) {
								runSv.scrollBy(0, scrollHeight);
							}

						}
					});
				} else if (-height > mKeyHeight) {// 当输入法收起，回滚回顶部
					sv.scrollTo(0, 0);
				}
			}
		});

	}
	
	public static String getLocalTime() {//获取系统当前时间
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String str = formatter.format(curDate);
		return str;

	}
	
	  /** 
     * 按照提供的格式将字符串转换成Date类型 
     * @param dateStr 
     * @param formaterString 
     * @return 
     */  
    public static Date toDate(String dateStr, String formaterString) {  
        Date date = null;  
        SimpleDateFormat formater = new SimpleDateFormat();  
        formater.applyPattern(formaterString);  
        try {  
            date = formater.parse(dateStr);  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        return date;  
    }  
    
    /** 
     * 将时间字符串转换为Date类型 
     * @param dateStr 
     * @return Date 
     */  
    public static Date toDate(String dateStr) {  
        Date date = null;  
        SimpleDateFormat formater = new SimpleDateFormat();  
        formater.applyPattern("yyyy-MM-dd");  
        try {  
            date = formater.parse(dateStr);  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        return date;  
    }  
    
    /** 
     * 时间戳转换成日期格式字符串 
     * @param seconds 精确到秒的字符串 
     * @param format
     * @return 
     */  
    public static String timeStamp2Date(String seconds,String format) {  
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){  
            return "";  
        }  
        if(format == null || format.isEmpty()) format = "yyyy-MM-dd HH:mm:ss";  
        SimpleDateFormat sdf = new SimpleDateFormat(format);  
        return sdf.format(new Date(Long.valueOf(seconds+"000")));  
    }  
    
    public static Long getDaysBetween(Date startDate, Date endDate) {//计算两个时间的间隔
        Calendar fromCalendar = Calendar.getInstance();  
        fromCalendar.setTime(startDate);  
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);  
        fromCalendar.set(Calendar.MINUTE, 0);  
        fromCalendar.set(Calendar.SECOND, 0);  
        fromCalendar.set(Calendar.MILLISECOND, 0);  
  
        Calendar toCalendar = Calendar.getInstance();  
        toCalendar.setTime(endDate);  
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);  
        toCalendar.set(Calendar.MINUTE, 0);  
        toCalendar.set(Calendar.SECOND, 0);  
        toCalendar.set(Calendar.MILLISECOND, 0);  
        return (toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24);  
    }
    public static double testDouble(double... v) {//计算double数据类型的和，以免精度误差
		// TODO Auto-generated method stub
		 BigDecimal b  = new BigDecimal(Double.toString(v[0]));
	        for (int i = 1; i < v.length; i++) {
	            BigDecimal b2 = new BigDecimal(Double.toString(v[i]));
	            b=b.add(b2);
	        }
	        return b.doubleValue();
	}
    
    public boolean isStrOverSeven(String str){
    	if(str.length()>=7){
    		return true;
    	}else{
    		return false;//小于7
    	}
    }
    
    public boolean checkBoxisChecked(CheckBox ch){
    	if(ch.isChecked()){
    		return true;
    	}else{
    		return false;
    	}
    }
    
    /**
	 * 隐藏软键盘
	 * 
	 * @param context
	 * @param view
	 */
	public static void hideInputMethod( Context context , View view )
	{
		InputMethodManager imm = ( InputMethodManager ) context
				.getSystemService( Context.INPUT_METHOD_SERVICE );
		if ( imm != null )
		{
			imm.hideSoftInputFromWindow( view.getWindowToken( ) , 0 );
		}
	}
	
	public static void isStartBooth(Context context){//打开蓝牙
		BluetoothAdapter adapter;
		adapter = BluetoothAdapter.getDefaultAdapter();  
		if(adapter==null){//设置不支持蓝牙
			return ;
		}
		// 打开蓝牙      
		if (!adapter.isEnabled())    
		{    
		    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);    
		    // 设置蓝牙可见性，最多300秒      
		    intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);    
		    context.startActivity(intent);    
		}   
		

	}
    

   /* public static void checkUpdate(Context context, int versionCode, String url, String updateMessage, boolean isForced) {
        if (versionCode > UpdateManager.getInstance().getVersionCode(context)) {
            int type = 0;//更新方式，0：引导更新，1：安装更新，2：强制更新
            if (UpdateManager.getInstance().isWifi(context)) {
                type = 1;
            }
            if (isForced) {
                type = 2;
            }

            //检测是否已下载
            String downLoadPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/downloads/";
            File dir = new File(downLoadPath);
            if (!dir.exists()) {
                dir.mkdir();
            }
            String fileName = url.substring(url.lastIndexOf("/") + 1, url.length());
            if (fileName == null && TextUtils.isEmpty(fileName) && !fileName.contains(".apk")) {
                fileName = context.getPackageName() + ".apk";
            }
            File file = new File(downLoadPath + fileName);

            //设置参数
            UpdateManager.getInstance().setType(type).setUrl(url).setUpdateMessage(updateMessage).setFileName(fileName).setIsDownload(file.exists());
            if (type == 1 && !file.exists()) {
                UpdateManager.getInstance().downloadFile(context);
            } else {
                UpdateManager.getInstance().showDialog(context);
            }
        }
        }*/
	
	public  String  getString(String str){//大于六位的都弄省虐号
		if(str.length()>6){
			String str1=str.substring(0,6)+"...";
			return str1;
		}else{
			return str;
   		}
	}
	public static boolean isNetworkAvailable(Context context) {   
        ConnectivityManager cm = (ConnectivityManager) context   
                .getSystemService(Context.CONNECTIVITY_SERVICE);   
        if (cm == null) {   
        } else {
        	//如果仅仅是用来判断网络连接
        	//则可以使用 cm.getActiveNetworkInfo().isAvailable();  
            NetworkInfo[] info = cm.getAllNetworkInfo();   
            if (info != null) {   
                for (int i = 0; i < info.length; i++) {   
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {   
                        return true;   
                    }   
                }   
            }   
        }   
        return false;   
	}
	
	public static void setHintTextSize(EditText editText, String hintText, int textSize) {
	    // 新建一个可以添加属性的文本对象
	    SpannableString ss = new SpannableString(hintText);
	    // 新建一个属性对象,设置文字的大小
	    AbsoluteSizeSpan ass = new AbsoluteSizeSpan(textSize, true);
	    // 附加属性到文本
	    ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

	    // 设置hint
	    editText.setHint(new SpannedString(ss)); // 一定要进行转换,否则属性会消失
	}
	public static String strChange(String str,int i){
		if(i==1){//小写转大写
			return str.toUpperCase();
		}else{//大写转小写（只对英文字母有效，其它的结果没有效）
			return str.toLowerCase();
		}
	}

	//获取状态栏高度
	public static int getStatusBarHeight(Context context) {
		Class<?> c = null;
		Object obj = null;
		Field field = null;
		int x = 0, statusBarHeight = 0;
		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			statusBarHeight = context.getResources().getDimensionPixelSize(x);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return statusBarHeight;
	}
	/*public static void writeLog(){
		 LogUtils.getLog2FileConfig().configLog2FileEnable(true)
         // targetSdkVersion >= 23 需要确保有写sdcard权限
         .configLog2FilePath("/sdcard/项目文件夹/logs/")
         .configLog2FileNameFormat("%d{yyyyMMdd}.txt") 
         .configLogFileEngine(new LogFileEngineFactory());              
	}*/
	
	// 由于在取消Alarm的同时也取消了pi，并且一个PendingIntent只能登记给一个Alarm，
	// 所以可通过检查pi是否存在，来确认Alarm是否激活。
	/*public static boolean isServiceAlarmOn(Context context) {
	    Intent i = new Intent(context, AlarmReceiver.class);
	    // FLAG_NO_CREATE表示如果描述的pi不存在，则返回null，而不是创建它。
	    PendingIntent pi = PendingIntent.getService(
	            context, 0, i, PendingIntent.FLAG_NO_CREATE);
	    return pi != null;
	}*/
	/**
	 * 将图片转换成Base64编码的字符串
	 *
	 * @param path
	 * @return base64编码的字符串
	 */
	public static String  imageToBase64(String path,InputStream assetIn) {
		if (TextUtils.isEmpty(path)) {
			return null;
		}
		InputStream is = null;
		byte[] data = null;
		String result = null;
		try {
			if(assetIn==null) {
				is = new FileInputStream(path);
			}else{
				is=assetIn;
			}
			// 创建一个字符流大小的数组。
			data = new byte[is.available()];
			// 写入数组
			is.read(data);
			// 用默认的编码格式进行编码
			result = Base64.encodeToString(data, Base64.DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return result;
	}

}
