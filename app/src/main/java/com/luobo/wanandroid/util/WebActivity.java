package com.luobo.wanandroid.util;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.luobo.wanandroid.BaseActivity;
import com.luobo.wanandroid.R;

import static android.view.KeyEvent.KEYCODE_BACK;

public class WebActivity extends BaseActivity {
    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mWebView = new WebView(getApplicationContext());
        addContentView(mWebView, params);
        initWebView();
        Intent intent = getIntent();
        mWebView.loadUrl(intent.getStringExtra("URL"));
    }

    public void initWebView() {
        mWebView.canGoBack();
        mWebView.setWebViewClient(new WebViewClient());

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setUseWideViewPort(true); //将图片调整到适合webView的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webSettings.setJavaScriptEnabled(true);//如果访问的页面中要与Javascript交互，则webView必须设置支持Javascript

    }

    //Back键控制网页后退
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if ((keyCode == KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);

    }


    @Override
    protected void onDestroy() {
        if (mWebView != null) {
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.clearHistory();
            ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();

    }
}