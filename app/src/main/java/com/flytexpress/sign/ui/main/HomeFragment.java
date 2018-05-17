package com.flytexpress.sign.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flytexpress.sign.MainActivity;
import com.flytexpress.sign.R;
import com.flytexpress.sign.ui.base.LazyLoadFragment;
import com.flytexpress.sign.ui.sign.SignActivity;
import com.flytexpress.sign.util.Tools;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.trinea.android.common.util.ToastUtils;

public class HomeFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.image_lin)
    LinearLayout imageLin;
    /*@BindView(R.id.sign_jump)
    Button signJump;*/
    Unbinder unbinder;
    private MainFragmentPagerActivity mA;
    private Context mContext;
    private int doHeigth;
    private int[] imageUrl={R.mipmap.driver_sign};//首页功能按钮
    private int[] imageStr={R.string.sign_name};
    private int[] imagViews={};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = View.inflate(this.getActivity(), R.layout.home_fragment, null);
        mA = new MainFragmentPagerActivity();
        mContext=getActivity();
        Log.i("TAG","homeFragment的OnCreateView");
        setView(v);
        unbinder = ButterKnife.bind(this, v);
        return v;
    }


    private void setView(View v) {//使用addView自己添加布局，可以随时调整布局和视图
//        View view=LayoutInflater.from(mContext).inflate(R.layout.image_layout,null);
        imageLin= (LinearLayout) v.findViewById(R.id.image_lin);
        imageLin.removeAllViews();
        for (int i = 0; i <imageUrl.length ; i++) {
            final int flag=i;
            View view = LayoutInflater.from(mContext).inflate(R.layout.image_layout,imageLin,false);//三种方法各有不同，此时是false ，imageLin布局wrap等参数有效果，true时直接引用，不需要addview
//          View view=LayoutInflater.from(mContext).inflate(R.layout.image_layout,null);
            ImageView imageView= (ImageView) view.findViewById(R.id.layout_image);
            TextView textView= (TextView) view.findViewById(R.id.layout_tv);
            imageView.setBackgroundResource(imageUrl[i]);
            textView.setText(imageStr[i]);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//          params.width = 200;
//          params.height = 200;
//          params.topMargin = 200;
            params.setMargins(20,0,0,0);
            imageView.setTag(i);//设置标志，用于点击的时候触发
            view.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
//                  int position = (Integer) view.getTag();
                  if(flag==0){
                      SignActivity.jumpSignAct(mContext);
                  }else if(flag==1){
//                      Log.e("TAG","value:"+position+","+flag);
//                      MainFragmentPagerActivity.jumpMain(mContext);
                  }
              }
           });
             imageLin.addView(view);
        }

        doHeigth= Tools.getStatusBarHeight(mContext);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

   /* @Override
    protected int setContentView() {
        return R.layout.home_fragment;
    }

    @Override
    protected void lazyLoad() {
        mA = new MainFragmentPagerActivity();
        mContext=getActivity();
        Log.i("TAG","homeFragment的OnCreateView");
        View view =getContentView();//获取fragment加载的布局
        setView(view);
        unbinder = ButterKnife.bind(this, view);
    }*/

    /*@OnClick(R.id.sign_jump)
    public void jumpSign(){
        SignActivity.jumpSignAct(getActivity());
    }*/
   /* @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        // TODO Auto-generated method stub
        super.setUserVisibleHint(isVisibleToUser);
        Log.i("TAG","homeFragment的setUserVisibleHint");
        if(getUserVisibleHint()){
            Log.i("TAG","HomeFragment是可见的");
        }else{
            Log.i("TAG","HomeFragment是不可见的");
        }
    }*/
}



