package com.flytexpress.sign.ui.sign;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKLocationManager;
import com.baidu.mapapi.MapActivity;
import com.flytexpress.sign.R;
import com.flytexpress.sign.bean.login.LoginResult;
import com.flytexpress.sign.bean.sign.PKGInfoRequest;
import com.flytexpress.sign.bean.sign.TransferParentInfoResult;
import com.flytexpress.sign.bean.sign.TransferReceiveParam;
import com.flytexpress.sign.bean.sign.TransferReceiveResult;
import com.flytexpress.sign.config.BaseConfig;
import com.flytexpress.sign.model.sign.SignView;
import com.flytexpress.sign.presenter.sign.SignPresenter;
import com.flytexpress.sign.ui.sign_function.LandscapeActivity;
import com.flytexpress.sign.util.DialogUtilPro;
import com.flytexpress.sign.util.GsonUtil;
import com.flytexpress.sign.util.SharedPreferenceCache;
import com.flytexpress.sign.util.Tools;
import com.flytexpress.sign.util.common.DialogUtil;
import com.flytexpress.sign.util.location.LocationImp;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.common.Constant;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.trinea.android.common.util.ToastUtils;

/**
 * 签名界面，继承mapActivity，实现百度地图接口，获取当前经纬度，权限在BaseActivity中已经获取（用系统自带的方法）
 */
public class SignActivity extends MapActivity implements SignView, com.baidu.mapapi.LocationListener {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_right_text)
    TextView titleRightText;
    @BindView(R.id.title_invisible)
    ImageView titleInvisible;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.bu_sweep)
    Button buSweep;
    @BindView(R.id.et_scanPKG)
    EditText etScanPKG;
    @BindView(R.id.bu_summitImage)
    Button buSummitImage;
    @BindView(R.id.bu_seeImage)
    Button buSeeImage;
    @BindView(R.id.tv_postytype)
    TextView tvPostytype;
    @BindView(R.id.tv_totalWeight)
    TextView tvTotalWeight;
    @BindView(R.id.tv_deliverWeight)
    TextView tvDeliverWeight;
    @BindView(R.id.tv_detailTotalWeight)
    TextView tvDetailTotalWeight;
    @BindView(R.id.tv_see)
    TextView tvSee;
    @BindView(R.id.bu_receiveSummit)
    Button buReceiveSummit;
    @BindView(R.id.tv_hasReceiveQuaility)
    TextView tvHasReceiveQuaility;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    private Context mContext;
    LocationManager locationManager;
    private TextView tv_see;
    private String locationProvider;
    private BMapManager mapManager;
    private MKLocationManager mLocationManager = null;
    private static final int DRAWABLE_RIGHT = 2;
    private ImageView mImageView;
    private Dialog dialog;
    public static String pathImage = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "ls.png";
    private SignPresenter signPresenter;
    private LoginResult loginbean;
    private Dialog customerDialog;
    private TransferParentInfoResult transferParentInfoResult;
    private String success_Ptid;//前一个已经成功的PKG的邮递方式
    private Dialog mProgressDialog;
    private TransferReceiveResult receiveResult;//签收结果类
    private String receivePkgBatchId = "";//保存上次签收成功的批次号，备用
    private int count;//数量
    //    private String assetPath= "file:///android_asset/account.png";
    private String assetPath = "account.png";
    private String scanResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        mContext = this;
        ButterKnife.bind(this);
        SignActivity signActivity = new SignActivity();
        signPresenter = new SignPresenter(mContext, this, signActivity);
        initGeneralData();
        initView();
        initScanClick();
