package com.flytexpress.sign.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.flytexpress.sign.bean.view.PieData;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/5/18.
 * 扇形统计
 */

public class PieView extends View {
    private int[] mColor = {0};
    // 颜色表(注意: 此处定义颜色使用的是ARGB，带Alpha通道的)
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};
    private Paint mPaint = new Paint();
    private float mStartAngel = 0;
    private int mWidth, mHeight;
    // 数据
    private ArrayList<PieData> mData;
    // 饼状图初始绘制角度
    private float mStartAngle = 0;

    public PieView(Context context) {
        this(context, null);
    }

    public PieView(Context context, @Nullable AttributeSet attrs) {//初始化的时候回执行
        super(context, attrs);
    /*    mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.STROKE);*/
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (null == mData)
            return;
        float currentStartAngle = mStartAngle;                    // 当前起始角度
        canvas.translate(mWidth / 2, mHeight / 2);                // 将画布坐标原点移动到中心位置
        float r = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);  // 饼状图半径
        RectF rect = new RectF(-r, -r, r, r);                     // 饼状图绘制区域
        Log.d("TAG","来过了"+mData.size()+","+currentStartAngle);
        for (int i = 0; i < mData.size(); i++) {
            PieData pie = mData.get(i);
            mPaint.setColor(pie.getColor());
//            canvas.drawCircle(200,200,100,mPaint);

            canvas.drawArc(rect, currentStartAngle, pie.getAngle(), true, mPaint);
            currentStartAngle += pie.getAngle();
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    public void setData(ArrayList<PieData> mData) {
        this.mData = mData;
        Log.d("TAG", "看下值：" + mData.size());
        initData(mData);
        invalidate();   // 刷新,数据改变时，需要刷新（重绘）界面,调用此方法
    }

    private void initData(ArrayList<PieData> mData) {//初始化数据
        if (null == mData || mData.size() == 0)   // 数据有问题 直接返回
            return;

        float sumValue = 0;
        for (int i = 0; i < mData.size(); i++) {
            PieData pie = mData.get(i);

            sumValue += pie.getValue();       //计算数值和

            int j = i % mColors.length;       //设置颜色
            pie.setColor(mColors[j]);
        }

        float sumAngle = 0;
        for (int i = 0; i < mData.size(); i++) {
            PieData pie = mData.get(i);

            float percentage = pie.getValue() / sumValue;   // 百分比
            float angle = percentage * 360;                 // 对应的角度

            pie.setPercentage(percentage);                  // 记录百分比
            pie.setAngle(angle);                            // 记录角度大小
            sumAngle += angle;

            Log.i("angle", "" + pie.getAngle());
        }
    }

    public void setStartAngle(int startAngle) {//设置初始角度
        this.mStartAngel = startAngle;
        invalidate();   // 刷新
    }
}
