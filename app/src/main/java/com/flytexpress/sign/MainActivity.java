package com.flytexpress.sign;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.flytexpress.sign.bean.login.LoginBean;
import com.flytexpress.sign.bean.login.LoginResult;
import com.flytexpress.sign.model.login.LoginView;
import com.flytexpress.sign.presenter.login.LoginPresenter;
import com.flytexpress.sign.ui.base.BaseActivity;
import com.flytexpress.sign.ui.main.MainFragmentPagerActivity;
import com.flytexpress.sign.util.DialogUtilPro;
import com.flytexpress.sign.util.GsonUtil;
import com.flytexpress.sign.util.MD5;
import com.flytexpress.sign.util.SharedPreferenceCache;
import com.flytexpress.sign.util.Tools;
import com.flytexpress.sign.util.common.DialogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.trinea.android.common.util.ToastUtils;

public class MainActivity extends BaseActivity implements LoginView {
    private final String Hand="Hand";
    @BindView(R.id.title_sell)
    ImageView titleSell;
    @BindView(R.id.title_version)
    TextView titleVersion;
    @BindView(R.id.title_version_tv)
    TextView titleVersionTv;
    @BindView(R.id.login_input_name)
    AutoCompleteTextView loginInputName;
    @BindView(R.id.r1)
    RelativeLayout r1;
    @BindView(R.id.login_input_password)
    EditText loginInputPassword;
    @BindView(R.id.r2)
    RelativeLayout r2;
    @BindView(R.id.login_comfirm_button)
    Button loginComfirmButton;
    @BindView(R.id.login_check_update)
    Button loginCheckUpdate;
    @BindView(R.id.lin_login)
    LinearLayout linLogin;
    @BindView(R.id.Re_layout)
    RelativeLayout ReLayout;
    private Context mContext;
    private LoginPresenter loginPresenter;
    private Dialog mProgressDialog;
    //drawable是拿一个数组来保存上下左右4个drawable对象的 分别对应0，1，2，3
    private static final int DRAWABLE_RIGHT = 2;
    private boolean mbDisplayFlg = false;
    private String nowVersion;
    public int NO_VISIBLE_PROGRESS=0;
    public int YES_VISIBLE_PROGRESS=1;

    @Override
    protected void onResume() {
        super.onResume();
        loginInputPassword.setText("");
        String account = SharedPreferenceCache.getInstance(mContext).getString("loginUserName");
        if (!Tools.isEmpty(account)) {
            loginInputName.setText(account);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        checkVersion(NO_VISIBLE_PROGRESS);
    }

    private void checkVersion(int isVisible) {
        loginPresenter.updateVersion(nowVersion,isVisible);
    }


    private void initView() {
        nowVersion = Tools.getVersionInfo(mContext, 1);
        titleVersionTv.setText("版本号：" + nowVersion);
        MainActivity mActivity = new MainActivity();
        loginPresenter = new LoginPresenter(this, mContext, mActivity);
        loginInputPassword.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 输入的内容变化的监听
                Log.i("输入过程中执行该方法", "文字变化");
                if (!Tools.isEmpty(s + "")) {
                    loginComfirmButton.setBackground(getResources().getDrawable(R.drawable.login_init_out));
                    loginComfirmButton.setTextColor(getResources().getColor(R.color.white));
                } else {
                    loginComfirmButton.setBackground(getResources().getDrawable(R.drawable.login_init_side));
                    loginComfirmButton.setTextColor(getResources().getColor(R.color.font_back));
                }
            }

            @SuppressLint("NewApi")
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // 输入前的监听
                Log.i("输入前确认执行该方法", "开始输入");
            }

