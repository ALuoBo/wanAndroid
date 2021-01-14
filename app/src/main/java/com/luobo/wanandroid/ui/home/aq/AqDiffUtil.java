package com.luobo.wanandroid.ui.home.aq;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

class AqDiffUtil extends DiffUtil.ItemCallback<AqResponse.DatasBean>  {
    @Override
    public boolean areItemsTheSame(@NonNull AqResponse.DatasBean oldItem, @NonNull AqResponse.DatasBean newItem) {

        return oldItem == newItem;
    }

    @Override
    public boolean areContentsTheSame(@NonNull AqResponse.DatasBean oldItem, @NonNull AqResponse.DatasBean newItem) {
        return oldItem.getId() == newItem.getId();
    }
}
