package com.flytexpress.sign.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.flytexpress.sign.MainActivity;
import com.flytexpress.sign.R;
import com.flytexpress.sign.util.SharedPreferenceCache;

public class SplashActivity extends Activity {
    private Handler handler = new Handler();
    private Context mContext;
    private boolean isFirst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
//      dodealStart();
        mContext=this;
        initView();
    }

    private void dodealStart() {
        if((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0){
            finish();
            return;
        }
    }

    private void initView() {
        isFirst= SharedPreferenceCache.getInstance(mContext).getBoolean("isFirst");
        //延时任务进入引导页
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(isFirst==false){
                    Intent intent = new Intent(SplashActivity.this, StartActivity.class);//进入启动页
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);//进入启动页
                    startActivity(intent);
                }
            }
        }, 2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
