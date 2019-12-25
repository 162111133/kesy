package com.example.kesy.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kesy.R;
import com.example.kesy.adapter.Software_Adapter;
import com.example.kesy.bean.SoftBean;
import com.example.kesy.ui.fragment.activity.Find_soft;
import com.example.kesy.ui.fragment.activity.Soft_china;
import com.example.kesy.ui.fragment.activity.Soft_newsoft;
import com.example.kesy.ui.fragment.activity.Soft_opencompany;
import com.example.kesy.ui.fragment.activity.Soft_soft;

import java.util.ArrayList;
import java.util.List;

public class Comprehensive_software extends Fragment {
    private View view;
    public RecyclerView recyclerView;
    private Software_Adapter software_adapter;
    private ArrayList<SoftBean> softBeanList = new ArrayList<SoftBean>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.comprehensive_software,container,false);
        initRecyclerView();
        initData();
        ImageView soft = (ImageView)view.findViewById(R.id.comprehensive_sotf_soft);
        soft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getActivity(), Soft_soft.class);
                startActivity(intent1);
            }
        });
        ImageView china = (ImageView)view.findViewById(R.id.comprehensive_sotf_chia);
        china.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getActivity(), Soft_china.class);
                startActivity(intent1);
            }
        });
        ImageView newsoft = (ImageView)view.findViewById(R.id.comprehensive_sotf_newsoft);
        newsoft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getActivity(), Soft_newsoft.class);
                startActivity(intent1);
            }
        });
        ImageView opencompay = (ImageView)view.findViewById(R.id.comprehensive_sotf_opencompay);
        opencompay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getActivity(), Soft_opencompany.class);
                startActivity(intent1);
            }
        });
        return view;
    }

    private void initData() {
        SoftBean softBean = new SoftBean();
        softBean.setTitle("Hunt Redis");
        softBean.setPic(R.drawable.recommend);
        softBean.setMain_body("Hunt Redis 是使用D语言开发的Redis客户端，非常容易使用，ApI移植自自Jedis项目,兼容Redis2.8.x/ 3.x/4.x/5.x。基础特性:排序链接");
        softBean.setSmall_title("D 语言 Redis 客户端");
        softBeanList.add(softBean);

        SoftBean softBean2 = new SoftBean();
        softBean2.setTitle("Lars");
        softBean2.setPic(R.drawable.recommend);
        softBean2.setMain_body("(Load balance And Remote service schedule System)一、系统开发环境: Linux : Ubuntu18.04 protobuf : libprotoc 3.6.1版本及以.");
        softBean2.setSmall_title("基于C++ 负载均衡远程服务器调度系统");
        softBeanList.add(softBean2);

        SoftBean softBean3 = new SoftBean();
        softBean3.setTitle("NoahV");
        softBean3.setPic(R.drawable.recommend);
        softBean3.setMain_body("NoahV是-个致力于解决中后台前端效率问题的前端框架,立足于运维和监控的应用场景,使用当前前端最新的技术栈并结合团队在项目开发中的");
        softBean3.setSmall_title("立足于运维与监控的前端框架");
        softBeanList.add(softBean3);

        SoftBean softBean4 = new SoftBean();
        softBean4.setTitle("DataSphere Studio");
        softBean4.setPic(R.drawable.recommend);
        softBean4.setMain_body("DataSphere Studio (简称DSS)是微众银行大数据平台--WeDataSphere，自研的- -站式数据应用开发管理门户。基于Linkis计算");
        softBean4.setSmall_title("一站式数据应用开发管理门户");
        softBeanList.add(softBean4);

        SoftBean softBean5 = new SoftBean();
        softBean5.setTitle("G2Plot");
        softBean5.setPic(R.drawable.recommend);
        softBean5.setMain_body("一套简单、易用、并具备一定扩展能力和组合能力的统计图表库，基于图形语法理论搭建而成，[G2Plot]中的G2即意指图形语法(the Grammar 0...");
        softBean5.setSmall_title("开箱即用的图表库");
        softBeanList.add(softBean5);

        SoftBean softBean6 = new SoftBean();
        softBean6.setTitle("Kong-Kuma");
        softBean6.setPic(R.drawable.recommend);
        softBean6.setMain_body("Kuma是一个现代的通用服务网格控制平面。Kuma基于Envoy搭建;Envoy是一个为云原生应用设计的强大的代理软件。...");
        softBean6.setSmall_title("通用服务网格");
        softBeanList.add(softBean6);

        SoftBean softBean7 = new SoftBean();
        softBean7.setTitle("WebGLStudiojs");
        softBean7.setPic(R.drawable.recommend);
        softBean7.setMain_body("WebcLStudiojs是一个基于浏览器的3D图形套件。特性:完整的3D图形引擎(LiteScenejs)，支持多种光照、阴影贴...\n");
        softBean7.setSmall_title("基于浏览器的3D图形套件");
        softBeanList.add(softBean7);

        SoftBean softBean8 = new SoftBean();
        softBean8.setTitle("Hunt Redis");
        softBean8.setPic(R.drawable.recommend);
        softBean8.setMain_body("hunt Redis 是使用D语言开发的Redis客户端，非长容易使用，API移植紫Jedis项目，兼容Redis2.8.x");
        softBean8.setSmall_title("D 语言 Redis 客户端");
        softBeanList.add(softBean8);

    }

    private void initRecyclerView() {
        recyclerView =(RecyclerView)view.findViewById(R.id.soft_recyview);
        software_adapter = new Software_Adapter(getActivity(),softBeanList);
        recyclerView.setAdapter(software_adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));

    }
}
