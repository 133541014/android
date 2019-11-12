package com.example.helloworld.webview;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.example.helloworld.MainActivity;
import com.example.helloworld.R;

public class MyWebChromeClient extends WebChromeClient {

    private Activity activity;
    private WebView webView;

    public MyWebChromeClient(Activity activity,WebView webView) {
        this.activity = activity;
        this.webView = webView;
    }

    @Override
    public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
        //创建一个Builder来显示网页中的对话框
        new AlertDialog.Builder(activity).setTitle("Alert对话框").setMessage(message)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                }).setCancelable(false).show();
        return true;
    }

    @Override
    public boolean onJsConfirm(WebView view, String url, String message,
                               final JsResult result) {
        new AlertDialog.Builder(activity).setTitle("Confirm对话框").setMessage(message)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.cancel();
                    }
                }).setCancelable(false).show();
        return true;
    }



}
