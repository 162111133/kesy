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
import com.example.kesy.adapter.FramentAdapter2;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class Home_Release extends Fragment {
    private String[] mTitle = new String[4];
    private String[] mData = new String[4];

    {
        for (int i = 0; i < 4; i++) {
            mTitle[i] = "title" + i;
            mData[i] = "data" + i;
        }
    }

    TabLayout mTabLayout;
    ViewPager mViewPager;
    FragmentPagerAdapter mAdapter;
    ArrayList<Fragment> flist;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_release, container, false);
        mTabLayout = view.findViewById(R.id.home_release_tablayout);
        mViewPager = view.findViewById(R.id.home_releas_vipg);
        FragmentManager man = getActivity().getSupportFragmentManager();
        flist = new ArrayList<Fragment>();
        flist.add(new Release_new());
        flist.add(new Release_hot());
        flist.add(new Release_topic());
        flist.add(new Release_chaos());
        flist.add(new Release_my());

        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        final TabLayout.TabLayoutOnPageChangeListener listener =
                new TabLayout.TabLayoutOnPageChangeListener(mTabLayout);
        mViewPager.addOnPageChangeListener(listener);
        mAdapter = new FramentAdapter2(man, flist);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setTabsFromPagerAdapter(mAdapter);
        return view;
    }
}

