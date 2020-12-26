package com.luobo.wanandroid.ui.home.article;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
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

public class ArticleAdapter extends ListAdapter<ArticleBean.DataBean.DatasBean, ArticleAdapter.ArticleViewHolder> {
    private Context context;

    public ArticleAdapter(Context context, @NonNull DiffUtil.ItemCallback<ArticleBean.DataBean.DatasBean> diffCallback) {
        super(diffCallback);
        this.context = context;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_article, parent, false);
        return new ArticleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        ArticleBean.DataBean.DatasBean articleBean = getCurrentList().get(position);
        if (articleBean.isFresh()) {
            Log.e("will", "onBindViewHolder: " + articleBean.isFresh());
            holder.newTag.setVisibility(View.VISIBLE);
        } else {
            holder.newTag.setVisibility(View.INVISIBLE);
        }
        holder.niceDate.setText(articleBean.getNiceDate());
        holder.textView.setText(Html.fromHtml(articleBean.getTitle()));
        holder.classify.setText(Html.fromHtml(articleBean.getChapterName()));
        holder.cardView.setOnClickListener(v -> {
            Intent intent = new Intent(context, WebActivity.class);
            intent.putExtra("URL", articleBean.getLink());
            context.startActivity(intent);
        });
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder {
        TextView textView, newTag, niceDate;
        TextView classify;
        CardView cardView;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.title);
            classify = itemView.findViewById(R.id.classify);
            cardView = itemView.findViewById(R.id.articleItem);
            newTag = itemView.findViewById(R.id.newTag);
            niceDate = itemView.findViewById(R.id.niceDate);
        }

    }
}
