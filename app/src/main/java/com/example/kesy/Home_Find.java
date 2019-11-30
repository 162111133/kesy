package com.example.kesy;

import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Home_Find extends AppCompatActivity {
    private ViewPager viewPager;
    private List<ImageView> imageViews;
    private String[] titles;
    private int[] imageResId;
    private List<View> dots;
    private TextView tv_title;
    private ScheduledExecutorService scheduledExecutorService;
    private int currentItem = 0;
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            viewPager.setCurrentItem(currentItem);// 切换当前显示的图片
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_find);
        imageResId = new int[]{R.drawable.img1, R.drawable.img2,};
        titles = new String[imageResId.length];
        titles[0] = "2019年度最受欢迎中国开源软件评选";
        titles[1] = "Linux、国产OS、Win 10、Android、Ubuntu与Zorin OS";
        imageViews = new ArrayList<ImageView>();
        for (int i = 0; i < imageResId.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(imageResId[i]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageViews.add(imageView);
        }
        dots = new ArrayList<View>();
        dots.add(findViewById(R.id.v_dot0));
        dots.add(findViewById(R.id.v_dot1));
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText(titles[0]);

        viewPager = (ViewPager) findViewById(R.id.vp);
        viewPager.setAdapter(new MyAdapter());

        viewPager.setOnPageChangeListener(new MyPageChangeListener());
    }
    @Override
    protected void onStart() {
        super.onStart();
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // 当Activity显示出来后，每两秒钟切换一次图片显示
        scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 2, 2, TimeUnit.SECONDS);
    }
    private class ScrollTask implements Runnable {

        public void run() {
            synchronized (viewPager) {
                System.out.println("currentItem: " + currentItem);
                currentItem = (currentItem + 1) % imageViews.size();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.obtainMessage().sendToTarget(); // 通过Handler切换图片
            }
        }

    }
    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {
        private int oldPosition = 0;

        public void onPageSelected(int position) {
            currentItem = position;
            tv_title.setText(titles[position]);
            dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
            dots.get(position).setBackgroundResource(R.drawable.dot_focused);
            oldPosition = position;
        }

        public void onPageScrollStateChanged(int arg0) {

        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }
    }

    private class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageResId.length;
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(imageViews.get(arg1));
            return imageViews.get(arg1);
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView((View) arg2);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        public void restoreState(Parcelable arg0, ClassLoader arg1) {

        }

        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {

        }

        @Override
        public void finishUpdate(View arg0) {

        }

    }
}
