package com.flytexpress.sign.presenter;

/**
 * Created by Administrator on 2017/12/6.
 * 基础BasePresenter层，用于一些公共的接口方法
 */

public interface BasePresenter {
    void failed(Object o);//统一处理网络异常
    void destroy();//统一处理消失的时候
}
