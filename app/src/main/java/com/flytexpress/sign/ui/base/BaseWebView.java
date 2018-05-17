package com.flytexpress.sign.ui.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Process;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.flytexpress.sign.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 直线进度条+webView(直接显示html界面)
 */
public class BaseWebView extends Activity {
    @BindView(R.id.title_back)
    ImageView titleBack;
    ProgressBar progressBar1;
    WebView webview1;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private WebView webView;
    private ProgressBar pg1;
    private String url, title, inputUrl;
    private String startUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        ButterKnife.bind(this);
        initView();
        inputUrl = getIntent().getStringExtra("url");
        title=getIntent().getStringExtra("title");
        tvTitle.setText(title);
        webView.loadUrl(inputUrl);
    }


    public static void jumpBaseWebView(Context context, String url, String title) {
        Intent in = new Intent();
        in.setClass(context, BaseWebView.class);
        in.putExtra("url", url);
        in.putExtra("title", title);
        context.startActivity(in);
    }

    private void initView() {
        // TODO 自动生成的方法存根
        Intent in = getIntent();
        url = in.getStringExtra("url");
        title = in.getStringExtra("title");
        webView = (WebView) findViewById(R.id.webview1);
        pg1 = (ProgressBar) findViewById(R.id.progressBar1);
        // 首先设置支持JS脚本
        webView.getSettings().setJavaScriptEnabled(true);
        // 支持Js在当前App打开应用，当页面跳转的时候依旧在当前的WebView之中
        webView.setWebViewClient(new WebViewClient() {
            //覆写shouldOverrideUrlLoading实现内部显示网页

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {//开始时的url
                super.onPageStarted(view, url, favicon);
                startUrl = url;//为了处理返回不了的问题  一些重定向的界面，经过返回的时候重新回到重定向界面，导致回不去 例如进入A-B-C 返回的时候进入了A又重定向了B，所以会一直返回不出去
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {//当网页面加载失败时，会调用 这个方法，所以我们在这个方法中处理
                super.onReceivedError(view, request, error);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {//加载后的url 判断有没有重定向
                // TODO 自动生成的方法存根
                view.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
//                view.loadUrl(url);
//                WebView.HitTestResult hitTestResult = view.getHitTestResult();
                //hitTestResult==null解决重定向问题

//                if (hitTestResult == null) {

                if (startUrl != null && startUrl.equals(url)) {//判断有没有重定向
                    view.loadUrl(url);
                } else {
                    //交给系统处理
                    return super.shouldOverrideUrlLoading(view, url);
                }
                return true;

//                }
            }
        });
        WebSettings seting = webView.getSettings();
        seting.setDomStorageEnabled(true);
        seting.setJavaScriptEnabled(true);//设置webview支持javascript脚本
        //设置webview自适应屏幕
        seting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        seting.setLoadWithOverviewMode(true);
        seting.setUseWideViewPort(true);
        seting.setLoadWithOverviewMode(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO 自动生成的方法存根

                if (newProgress == 100) {
                    pg1.setVisibility(View.GONE);//加载完网页进度条消失
                } else {
                    pg1.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    pg1.setProgress(newProgress);//设置进度值
                }

            }
        });

    }


    //设置返回键动作（防止按返回键直接退出程序)
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO 自动生成的方法存根
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {//当webview不是处于第一页面时，返回上一个页面
                webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
                webView.goBack();
                return true;
            } else {//当webview处于第一页面时,直接退出程序
                System.exit(0);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick(R.id.title_back)
    public void onViewClicked() {
        if (webView.canGoBack()) {//当webview不是处于第一页面时，返回上一个页面
            webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            webView.goBack();
        } else {//当webview处于第一页面时,直接退出程序
            this.finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.removeAllViews();
        webView.destroy();
        Process.killProcess(Process.myPid());
    }
}
