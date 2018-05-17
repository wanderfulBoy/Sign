package com.flytexpress.sign.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flytexpress.sign.R;
import com.flytexpress.sign.config.MainConfig;
import com.flytexpress.sign.ui.base.BaseWebView;
import com.flytexpress.sign.util.Tools;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SettingFragment extends Fragment {
    @BindView(R.id.version_tel)
    TextView versionTel;
    @BindView(R.id.updateVersion)
    TextView updateVersion;
    @BindView(R.id.more)
    TextView more;
    Unbinder unbinder;
    @BindView(R.id.more_rela)
    RelativeLayout moreRela;
    @BindView(R.id.tv_watch_version)
    TextView tvWatchVersion;
    @BindView(R.id.re_watchversion)
    RelativeLayout reWatchversion;
    private Context mContext;
    public final String url = "http://www.flytexpress.com/Home/Content?id=39e35bbe-0de3-4b14-23db-62dca3a7928e&item=Help&language=Chinese";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_fragment, null);
        unbinder = ButterKnife.bind(this, view);
        mContext = this.getActivity();
        Log.i("TAG", "settingFragment的OnCreateView");
        setupView(view);
//        addListener();
        return view;
    }

    @OnClick({R.id.more_rela,R.id.re_watchversion})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.more_rela:
                BaseWebView.jumpBaseWebView(mContext, url, "货运知识");
                break;
            case R.id.re_watchversion:
                BaseWebView.jumpBaseWebView(mContext, MainConfig.APKURL+"/RpsIterateRecord/FlytExpressSign","版本更新信息");
                break;
        }
    }

    private void addListener() {
    }

    private void setupView(View view) {
        //  versionTel= (TextView) view.findViewById(R.id.version_tel);
        String version = Tools.getVersionInfo(mContext, 1);
        versionTel.setText("版本号:" + version);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

   /* @Override
    protected int setContentView() {
        return R.layout.setting_fragment;
    }

    @Override
    protected void lazyLoad() {
        View view =getContentView();
        unbinder = ButterKnife.bind(this, view);
        mContext=this.getActivity();
        setupView(view);
    }*/

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = this.getActivity();
    }
   /* @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        // TODO Auto-generated method stub
        super.setUserVisibleHint(isVisibleToUser);
        Log.i("TAG","settingfragment的setUserVisibleHint");
        if(getUserVisibleHint()){//用户可见的
            Log.i("TAG","SettingFragment是可见的");
        }else{//用户不可见，即ui还未初始化，可以进行网络请求获取数据等
            Log.i("TAG","SettingFragment是不可见的");
        }
    }*/
}
