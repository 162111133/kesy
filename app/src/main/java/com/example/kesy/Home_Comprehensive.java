package com.example.kesy;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class Home_Comprehensive extends AppCompatActivity {
    private TabLayout myTabLayout;
    private ViewPager myViewPager;
    private List<Fragment> fragments;
    private String[] table = {"关注", "软件","咨询", "推荐","问答","博客","英文"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_comprehensive);
        myTabLayout = (TabLayout) findViewById(R.id.tb1);
        myViewPager = (ViewPager) findViewById(R.id.myviewpage1);
        fragments = new ArrayList<>();
        fragments.add(new Comprehensive_concern());
        fragments.add(new Comprehensive_software());
        fragments.add(new Comprehensive_information());
        fragments.add(new Comprehensive_recommend());
        fragments.add(new Comprehensive_question());
        fragments.add(new Comprehensive_blogs());
        fragments.add(new Comprehensive_english());
        Adapter adapter=new Adapter(getSupportFragmentManager(),fragments);
        myViewPager.setAdapter(adapter);
        myTabLayout.setupWithViewPager(myViewPager);

    }
    class Adapter extends FragmentPagerAdapter{
        private List<Fragment> list;
        public Adapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.list=list;
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        //重写getPageTitle()方法，获取页标题
        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return table[position];
        }
    }
}
