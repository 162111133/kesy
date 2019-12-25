package com.example.kesy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kesy.R;
import com.example.kesy.bean.InforBean;

import java.util.ArrayList;
import java.util.List;

public class Information_Adapter extends RecyclerView.Adapter<Information_Adapter.ViewHolder> {
    private List<InforBean> InforBeanList;
    private Context context;
    public Information_Adapter(Context context, ArrayList<InforBean> InforBeanList) {
        this.context = context;
        this.InforBeanList = InforBeanList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.information_item,null, false);
        Information_Adapter.ViewHolder viewHolder = new Information_Adapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        InforBean data1 = InforBeanList.get(i);
        viewHolder.inforImage.setImageResource(data1.getPic());
        viewHolder.title.setText(data1.getTitle());
        viewHolder.main_body.setText(data1.getMain_body());
    }

    @Override
    public int getItemCount() {
        return InforBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView inforImage;
        TextView title;
        TextView main_body;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            inforImage = itemView.findViewById(R.id.infor_img);
            title = itemView.findViewById(R.id.infor_title);
            main_body = itemView.findViewById(R.id.infor_boby);
        }
    }
}
