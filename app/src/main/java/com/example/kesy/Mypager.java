package com.example.kesy;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class Mypager extends PagerAdapter {
    private List<View> myViewList;

    public Mypager(List<View> myViewList){
        this.myViewList = myViewList;
    }

    @Override
    public int getCount() {
        return myViewList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(myViewList.get(position));
        return myViewList.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(myViewList.get(position));
    }
}
