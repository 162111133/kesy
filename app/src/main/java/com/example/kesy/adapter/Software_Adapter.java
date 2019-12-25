package com.example.kesy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kesy.R;
import com.example.kesy.bean.SoftBean;

import java.util.ArrayList;
import java.util.List;

public class Software_Adapter extends RecyclerView.Adapter<Software_Adapter.ViewHolder> {
    private List<SoftBean> softBeanList;
    private Context context;

    public Software_Adapter(Context context, ArrayList<SoftBean> softBeansList) {
        this.context = context;
        this.softBeanList = softBeansList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.software_item,null, false);
        Software_Adapter.ViewHolder viewHolder = new Software_Adapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        SoftBean data = softBeanList.get(i);
        viewHolder.sofeImage.setImageResource(data.getPic());
        viewHolder.title.setText(data.getTitle());
        viewHolder.small_title.setText(data.getSmall_title());
        viewHolder.main_body.setText(data.getMain_body());
    }

    @Override
    public int getItemCount() {
        return softBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView sofeImage;
        TextView title;
        TextView small_title;
        TextView main_body;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sofeImage = itemView.findViewById(R.id.soft_img);
            title = itemView.findViewById(R.id.soft_title);
            small_title = itemView.findViewById(R.id.small_title);
            main_body = itemView.findViewById(R.id.main_body);
        }
    }
}
