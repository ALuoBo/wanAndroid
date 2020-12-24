package com.luobo.wanandroid.ui.home.top;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.luobo.wanandroid.ui.home.top.ToppingBean;

/*
 * 学习ListAdapter 可参考：
 * https://www.jianshu.com/p/7992060cc2cb
 * */

public class ToppingDiffUtil extends DiffUtil.ItemCallback<ToppingBean.DataBean> {

    @Override
    public boolean areItemsTheSame(@NonNull ToppingBean.DataBean oldItem, @NonNull ToppingBean.DataBean newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull ToppingBean.DataBean oldItem, @NonNull ToppingBean.DataBean newItem) {
        return false;
    }
}
