package com.flytexpress.sign.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.os.MessageQueue;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flytexpress.sign.R;
import com.flytexpress.sign.ui.base.LazyLoadFragment;

import butterknife.ButterKnife;

/**
 * 禁止预加载，节省流量提高速度
 */
public class AccountFragment extends Fragment {
	private Context mContext;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	/*@Override
	protected int setContentView() {
		return R.layout.account_fragment;
	}

	@Override
	protected void lazyLoad() {
		View view =getContentView();//获取fragment加载的布局
		mContext=this.getActivity();
	}*/

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.account_fragment, null);
		Log.i("TAG","accountFragment的OnCreateView");
		return view;
	}
	/*@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		// TODO Auto-generated method stub
		super.setUserVisibleHint(isVisibleToUser);
		Log.i("TAG","Accountfragment的setUserVisibleHint");
		if(getUserVisibleHint()){
			Log.i("TAG","AccountFragment是可见的");
		}else{
			Log.i("TAG","AccountFragment是不可见的");
		}
	}*/
	
}


