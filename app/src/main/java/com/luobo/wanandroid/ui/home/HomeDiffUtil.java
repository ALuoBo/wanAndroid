package com.luobo.wanandroid.ui.home;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

class HomeDiffUtil extends DiffUtil.ItemCallback<HomeBean> {
    private String TAG = this.getClass().getName();

    @Override
    public boolean areItemsTheSame(@NonNull HomeBean oldItem, @NonNull HomeBean newItem) {

        if (oldItem.getViewType() != newItem.getViewType()) {
            Log.e(TAG, "areItemsTheSame:  type" +(oldItem.getViewType() != newItem.getViewType()) );
            return false;
        } else if (oldItem.getViewType() == HomePageAdapter.NORMAL_VIEW_TYPE) {
            ArticleBean.DataBean.DatasBean oldBean = (ArticleBean.DataBean.DatasBean) oldItem;
            ArticleBean.DataBean.DatasBean newBean = (ArticleBean.DataBean.DatasBean) newItem;
            Log.e(TAG, "areItemsTheSame NORMAL: "+ (oldBean.getId() == newBean.getId()) );
            return oldBean.getId() == newBean.getId();
        }
        if (oldItem.getViewType() == HomePageAdapter.TOP_VIEW_TYPE) {
            ToppingBean.DataBean oldBean = (ToppingBean.DataBean) oldItem;
            ToppingBean.DataBean newBean = (ToppingBean.DataBean) newItem;
            Log.e(TAG, "areItemsTheSame: TOP "+ (oldBean.getId() == newBean.getId()) );
            return oldBean.getId() == newBean.getId();
        }

        return true;
    }

    @Override
    public boolean areContentsTheSame(@NonNull HomeBean oldItem, @NonNull HomeBean newItem) {
        if (oldItem.getViewType() != newItem.getViewType()) {
            Log.e(TAG, "areContentTheSame:  type" +(oldItem.getViewType() != newItem.getViewType()) );
            return false;
        } else if (oldItem.getViewType() == HomePageAdapter.NORMAL_VIEW_TYPE) {
            ArticleBean.DataBean.DatasBean oldBean = (ArticleBean.DataBean.DatasBean) oldItem;
            ArticleBean.DataBean.DatasBean newBean = (ArticleBean.DataBean.DatasBean) newItem;
            Log.e(TAG, "areContentTheSame NORMAL: "+ (oldBean.getId() == newBean.getId()) );
            return oldBean.getId() == newBean.getId();
        }
        if (oldItem.getViewType() == HomePageAdapter.TOP_VIEW_TYPE) {
            ToppingBean.DataBean oldBean = (ToppingBean.DataBean) oldItem;
            ToppingBean.DataBean newBean = (ToppingBean.DataBean) newItem;
            Log.e(TAG, "areContentTheSame: TOP "+ (oldBean.getId() == newBean.getId()) );
            return oldBean.getId() == newBean.getId();
        }
        return true;
    }
}
