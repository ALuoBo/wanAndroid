package com.luobo.wanandroid.ui.aq;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

class AqDiffUtil extends DiffUtil.ItemCallback<AqResponse.DataBean.DatasBean>  {
    @Override
    public boolean areItemsTheSame(@NonNull AqResponse.DataBean.DatasBean oldItem, @NonNull AqResponse.DataBean.DatasBean newItem) {

        return oldItem == newItem;
    }

    @Override
    public boolean areContentsTheSame(@NonNull AqResponse.DataBean.DatasBean oldItem, @NonNull AqResponse.DataBean.DatasBean newItem) {
        return oldItem.getId() == newItem.getId();
    }
}
