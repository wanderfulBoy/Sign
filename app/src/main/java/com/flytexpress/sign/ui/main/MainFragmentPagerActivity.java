package com.flytexpress.sign.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.flytexpress.sign.MainActivity;
import com.flytexpress.sign.R;
import com.flytexpress.sign.adapter.MainFragmentPagerAdapter;
import com.flytexpress.sign.util.Tools;
import com.flytexpress.sign.util.common.DialogUtil;
import com.flytexpress.sign.view.CustomViewPager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainFragmentPagerActivity extends FragmentActivity implements View.OnClickListener {

    @BindView(R.id.viewPager)
    CustomViewPager viewPager;
    @BindView(R.id.main_rb_home)
    RadioButton mainRbHome;
    @BindView(R.id.main_rb_category)
    RadioButton mainRbCategory;
    @BindView(R.id.main_rb_linliRoll)
    RadioButton mainRbLinliRoll;
    @BindView(R.id.main_radioGroup)
    RadioGroup mainRadioGroup;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.title_invisible)
    ImageView titleInvisible;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.lin_nouse)
    LinearLayout linNouse;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_right_text)
    TextView titleRightText;
    private Context mContext;
    MainFragmentPagerAdapter adapter;
    private ArrayList<Fragment> datas;
    private HomeFragment homeFragment = null;
    private AccountFragment accountFragment = null;
    private SettingFragment settingFragment = null;
    private RadioButton[] btnArray;
    private int currentPageIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_main_fragment);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        int doHeigth = Tools.getStatusBarHeight(mContext);
        titleName.setText("首页");
        titleBack.setVisibility(View.GONE);
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainFragmentPagerActivity.this.finish();
            }
        });
        adapter = new MainFragmentPagerAdapter(this.getSupportFragmentManager(), null);
        viewPager.setAdapter(adapter);
        setupView();
        addListener();
//		setButtonColor();
        viewPager.setCurrentItem(0);
        final HomeFragment leftFragment = new HomeFragment();
       /* leftFragment.getEditText(new CallBack() {

            @Override
            public void getResult(String result) {
                // TODO Auto-generated method stub
//				viewpager.setCurrentItem(1);
                Log.d("TAG", result);
            }
        });*/
     /*   RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(viewPager.getLayoutParams());
        lp.setMargins(0,doHeigth,0,0);
        viewPager.setLayoutParams(lp);*/
    }

    private void setupView() {
        datas = new ArrayList<Fragment>();
        datas.add(homeFragment = new HomeFragment());
        datas.add(accountFragment = new AccountFragment());
        datas.add(settingFragment = new SettingFragment());


        adapter.setDatas(datas);
        adapter.notifyDataSetChanged();


		/*btnArray=new Button[]{
                (Button)findViewById(R.id.btnFriendList),
				(Button)findViewById(R.id.btnGroupChat),
				(Button)findViewById(R.id.btnTopicList),
		};*/
        btnArray = new RadioButton[]{
                (RadioButton) findViewById(R.id.main_rb_home),
                (RadioButton) findViewById(R.id.main_rb_category),
                (RadioButton) findViewById(R.id.main_rb_linliRoll),
        };
    }

    public static void jumpMain(Context context) {
        Intent intent = new Intent(context, MainFragmentPagerActivity.class);
        context.startActivity(intent);
    }

    private void addListener() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int index) {
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageSelected(int pageIndex) {
                currentPageIndex = pageIndex;
                //		setButtonColor();
                if (pageIndex == 0) {
                    titleName.setText("首页");
                } else if (pageIndex == 1) {
                    titleName.setText("账户");
                } else if (pageIndex == 2) {
                    titleName.setText("更多");
                }
                btnArray[currentPageIndex].setChecked(true);
            }
        });

        for (Button btn : btnArray) {
            btn.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        try {
            switch (view.getId()) {
                case R.id.main_rb_home:
                    titleName.setText("首页");
                    currentPageIndex = 0;
                    break;
                case R.id.main_rb_category:
                    titleName.setText("账户");
                    currentPageIndex = 1;
                    break;
                case R.id.main_rb_linliRoll:
                    titleName.setText("更多");
                    currentPageIndex = 2;
                    break;
            }
            viewPager.setCurrentItem(currentPageIndex);
           /* new Handler().post(new Runnable() {
                @Override
                public void run() {
                    viewPager.setCurrentItem(currentPageIndex); //Where "2" is the position you want to go
                }
            });*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            DialogUtil.showTowButtonDialog(mContext, getResources().getString(R.string.dialog_title), getResources().getString(R.string.dialog_content), getResources().getString(R.string.dialog_confirm), getResources().getString(R.string.dialog_cancel), new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainFragmentPagerActivity.this.finish();
                }
            }, new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
        return super.onKeyDown(keyCode, event);
    }
}
