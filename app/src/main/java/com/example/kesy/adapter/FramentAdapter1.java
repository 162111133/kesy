package com.example.kesy.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class FramentAdapter1 extends FragmentPagerAdapter {
    private String[] title = {"关注", "软件", "资讯", "推荐","问答","博客","英语"};
    private List<Fragment> fragmentList;

    public FramentAdapter1(FragmentManager fm, ArrayList<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
