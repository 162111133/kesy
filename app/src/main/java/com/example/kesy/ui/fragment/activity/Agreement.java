package com.example.kesy.ui.fragment.activity;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;



import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.kesy.R;
import com.example.kesy.utils.StatusBarUtil;

public class Agreement extends AppCompatActivity {
    private  Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_agreement);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        StatusBarUtil.setStatusBarColor(Agreement.this,R.color.bar);
        toolbar = (Toolbar)findViewById(R.id.agr_tlb);
        ImageView back = (ImageView)findViewById(R.id.agr_img_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
