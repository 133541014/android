package com.example.helloworld.webview;

import android.app.Activity;
import android.net.http.SslError;
import android.os.Build;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebViewClient extends WebViewClient {

    private Activity activity;
    private WebView webView;

    public MyWebViewClient(Activity activity,WebView webView) {
        this.activity = activity;
        this.webView = webView;
    }

    //设置在webView点击打开的新网页在当前界面显示,而不跳转到新的浏览器中
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
    }
}
