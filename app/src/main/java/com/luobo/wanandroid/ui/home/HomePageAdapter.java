package com.luobo.wanandroid.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.luobo.wanandroid.R;
import com.luobo.wanandroid.util.WebActivity;

class HomePageAdapter extends ListAdapter<ArticleDataBean.DataBean.DatasBean, HomePageAdapter.MyViewHolder> {

    private Context context;

    public HomePageAdapter(Context context, DiffUtil.ItemCallback<ArticleDataBean.DataBean.DatasBean> dataBeanItemCallback) {
        super(dataBeanItemCallback);
        this.context = context;

    }

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.home_item_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TextView textView = holder.itemView.findViewById(R.id.title);
        TextView classify = holder.itemView.findViewById(R.id.classify);
        textView.setText(getItem(position).getTitle());
        classify.setText(getItem(position).getChapterName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebActivity.class);
                intent.putExtra("URL", getItem(position).getLink());
                context.startActivity(intent);
            }
        });
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
