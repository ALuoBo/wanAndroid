package com.luobo.wanandroid.ui.official;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;


public class OfficialDiffUtil extends DiffUtil.ItemCallback<OfficialArticleBean.DataBean.DatasBean> {
    @Override
    public boolean areItemsTheSame(@NonNull OfficialArticleBean.DataBean.DatasBean oldItem, @NonNull OfficialArticleBean.DataBean.DatasBean newItem) {
        return oldItem == newItem;
    }
    @Override
    public boolean areContentsTheSame(@NonNull OfficialArticleBean.DataBean.DatasBean oldItem, @NonNull OfficialArticleBean.DataBean.DatasBean newItem) {
        return oldItem.getId() == newItem.getId();
    }
}
