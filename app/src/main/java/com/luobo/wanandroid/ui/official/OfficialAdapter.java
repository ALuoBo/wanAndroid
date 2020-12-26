package com.luobo.wanandroid.ui.official;

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

class OfficialAdapter extends ListAdapter<OfficialArticleBean.DataBean.DatasBean, OfficialAdapter.MViewHolder> {
    private Context context;

    protected OfficialAdapter(Context context, @NonNull DiffUtil.ItemCallback<OfficialArticleBean.DataBean.DatasBean> diffCallback) {
        super(diffCallback);
        this.context = context;
    }


    @NonNull
    @Override
    public MViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_article, parent, false);
        return new MViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MViewHolder holder, int position) {
        holder.niceTime.setText(getItem(position).getNiceDate());
        holder.textView.setText(getItem(position).getTitle());
        holder.classify.setText(getItem(position).getChapterName());
        holder.cardView.setOnClickListener(v -> {
            Intent intent = new Intent(context, WebActivity.class);
            intent.putExtra("URL", getItem(position).getLink());
            context.startActivity(intent);
        });

    }

    class MViewHolder extends RecyclerView.ViewHolder {
        TextView textView ,niceTime;
        TextView classify;
        CardView cardView;

        public MViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.title);
            classify = itemView.findViewById(R.id.classify);
            cardView = itemView.findViewById(R.id.articleItem);
            niceTime = itemView.findViewById(R.id.niceDate);
        }
    }
}
