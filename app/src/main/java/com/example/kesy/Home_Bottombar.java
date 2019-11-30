package com.example.kesy;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class Home_Bottombar extends AppCompatActivity {
    private ViewPager viewPager;
    private RadioGroup mTabRadioGroup;
    private View view1, view2, view3,view4;
    private List<View> myViewList;
    private List<Fragment> mFragments;
    private LocalActivityManager manager;
    private FragmentPagerAdapter mAdapter;
    private Intent intentComprehensive, intentRelease,intentFind, intentMy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottombar);
        viewPager = findViewById(R.id.fragment_vp);
        mTabRadioGroup = findViewById(R.id.tabs_rg);
        LayoutInflater inflater = getLayoutInflater();
        manager = new LocalActivityManager(this, true);
        manager.dispatchCreate(savedInstanceState);

        intentComprehensive = new Intent(Home_Bottombar.this, Home_Comprehensive.class);
        View view1 = manager.startActivity("viewID", intentComprehensive).getDecorView();
        intentRelease = new Intent(Home_Bottombar.this, Home_Release.class);
        View view2 = manager.startActivity("viewID", intentRelease).getDecorView();
        intentMy = new Intent(Home_Bottombar.this, Home_Find.class);
        View view3 = manager.startActivity("viewID", intentMy).getDecorView();
        intentFind = new Intent(Home_Bottombar.this, Home_My.class);
        View view4 = manager.startActivity("viewID", intentFind).getDecorView();

        myViewList = new ArrayList<View>();
        myViewList.add(view1);
        myViewList.add(view2);
        myViewList.add(view3);
        myViewList.add(view4);

        viewPager.setAdapter(new Mypager(myViewList));
        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), mFragments);
        viewPager.addOnPageChangeListener(mPageChangeListener);
        mTabRadioGroup.setOnCheckedChangeListener(mOnCheckedChangeListener);
        PagerAdapter pagerAdapter = new PagerAdapter() {
            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {

                return arg0 == arg1;
            }

            @Override
            public int getCount() {

                return myViewList.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                container.removeView(myViewList.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {

                container.addView(myViewList.get(position));


                return myViewList.get(position);
            }

        };
        viewPager.setAdapter(pagerAdapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewPager.removeOnPageChangeListener(mPageChangeListener);
    }

    private ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            RadioButton radioButton = (RadioButton) mTabRadioGroup.getChildAt(position);
            radioButton.setChecked(true);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    private RadioGroup.OnCheckedChangeListener mOnCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            for (int i = 0; i < group.getChildCount(); i++) {
                if (group.getChildAt(i).getId() == checkedId) {
                    viewPager.setCurrentItem(i);
                    return;
                }
            }
        }
    };

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> mList;

        public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.mList = list;
        }

        @Override
        public Fragment getItem(int position) {
            return this.mList == null ? null : this.mList.get(position);
        }

        @Override
        public int getCount() {
            return this.mList == null ? 0 : this.mList.size();
        }
    }
}
