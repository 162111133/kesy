package com.example.kesy.ui.fragment.activity;

import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kesy.R;
import com.example.kesy.utils.StatusBarUtil;

/*
 *创建者：LLR
 *日期：2019/12/25
 */public class Find_openbag extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_openbag);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        StatusBarUtil.StatusBarLightMode(this);
        WebView webView = (WebView)findViewById(R.id.openbag_view);
        webView.loadUrl("https://zb.oschina.net/projects/list.html");
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webView.requestFocus();
        ImageView back = (ImageView)findViewById(R.id.agr_img_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.d("KeithXiaoY","开始加载");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.d("KeithXiaoY","加载结束");
            }

            // 链接跳转都会走这个方法
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d("KeithXiaoY","Url："+ url );
                view.loadUrl(url);// 强制在当前 WebView 中加载 url
                return true;
            }
        });
    }
}
