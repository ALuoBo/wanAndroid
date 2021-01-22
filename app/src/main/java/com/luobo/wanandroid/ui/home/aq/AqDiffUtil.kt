package com.luobo.wanandroid.ui.home.aq

import androidx.recyclerview.widget.DiffUtil
import com.luobo.wanandroid.ui.home.aq.AqResponse

internal class AqDiffUtil : DiffUtil.ItemCallback<AqResponse.DatasBean>() {
    override fun areItemsTheSame(oldItem: AqResponse.DatasBean, newItem: AqResponse.DatasBean): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: AqResponse.DatasBean, newItem: AqResponse.DatasBean): Boolean {
        return oldItem.id == newItem.id
    }
}