package com.example.kesy.ui.fragment.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kesy.R;
import com.jaeger.library.StatusBarUtil;

public class Main_login extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_login);
        setStatusBar();
    }
    protected void setStatusBar() {
        //这里做了两件事情，1.使状态栏透明并使contentView填充到状态栏 2.预留出状态栏的位置，防止界面上的控件离顶部靠的太近。这样就可以实现开头说的第二种情况的沉浸式状态栏了
        StatusBarUtil.setTransparent(this);
    }

}
