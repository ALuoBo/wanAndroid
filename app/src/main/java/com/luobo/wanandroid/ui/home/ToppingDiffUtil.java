package com.luobo.wanandroid.ui.home;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

/*
 * 学习ListAdapter 可参考：
 * https://www.jianshu.com/p/7992060cc2cb
 * */

public class ToppingDiffUtil extends DiffUtil.ItemCallback<ToppingBean.DataBean> {

    @Override
    public boolean areItemsTheSame(@NonNull ToppingBean.DataBean oldItem, @NonNull ToppingBean.DataBean newItem) {
        return oldItem == newItem;
    }

    @Override
    public boolean areContentsTheSame(@NonNull ToppingBean.DataBean oldItem, @NonNull ToppingBean.DataBean newItem) {
        return oldItem.getId() == newItem.getId();
    }
}
