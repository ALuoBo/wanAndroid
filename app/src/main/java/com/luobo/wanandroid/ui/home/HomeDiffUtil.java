package com.luobo.wanandroid.ui.home;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

class HomeDiffUtil extends DiffUtil.ItemCallback<HomeBean> {
    @Override
    public boolean areItemsTheSame(@NonNull HomeBean oldItem, @NonNull HomeBean newItem) {
        return oldItem == newItem;
    }

    @Override
    public boolean areContentsTheSame(@NonNull HomeBean oldItem, @NonNull HomeBean newItem) {

        if (oldItem.getViewType() != newItem.getViewType()) {
            return false;
        } else if (oldItem.getViewType() == HomePageAdapter.NORMAL_VIEW_TYPE) {
            ArticleBean.DataBean.DatasBean oldBean = (ArticleBean.DataBean.DatasBean) oldItem;
            ArticleBean.DataBean.DatasBean newBean = (ArticleBean.DataBean.DatasBean) newItem;
            return oldBean.getId() == newBean.getId();
        }
        if (oldItem.getViewType() == HomePageAdapter.HEADER_VIEW_TYPE) {
            ToppingBean.DataBean oldBean = (ToppingBean.DataBean) oldItem;
            ToppingBean.DataBean newBean = (ToppingBean.DataBean) newItem;
            return oldBean.getId() == newBean.getId();
        }
        return true;
    }
}
