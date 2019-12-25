package com.example.kesy.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.kesy.R;
import com.example.kesy.adapter.Information_Adapter;
import com.example.kesy.adapter.Software_Adapter;
import com.example.kesy.bean.InforBean;
import com.example.kesy.bean.SoftBean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Comprehensive_information extends Fragment {
    private ViewPager mViewPaper;
    private List<ImageView> images;
    private List<View> dots;
    private int currentItem;
    //记录上一次点的位置
    private int oldPosition = 0;
    //存放图片的id
    private int[] imageIds = new int[]{
            R.drawable.infor_1,
            R.drawable.infor_2,
            R.drawable.infor_3,

    };
    //存放图片的标题
    private String[] titles = new String[]{
            "Intellij平台2020年路线图",
            "Vim8.2发布",
            "Ubuntu想在Windows的WSL中做到领先"
    };
    private TextView title;

    private ScheduledExecutorService scheduledExecutorService;
    private View view;
    public RecyclerView recyclerView;
    private Information_Adapter information_adapter;
    private ArrayList<InforBean> inforBeanList = new ArrayList<InforBean>();
    private ViewPagerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.comprehensive_information,container,false);
        initRecyclerView();
        initData();
        setView();
        return view;
    }

    private void setView() {
        mViewPaper= (ViewPager)view.findViewById(R.id.infor_viewpager);

        //显示的图片
        images = new ArrayList<ImageView>();
        for (int i = 0; i < imageIds.length; i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setBackgroundResource(imageIds[i]);
            images.add(imageView);
        }
        //显示的小点
        dots = new ArrayList<View>();
        dots.add(view.findViewById(R.id.infor_dot0));
        dots.add(view.findViewById(R.id.infor_dot1));
        dots.add(view.findViewById(R.id.infor_dot2));

        title = (TextView) view.findViewById(R.id.infor_title);
        title.setText(titles[0]);

        adapter = new ViewPagerAdapter();
        mViewPaper.setAdapter(adapter);

        mViewPaper.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {


            @Override
            public void onPageSelected(int position) {
                title.setText(titles[position]);
                dots.get(position).setBackgroundResource(R.drawable.dot_focused);
                dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);

                oldPosition = position;
                currentItem = position;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }
    public class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
            // TODO Auto-generated method stub
//          super.destroyItem(container, position, object);
//          view.removeView(view.getChildAt(position));
//          view.removeViewAt(position);
            view.removeView(images.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            // TODO Auto-generated method stub
            view.addView(images.get(position));
            return images.get(position);
        }

    }
    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(
                new ViewPageTask(),
                2,
                2,
                TimeUnit.SECONDS);
    }
    private class ViewPageTask implements Runnable{

        @Override
        public void run() {
            currentItem = (currentItem + 1) % imageIds.length;
            mHandler.sendEmptyMessage(0);
        }
    }
    private Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            mViewPaper.setCurrentItem(currentItem);
        }
    };
    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        if(scheduledExecutorService != null){
            scheduledExecutorService.shutdown();
            scheduledExecutorService = null;
        }
    }

    private void initRecyclerView() {
        recyclerView =(RecyclerView)view.findViewById(R.id.infor_recyview);
        information_adapter = new Information_Adapter(getActivity(),inforBeanList);
        recyclerView.setAdapter(information_adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));

    }

    private void initData() {
        InforBean inforBean = new InforBean();
        inforBean.setTitle("小说精品屋 v2.1.1发布，自适应小说阅读弹幕网站");
        inforBean.setPic(R.drawable.today);
        inforBean.setMain_body("小说精品屋-小说阅读弹幕网站v2.1.1版本发布了，主要改进包括：更新 爬虫自动更新程序优化，增加自动修复卒伍章节。新增项点小说网");
        inforBeanList.add(inforBean);
        InforBean inforBean1 = new InforBean();
        inforBean1.setTitle("OSCHINA APP更新，文章支持代码高亮");
        inforBean1.setPic(R.drawable.today);
        inforBean1.setMain_body("小伙伴们早上好,APP又有新版本更新来袭本次更新内容:发布动弹进度提醒全新优化文章详情样式改进,支持代码高亮新增用户协议和...");
        inforBeanList.add(inforBean1);
        InforBean inforBean2 = new InforBean();
        inforBean2.setTitle("GitLab12.6发布,可更高效地管理和共享c/c++资源");
        inforBean2.setPic(R.drawable.today);
        inforBean2.setMain_body("GitLab12.6已于近日发布,此版本增加了不少关于安全性方面的功能,可帮助用户更有效地监视应用程序安全性和发布项目的合规性。具有...");
        inforBeanList.add(inforBean2);
        InforBean inforBean3 = new InforBean();
        inforBean3.setTitle("SailfishOS3.2.1Nuuksio发布,移动操作系统");
        inforBean3.setPic(R.drawable.today);
        inforBean3.setMain_body("SailfishOS3.2.1Nuuksio现已发布。该版本进行了许多可靠性改进,特别是针对电子邮件,日历同步和VPN设置方面。除了提高可靠...");
        inforBeanList.add(inforBean3);
        InforBean inforBean4 = new InforBean();
        inforBean4.setTitle("TrensorFlow2.1.0-rc2发布");
        inforBean4.setPic(R.drawable.today);
        inforBean4.setMain_body("TensorFlow2.1.0-rc2发布了,TensorFlow2.1将是支持2的最后一个TF版本。Python2的支持将于2020年1月1日正式结...");
        inforBeanList.add(inforBean4);
        InforBean inforBean5 = new InforBean();
        inforBean5.setTitle("LinuxKernel5.5-rc3发布");
        inforBean5.setPic(R.drawable.today);
        inforBean5.setMain_body("目前,LinuxKernel5.5周期的第三个候选版本,即LinuxKernel5.5rc3正式发布。在Linux5.5-rc3的更新内容中,其中一项值...");
        inforBeanList.add(inforBean4);
        InforBean inforBean6 = new InforBean();
        inforBean6.setTitle("Serverless1.60.4发布,无服务器架构开发框架");
        inforBean6.setPic(R.drawable.today);
        inforBean6.setMain_body("Serverless架构开发框架ServerlessFramework发布了1.60.2、1.60.3和1.60.4版本,该框架使用AWSLambda、AzureFunctionsg...");
        inforBeanList.add(inforBean6);
    }
}
