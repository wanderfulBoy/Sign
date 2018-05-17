package com.flytexpress.sign.ui.sign_function;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.flytexpress.sign.R;
import com.flytexpress.sign.ui.sign.SignActivity;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LandscapeActivity extends Activity {
    @BindView(R.id.clear1)
    Button mClear;
    @BindView(R.id.save1)
    Button mSave;
   /* @BindView(R.id.change)
    Button change;
    @BindView(R.id.changewidth)
    Button changewidth;*/
    @BindView(R.id.view)
    com.flytexpress.sign.ui.sign_function.LinePathView pathView;
    @BindView(R.id.ll)
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_hand_write);
        ButterKnife.bind(this);
        setResult(50);
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pathView.getTouched()) {
                    try {
                        pathView.save(SignActivity.pathImage, false, 10);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    setResult(101);
                    finish();
                } else {
                    Toast.makeText(LandscapeActivity.this, "您没有签名~", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pathView.clear();
            }
        });
    }


}
