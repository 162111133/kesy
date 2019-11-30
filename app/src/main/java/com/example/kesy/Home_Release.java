package com.example.kesy;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;

public class Home_Release extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_release);
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tb1);

        tabLayout.addTab(tabLayout.newTab().setText("最新"));
        tabLayout.addTab(tabLayout.newTab().setText("热门"));
        tabLayout.addTab(tabLayout.newTab().setText("话题"));
        tabLayout.addTab(tabLayout.newTab().setText("乱弹"));
        tabLayout.addTab(tabLayout.newTab().setText("我的"));


        tabLayout.setOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}

