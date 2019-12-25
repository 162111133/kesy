package com.example.kesy.ui.fragment.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kesy.R;
import com.example.kesy.utils.StatusBarUtil;

/*
 *创建者：LLR
 *日期：2019/12/25
 */public class Soft_newsoft extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comprehensive_software_newsoft);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        StatusBarUtil.StatusBarLightMode(this);
        WebView webView = (WebView)findViewById(R.id.openbag_view);
        webView.loadUrl("https://www.oschina.net/news/project");
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
    }
}
