package com.luobo.wanandroid.ui.home;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

/*
 * 学习ListAdapter 可参考：
 * https://www.jianshu.com/p/7992060cc2cb
 * */

public class ArticleDiffUtil extends DiffUtil.ItemCallback<ArticleDataBean.DataBean.DatasBean> {

    @Override
    public boolean areItemsTheSame(@NonNull ArticleDataBean.DataBean.DatasBean oldItem, @NonNull ArticleDataBean.DataBean.DatasBean newItem) {

        return oldItem == newItem;
    }

    @Override
    public boolean areContentsTheSame(@NonNull ArticleDataBean.DataBean.DatasBean oldItem, @NonNull ArticleDataBean.DataBean.DatasBean newItem) {

        return oldItem.getId() == newItem.getId();
    }
}
