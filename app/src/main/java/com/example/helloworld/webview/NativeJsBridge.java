package com.example.helloworld.webview;

import android.app.AlertDialog;
import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.helloworld.R;

import java.util.HashMap;
import java.util.Map;

public class NativeJsBridge {

    private Context context;
    public NativeJsBridge(Context context) {
        this.context = context;
    }

    //将显示Toast和对话框的方法暴露给JS脚本调用
    @JavascriptInterface
    public void showToast(String name) {
        Toast.makeText(context, name, Toast.LENGTH_SHORT).show();
    }
    @JavascriptInterface
    public void showDialog() {
        new AlertDialog.Builder(context)
                .setTitle("联系人列表").setIcon(R.mipmap.ic_launcher)
                .setItems(new String[]{"基神", "B神", "曹神", "街神", "翔神"}, null)
                .setPositiveButton("确定", null).create().show();
    }

    @JavascriptInterface
    public String getData(){

        Map<String,Object> map = new HashMap<>();
        map.put("123","123");
        String result = JSON.toJSONString(map);
        return result;
    }
}
