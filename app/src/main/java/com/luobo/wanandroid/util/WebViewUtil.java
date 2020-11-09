package com.luobo.wanandroid.util;

import android.content.Context;
import android.webkit.WebView;

public class WebViewUtil {

    public static boolean browserWeb(Context context, String url) {
        WebView webView = new WebView(context);
        webView.loadUrl(url);
        return false;
    }
}
