package com.luobo.wanandroid.ui.official;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.luobo.wanandroid.ui.project.ProjectContentBean;


public class OfficialDiffUtil extends DiffUtil.ItemCallback<OfficialBean.DataBean.DatasBean> {
    @Override
    public boolean areItemsTheSame(@NonNull OfficialBean.DataBean.DatasBean oldItem, @NonNull OfficialBean.DataBean.DatasBean newItem) {
        return oldItem == newItem;
    }
    @Override
    public boolean areContentsTheSame(@NonNull OfficialBean.DataBean.DatasBean oldItem, @NonNull OfficialBean.DataBean.DatasBean newItem) {
        return oldItem.getId() == newItem.getId();
    }
}
