package com.flytexpress.sign.bean.view;

import android.support.annotation.NonNull;

/**
 * Created by Administrator on 2018/5/18.
 */

public class PieData {
    // 用户关心数据
    private String name;        // 名字
    private float value;        // 数值
    private float percentage;   // 百分比

    // 非用户关心数据
    private int color = 0;      // 颜色
    private float angle = 0;    // 角度

    public PieData(@NonNull String name, @NonNull float value) {
        this.name = name;
        this.value = value;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getAngle() {
        return angle;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }
}
