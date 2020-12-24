package com.luobo.wanandroid.ui.home.top;

import android.content.Context;
import android.content.Intent;
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


public class ToppingAdapter extends ListAdapter<ToppingBean.DataBean, ToppingAdapter.TopViewHolder> {
    private Context context;

    public ToppingAdapter(Context context, @NonNull DiffUtil.ItemCallback<ToppingBean.DataBean> diffCallback) {
        super(diffCallback);
        this.context = context;
    }


    @NonNull
    @Override
    public TopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_top, parent, false);
        return new TopViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull TopViewHolder holder, int position) {
        ToppingBean.DataBean bean = getCurrentList().get(position);
        holder.textView.setText(bean.getTitle());
        holder.classify.setText(bean.getChapterName());
        holder.cardView.setOnClickListener(v -> {
            Intent intent = new Intent(context, WebActivity.class);
            intent.putExtra("URL", bean.getLink());
            context.startActivity(intent);
        });

    }

    public class TopViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView classify;
        CardView cardView;

        public TopViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.topTitle);
            classify = itemView.findViewById(R.id.topClassify);
            cardView = itemView.findViewById(R.id.TopArticle);
        }
    }

}
