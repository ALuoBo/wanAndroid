package com.luobo.wanandroid.ui.home.article;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

/*
 * 学习ListAdapter 可参考：
 * https://www.jianshu.com/p/7992060cc2cb
 * https://www.jianshu.com/p/66d0feab2b5b
 * */

public class ArticleDiffUtil extends DiffUtil.ItemCallback<ArticleBean.DatasBean> {

    @Override
    public boolean areItemsTheSame(@NonNull ArticleBean.DatasBean oldItem, @NonNull ArticleBean.DatasBean newItem) {

        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull ArticleBean.DatasBean oldItem, @NonNull ArticleBean.DatasBean newItem) {
        // 当areItemsTheSame返回true时，我们还需要判断两个item的内容是否相同
        return oldItem.getLink().equals(newItem.getLink());
    }

}