//      testHttp();
//      initBaiDuMap();//初始化百度SDK获取经纬度
    }

    private void testHttp() {//本地测试使用
        PKGInfoRequest info = new PKGInfoRequest();
        info.Pkg = "PKG17102015049";
        info.OperatorId = loginbean.OperatorId;
        info.OperatorName = loginbean.CnName;
        info.ReceiveStationId = loginbean.ReceiveStationId;
        signPresenter.GetPkgInfo(info);
    }

    private void initGeneralData() {
        String loginResult = SharedPreferenceCache.getInstance(mContext).getString(
                "loginResult");
        if (loginResult != null && !"".equals(loginResult)) {
            loginbean = GsonUtil.getInstance().getServerBean(loginResult,
                    LoginResult.class);
            Log.i("TAG", loginbean.OperatorId + "--" + loginbean.ReceiveStationId + "--" + loginbean.ProcessCenterId + "--" + loginbean.UserName);
        } else {
        }
    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }

    @Override
    protected void onDestroy() {
        if (mapManager != null) {
            mapManager.destroy();
            mapManager = null;
        }
        mLocationManager = null;
        super.onDestroy();
    }

    @OnClick({R.id.bu_sweep, R.id.bu_seeImage, R.id.bu_summitImage, R.id.bu_receiveSummit})
    public void clickSweep(View view) {
        switch (view.getId()) {
            case R.id.bu_sweep:
                Intent intent = new Intent(mContext, CaptureActivity.class);
         /*ZxingConfig是配置类  可以设置是否显示底部布局，闪光灯，相册，是否播放提示音  震动等动能
         * 也可以不传这个参数
         * 不传的话  默认都为默认不震动  其他都为true
         * */

                //ZxingConfig config = new ZxingConfig();
                //config.setShowbottomLayout(true);//底部布局（包括闪光灯和相册）
                //config.setPlayBeep(true);//是否播放提示音
                //config.setShake(true);//是否震动
                //config.setShowAlbum(true);//是否显示相册
                //config.setShowFlashLight(true);//是否显示闪光灯
                //intent.putExtra(Constant.INTENT_ZXING_CONFIG, config);
                startActivityForResult(intent, BaseConfig.ZXING_REQUEST_CODE_SCAN);
                break;
            case R.id.bu_seeImage:
                File file = new File(pathImage);
                if (!file.exists()) {
                    ToastUtils.show(mContext, "签名不存在，请先前往签名");
                } else {
                    mImageView = getImageView();
                    dialog = new Dialog(mContext, R.style.AlertDialog_AppCompat_Light);
                    dialog.setContentView(mImageView);

                    //大图的点击事件（点击让他消失）
                    mImageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
                break;
            case R.id.bu_summitImage:
                if(Tools.isEmpty(etScanPKG.getText().toString())){
                    ToastUtils.show(mContext,"请先扫描PKG！");
                }else {
                    startActivityForResult(new Intent(mContext, LandscapeActivity.class), 1);
                }
                break;
            case R.id.bu_receiveSummit:
//                InputStream is=mContext.getClass().getClassLoader().getResourceAsStream("assets/"+assetPath);
                if (!Tools.isEmpty(etScanPKG.getText().toString())) {
                    File outfile = new File(pathImage);
                    if (outfile.exists()) {
                        String imageStr = Tools.imageToBase64(pathImage, null);
                        TransferReceiveParam d = new TransferReceiveParam();
                        d.OperatorId = loginbean.OperatorId;
                        d.OperatorName = loginbean.UserName;
                        d.ReceiveStationId = loginbean.ReceiveStationId;
                        d.pkg = etScanPKG.getText().toString();
                        d.PostTypeId = success_Ptid;
                        d.SignNameByte = imageStr;
                        signPresenter.transperReceiveRequest(d);
                    } else {
                        ToastUtils.show(mContext, "请前往进行签名后提交！");
                    }
                } else {
                    ToastUtils.show(mContext, mContext.getResources().getString(R.string.input_scan_edit));
                }
                break;
        }
    }

    private void dealImage() {

    }

    //动态的ImageView
    private ImageView getImageView() {
        ImageView iv = new ImageView(this);
        //宽高
        iv.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        //设置Padding
        iv.setPadding(20, 20, 20, 20);
        //imageView设置图片
        /*InputStream is = getResources().openRawResource(R.mipmap.login_sign);
        Drawable drawable = BitmapDrawable.createFromStream(is, null);
        iv.setImageDrawable(drawable);*/
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        Bitmap bm = BitmapFactory.decodeFile(pathImage, options);
        if (bm == null) {
            ToastUtils.show(mContext, "请先前往进行签名！");
        } else {
            iv.setImageBitmap(bm);
        }
        return iv;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 扫描二维码/条码回传

        if (requestCode == BaseConfig.ZXING_REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {
                scanResult = data.getStringExtra(Constant.CODED_CONTENT);
                Log.e("TAG", "扫描值：" + scanResult);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        etScanPKG.setText(scanResult);
                        if (!Tools.isEmpty(scanResult)) {
                            summitPKG();
                        }
                    }
                });

            }
        } else if (resultCode == 101) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            /*Bitmap bm = BitmapFactory.decodeFile(path1, options);
            img2.setImageBitmap(bm);*/
            //自动提交
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    buReceiveSummit.performClick();
                }
            });
        }
    }

    private void summitPKG() {
        PKGInfoRequest info = new PKGInfoRequest();
        info.Pkg = etScanPKG.getText().toString();
        info.OperatorId = loginbean.OperatorId;
        info.OperatorName = loginbean.CnName;
        info.ReceiveStationId = loginbean.ReceiveStationId;
        signPresenter.GetPkgInfo(info);
    }


    /**
     * 根据MyLocationOverlay配置的属性确定是否在地图上显示当前位置
     */
    @Override
    protected boolean isLocationDisplayed() {
        return false;
    }

    /**
     * 当位置发生变化时触发此方法
     *
     * @param location 当前位置
     */
    public void onLocationChanged(Location location) {
        if (location != null) {
            // 显示定位结果 (最新)
            tv_see.setText("当前经纬度：" + location.getLongitude() + "," + location.getLatitude());
            tvLocation.setText("当前经纬度："+location.getLongitude() + "," + location.getLatitude());
//            latText.setText("当前纬度：" + location.getLatitude());
        }
    }

    @Override
    protected void onPause() {
        if (mapManager != null) {
            mapManager.stop();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        if (mapManager != null) {
            mapManager.start();
        }
        super.onResume();
    }


    private void initScanClick() {
        etScanPKG.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (etScanPKG.getCompoundDrawables()[DRAWABLE_RIGHT] == null) {
                    return false;
                }
                //这里一定要对点击事件类型做一次判断，否则你的点击事件会被执行2次
                if (event.getAction() != MotionEvent.ACTION_UP) {
                    return false;
                }
                if (event.getX() > etScanPKG.getWidth() - etScanPKG.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width()) {
                    //do something you want
                    buSweep.performClick();
                    return true;
                }
                return false;
            }
        });
    }

    private void initBaiDuMap() {//使用百度SDK获取当前经纬度
        mapManager = new BMapManager(getApplication());
        // init方法的第一个参数需填入申请的API Key
        Log.e("TAG", "vlaye" + mapManager);
        if (mapManager == null) {
            return;
        }
        mapManager.init("C66C0501D0280744759A6957C42543AE38F5D540", null);
        super.initMapActivity(mapManager);

        mLocationManager = mapManager.getLocationManager();
        // 注册位置更新事件
        mLocationManager.requestLocationUpdates(SignActivity.this);
        // 使用GPS定位
        mLocationManager.enableProvider((int) MKLocationManager.MK_GPS_PROVIDER);
    }

    private void initView() {
        Drawable drawableEtScan = getResources().getDrawable(R.mipmap.sweep);
        drawableEtScan.setBounds(0, 0, 62, 62);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
        etScanPKG.setCompoundDrawables(null, null, drawableEtScan, null);//只放右边，根据setBounds的值
        etScanPKG.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // 在这里编写自己想要实现的功能
                    summitPKG();
                }
                return false;
            }
        });
        titleName.setText(getString(R.string.title_textview_receive));
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignActivity.this.finish();
            }
        });
        tv_see = (TextView) findViewById(R.id.tv_see);
        String contextService = Context.LOCATION_SERVICE;
        LocationImp imp = new LocationImp();
        locationManager = (LocationManager) getSystemService(contextService);
        List<String> providers = locationManager.getProviders(true);
        if (providers.contains(LocationManager.GPS_PROVIDER)) {
            //如果是GPS
            locationProvider = LocationManager.GPS_PROVIDER;
        } else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
            //如果是Network
            locationProvider = LocationManager.NETWORK_PROVIDER;
        } else {
            Toast.makeText(this, "没有可用的位置提供器", Toast.LENGTH_SHORT).show();
            return;
        }
        Tools.setHintTextSize(etScanPKG, "请输入或者扫描包裹号", 14);
        Location location = imp.getLocation(mContext);
        if (location != null) {
            //不为空,显示地理位置经纬度
            String locationStr = "维度：" + location.getLatitude() + "\n"
                    + "经度：" + location.getLongitude();
            tv_see.setText(locationStr);
        }
        tv_see = (TextView) findViewById(R.id.tv_see);

        //获取Location
       /* Location location = locationManager.getLastKnownLocation(locationProvider);
        if(location!=null){
            //不为空,显示地理位置经纬度
            String locationStr = "维度：" + location.getLatitude() +"\n"
                    + "经度：" + location.getLongitude();
            tv_see.setText(locationStr);
     }else{
            Log.e("TAG","空的啊");
        }*/
        LocationListener locationListener = new LocationListener() {
            public void onStatusChanged(String provider, int status,
                                        Bundle extras) {
                // TODO Auto-generated method stub
            }

            public void onProviderEnabled(String provider) {
                // TODO Auto-generated method stub
                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                String locationStr = "维度：" + location.getLatitude() + "\n"
                        + "经度：" + location.getLongitude();
                tvLocation.setText("位置："+location.getLatitude()+","+location.getLatitude());
                tv_see.setText(locationStr);
            }

            public void onProviderDisabled(String provider) {
                // TODO Auto-generated method stub
            }

            public void onLocationChanged(Location location) {
                double lat = location.getLatitude();
                double lon = location.getLongitude();
                Log.e("TAG", "经度：" + String.valueOf(lat));
                Log.e("TAG", "纬度：" + String.valueOf(lon));
               // ToastUtils.show(mContext, "地理位置：" + lat + "," + lon);
                tvLocation.setText("位置："+lat+","+lon);
//                tv_see.setText(String.valueOf(lat) + "," + String.valueOf(lon));
            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(locationProvider, 2000, 10,
                locationListener);

    }

    public static void jumpSignAct(Context context) {
        Intent in = new Intent(context, SignActivity.class);
        context.startActivity(in);
    }

    @Override
    public void deleteView() {

    }

    @Override
    public void showprogress() {
        showProgressDialog();
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
    public void showToast(String content) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                hideprogress();
            }
        });
    }

    @Override
    public String getPKG() {
        return null;
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
    public void getPKGSuccess(TransferParentInfoResult result) {
        Log.e("TAG", "获取PKG信息成功了" + result.Success);
        hideprogress();
        transferParentInfoResult = result;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (transferParentInfoResult.Success == true) {
                    tvPostytype.setText("邮递方式：" + transferParentInfoResult.PostTypeName);
                    tvTotalWeight.setText("件数：" + transferParentInfoResult.TransferParentModel.Quantities);
                    tvDeliverWeight.setText("发起重量：" + transferParentInfoResult.TransferParentModel.LaunchWeight);
                    tvDetailTotalWeight.setText("明细总重量：" + transferParentInfoResult.TransferParentModel.DeliveredWeight);
                } else {
                    customerDialog = DialogUtil.showOneButtonDialog(mContext, "系统提示", "" + transferParentInfoResult.Message, "确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            customerDialog.hide();
                        }
                    });
                }
            }
        });

    }

    @Override
    public void failed() {

    }

    @Override
    public void summitInfoSuccess(TransferReceiveResult transferReceiveResult) {
        receiveResult = transferReceiveResult;
        hideprogress();
        clearSignData();
        Log.i("TAG", "结果：" + receiveResult.Success + receiveResult.Data);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (receiveResult.Success == true) {
                    count++;
                    etScanPKG.setText("");
                    tvHasReceiveQuaility.setText("已签收数量：" + count);
                    clearData();
                    if (!Tools.isEmpty(receiveResult.PostTypeId)) {
                        success_Ptid = receiveResult.PostTypeId;
                    }
                    receivePkgBatchId = receiveResult.BatchId;
                } else {
                    etScanPKG.setText("");
                    customerDialog = DialogUtil.showOneButtonDialog(mContext, "系统提示", "" + receiveResult.Message, "确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            customerDialog.hide();
                            clearData();
                        }
                    });
                }
            }

        });
    }
    //清空签名数据
    private void clearSignData() {
        File file = new File(pathImage);
        if (file.exists()) {
            file.delete();
        }
    }

    private void clearData() {
        tvDetailTotalWeight.setText("明细总重量：" + 0);
        tvPostytype.setText("邮递方式：");
        tvTotalWeight.setText("总件数：" + 0);
        tvDeliverWeight.setText("出发地重量：" + 0);
    }


}