            @Override
            public void afterTextChanged(Editable s) {
                // 输入后的监听
                Log.i("输入结束执行该方法", "输入结束");

            }
        });
        loginInputName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (loginInputName.getCompoundDrawables()[DRAWABLE_RIGHT] == null) {
                    return false;
                }
                //这里一定要对点击事件类型做一次判断，否则你的点击事件会被执行2次
                if (event.getAction() != MotionEvent.ACTION_UP) {
                    return false;
                }
                if (event.getX() > loginInputName.getWidth() - loginInputName.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width()) {
                    //do something you want
                    loginInputName.setText("");
                    return true;
                }
                return false;
            }
        });
        String account = SharedPreferenceCache.getInstance(mContext).getString("loginUserName");
        if (!Tools.isEmpty(account)) {
            loginInputName.setText(account);
        }
        loginInputPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (loginInputPassword.getCompoundDrawables()[DRAWABLE_RIGHT] == null) {
                    return false;
                }
                //这里一定要对点击事件类型做一次判断，否则你的点击事件会被执行2次
                if (event.getAction() != MotionEvent.ACTION_UP) {
                    return false;
                }
                if (event.getX() > loginInputPassword.getWidth() - loginInputName.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width()) {
                    //do something you want
                    if (!mbDisplayFlg) {
                        // display password text, for example "123456"
                        loginInputPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    } else {
                        // hide password, display "."
                        loginInputPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                    mbDisplayFlg = !mbDisplayFlg;
                    loginInputPassword.postInvalidate();
                    return true;
                }
                return false;
            }
        });
        Tools.setHintTextSize(loginInputName, getResources().getString(R.string.login_account_failed), 14);
        Tools.setHintTextSize(loginInputPassword, getResources().getString(R.string.login_password_failed), 14);
    }

    @OnClick({R.id.login_comfirm_button, R.id.login_check_update})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.login_comfirm_button://处理登录操作
                if (Tools.isEmpty(getUserName())) {
                    Toast.makeText(mContext, getResources().getString(R.string.login_account_failed), Toast.LENGTH_SHORT).show();
                } else if (Tools.isEmpty(getPassWord())) {
                    Toast.makeText(mContext, getResources().getString(R.string.login_password_failed), Toast.LENGTH_SHORT).show();
                } else {
                    String pass = MD5.MD5Encode(getPassWord(), "UTF-8", true);
                    LoginBean bean = new LoginBean();
                    bean.LoginName = getUserName();
                    bean.Password = pass;
                    bean.OperatorId = getUserName();
                    bean.Platform=Hand;
//                  MainFragmentPagerActivity.jumpMain(mContext);测试可用
                    loginPresenter.login(bean);

                }
                break;

            case R.id.login_check_update://点击了检测更新
                loginPresenter.updateVersion(nowVersion,YES_VISIBLE_PROGRESS);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (loginPresenter != null) {//防止内存泄露，回收内存
            loginPresenter.destroy();
            loginPresenter = null;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {//在内存回收的时候保存状态
        super.onSaveInstanceState(outState);
    }

    @Override
    public void deleteView() {

    }

    @Override
    public void showprogress() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showProgressDialog();
            }
        });
    }

    @Override
    public void hideprogress() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                hideProgressDialog();
            }
        });
    }

    @Override
    public void showToast(final String content) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtils.show(mContext, content);
            }
        });

    }

    @Override
    public void failed() {//需要写公共dialog，弹出框提示用户检查网络连接
        showToast(getResources().getString(R.string.net_connect_failed));
    }

    @Override
    public String getUserName() {
        return loginInputName.getText().toString().trim();
    }

    @Override
    public String getPassWord() {
        return loginInputPassword.getText().toString().trim();
    }

    @Override
    public void OnSuccesssLogin(LoginResult result) {//登录成功后自己处理例如跳转
        if (result.Success == true) {
            if(!Tools.isEmpty(result.RpsToken)){
                SignApplication.getInstance().setRpsToken(result.RpsToken);
                Log.e("TAG",SignApplication.getInstance().getRpsToken());
            }
            SharedPreferenceCache.getInstance(mContext).putString("loginUserName", getUserName());
            //保存登录信息
            String cacheStr = GsonUtil.getInstance()
                    .getStringFromJsonObject(result);
            SharedPreferenceCache.getInstance(mContext).putString(
                    "loginResult", cacheStr);
            MainFragmentPagerActivity.jumpMain(mContext);
        } else {
            showToast(result.Message);
        }
    }

    @Override
    public void OnFailed(LoginResult result) {
        ToastUtils.show(mContext, getResources().getText(R.string.login_faile));
    }

    // 显示加载框
    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = DialogUtilPro.showProgressDialog(mContext, "",
                    getString(R.string.please_wait), null);
        }
        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (!isFinishing()) {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.cancel();
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            DialogUtil.showTowButtonDialog(mContext, getResources().getString(R.string.dialog_title), getResources().getString(R.string.dialog_content), getResources().getString(R.string.dialog_confirm), getResources().getString(R.string.dialog_cancel), new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainActivity.this.finish();
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
