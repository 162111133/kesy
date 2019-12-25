package com.example.kesy.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.kesy.R;
import com.example.kesy.adapter.FramentAdapter1;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class Home_Comprehensive extends Fragment {
    private String[] mTitle = new String[4];
    private String[] mData = new String[4];

    {
        for (int i = 0; i < 4; i++) {
            mTitle[i] = "title" + i;
            mData[i] = "data" + i;
        }
    }

    TabLayout mTabLayout1;
    ViewPager mViewPager1;
    FragmentPagerAdapter fmAdapter;
    ArrayList<Fragment> flist;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_comprehensive, container, false);
        mTabLayout1 = view.findViewById(R.id.home_compre_tablayout);
        mViewPager1 = view.findViewById(R.id.home_compre_vipg);
        FragmentManager man = getActivity().getSupportFragmentManager();
        flist = new ArrayList<Fragment>();
        flist.add(new Comprehensive_concern());
        flist.add(new Comprehensive_software());
        flist.add(new Comprehensive_information());
        flist.add(new Comprehensive_recommend());
        flist.add(new Comprehensive_question());
        flist.add(new Comprehensive_blogs());
        flist.add(new Comprehensive_english());

        mTabLayout1.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager1.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        final TabLayout.TabLayoutOnPageChangeListener listener =
                new TabLayout.TabLayoutOnPageChangeListener(mTabLayout1);
        mViewPager1.addOnPageChangeListener(listener);
        fmAdapter = new FramentAdapter1(man, flist);
        mViewPager1.setAdapter(fmAdapter);
        mTabLayout1.setTabsFromPagerAdapter(fmAdapter);
        return view;
    }

}
