package com.flytexpress.sign.ui.average;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.flytexpress.sign.R;
import com.flytexpress.sign.bean.view.PieData;
import com.flytexpress.sign.ui.base.BaseActivity;
import com.flytexpress.sign.view.PieView;

import java.util.ArrayList;
import java.util.List;

public class CircleActivity extends BaseActivity {
    private PieView pieView;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle);
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initView() {
        pieView= (PieView) findViewById(R.id.pie_view);
        ArrayList<PieData> list=new ArrayList<PieData>();
        PieData data=new PieData("1",0.02f);
        data.setPercentage(0.02f);
        data.setAngle(0.03f);
        data.setColor(getColor(R.color.black));
        PieData data2=new PieData("1",0.02f);
        data.setPercentage(0.03f);
        data.setAngle(0.08f);
        data.setColor(getColor(R.color.black));
        list.add(data);
        list.add(data2);
        pieView.setStartAngle(20);
        pieView.setData(list);
    }
}
