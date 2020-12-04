package com.luobo.wanandroid.ui.home;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.luobo.wanandroid.R;
import com.luobo.wanandroid.WebActivity;

class ToppingAdapter extends ListAdapter<ToppingBean.DataBean, ToppingAdapter.MyViewHolder> {

    private static final int NORMAL_VIEW_TYPE = 0;
    private static final int FOOTER_VIEW_TYPE = 1;

    private Context context;

    public ToppingAdapter(@NonNull DiffUtil.ItemCallback<ToppingBean.DataBean> diffCallback, Context context) {
        super(diffCallback);
        this.context = context;
    }

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        switch (viewType) {
            default:
                itemView = LayoutInflater.from(context).inflate(R.layout.topping, parent, false);
                break;
        }

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TextView textView = holder.itemView.findViewById(R.id.toppingTitle);
        textView.setText(Html.fromHtml(getItem(position).getTitle()));
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, WebActivity.class);
            intent.putExtra("URL", getItem(position).getLink());
            context.startActivity(intent);
        });
        ;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
