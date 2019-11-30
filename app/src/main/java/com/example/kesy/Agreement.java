package com.example.kesy;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;



import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Agreement extends AppCompatActivity {
    private  Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_agreement);
        ImageView back = (ImageView)findViewById(R.id.agr_img_back);
        toolbar = (Toolbar)findViewById(R.id.agr_tlb);
//        toolbar.setTitle("Circle软件许可及服务协议");
//        toolbar.setTitleTextColor(Color.parseColor("#FF0000"));
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
