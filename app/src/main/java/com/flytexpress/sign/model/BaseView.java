package com.flytexpress.sign.model;

/**
 * Created by Administrator on 2017/12/6.
 */

public interface BaseView {
    void deleteView();//清空当前view的数据
    void showprogress();//显示加载条
    void hideprogress();//隐藏加载条
    void showToast(String content);//吐司接口
    void failed();//所有的请求网络异常
}
