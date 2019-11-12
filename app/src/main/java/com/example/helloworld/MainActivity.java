package com.example.helloworld;

import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;

import com.example.helloworld.webview.MyWebChromeClient;
import com.example.helloworld.webview.MyWebViewClient;
import com.example.helloworld.webview.NativeJsBridge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private WebView webView;
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //设置主标题
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
        setWebView();
    }


    /**
     * 创建webview
     */
    private void setWebView() {
        webView = findViewById(R.id.webview);
        webView.setWebViewClient(new MyWebViewClient(this, webView));
        webView.setWebChromeClient(new MyWebChromeClient(this, webView));
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);  //设置WebView属性,运行执行js脚本
        settings.setLoadWithOverviewMode(true);//自适应屏幕
        String userAgent = settings.getUserAgentString();
        settings.setUserAgentString(userAgent+";app/fish");//修改useragent用于服务端区分不同客户端
        //设置缓存模式
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // 开启DOM storage API 功能
        settings.setDomStorageEnabled(true);
        // 开启database storage API功能
        settings.setDatabaseEnabled(true);
        String cacheDirPath = getFilesDir().getAbsolutePath() + getString(R.string.app_cache_dir);
        // 设置数据库缓存路径
        settings.setAppCacheEnabled(true);
        settings.setAppCachePath(cacheDirPath);
        Log.i("cachePath", cacheDirPath);
        webView.addJavascriptInterface(new NativeJsBridge(this), "android");
        //调用loadUrl方法为WebView加入链接
        webView.loadUrl(getString(R.string.web_url));
    }


    //我们需要重写回退按钮的时间,当用户点击回退按钮：
    //1.webView.canGoBack()判断网页是否能后退,可以则goback()
    //2.如果不可以连续点击两次退出App,否则弹出提示Toast
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                super.onBackPressed();
            }

        }
    }
}
