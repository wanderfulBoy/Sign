package com.flytexpress.sign.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

@SuppressLint("ClickableViewAccessibility")
public class CustomViewPager extends ViewPager {

	private boolean scrollble = true;  
	  
    public CustomViewPager(Context context) {  
        super(context);  
    }  
  
    public CustomViewPager(Context context, AttributeSet attrs) {  
        super(context, attrs);  
    }  
  
  
    @Override  
    public boolean onTouchEvent(MotionEvent ev) { //是否拦截滑动切屏的方法
        if (!scrollble) {  
            return true;  
        }  
        return super.onTouchEvent(ev);  
    }  
  
  
    public boolean isScrollble() {  
        return scrollble;  
    }  
  
    public void setScrollble(boolean scrollble) {  
        this.scrollble = scrollble;  
    }  
   

}
