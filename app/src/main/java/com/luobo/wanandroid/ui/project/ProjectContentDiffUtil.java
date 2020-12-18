package com.luobo.wanandroid.ui.project;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;


public class ProjectContentDiffUtil extends DiffUtil.ItemCallback<ProjectContentBean.DataBean.DatasBean> {
    @Override
    public boolean areItemsTheSame(@NonNull ProjectContentBean.DataBean.DatasBean oldItem, @NonNull ProjectContentBean.DataBean.DatasBean newItem) {
        return oldItem == newItem;
    }

    @Override
    public boolean areContentsTheSame(@NonNull ProjectContentBean.DataBean.DatasBean oldItem, @NonNull ProjectContentBean.DataBean.DatasBean newItem) {
        return oldItem.getId() == newItem.getId();
    }
}
