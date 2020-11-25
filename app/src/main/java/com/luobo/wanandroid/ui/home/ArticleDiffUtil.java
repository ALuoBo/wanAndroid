package com.luobo.wanandroid.ui.home;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;


public class ArticleDiffUtil extends DiffUtil.ItemCallback<ArticleDataBean.DataBean.DatasBean> {

    @Override
    public boolean areItemsTheSame(@NonNull ArticleDataBean.DataBean.DatasBean oldItem, @NonNull ArticleDataBean.DataBean.DatasBean newItem) {
        return false;
    }

    @Override
    public boolean areContentsTheSame(@NonNull ArticleDataBean.DataBean.DatasBean oldItem, @NonNull ArticleDataBean.DataBean.DatasBean newItem) {
        return false;
    }
}
