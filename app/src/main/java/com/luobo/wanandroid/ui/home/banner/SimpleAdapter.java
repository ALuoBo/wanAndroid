package com.luobo.wanandroid.ui.home.banner;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.luobo.wanandroid.R;
import com.luobo.wanandroid.WebActivity;
import com.luobo.wanandroid.base.MyApplication;
import com.zhpan.bannerview.BaseBannerAdapter;
import com.zhpan.bannerview.BaseViewHolder;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class SimpleAdapter extends BaseBannerAdapter<HomeBannerBean.DataBean> {
    Context context = MyApplication.getInstance();

    @Override
    protected void bindData(BaseViewHolder<HomeBannerBean.DataBean> holder, HomeBannerBean.DataBean data, int position, int pageSize) {
        ImageView imageView = holder.itemView.findViewById(R.id.banner_image);
        Glide.with(context).load(data.getImagePath()).into(imageView);
        Log.e("will", "bindData:" + data.getImagePath());
        holder.setOnClickListener(R.id.banner_image, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebActivity.class);
                intent.putExtra("URL", data.getUrl());
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_banner;
    }


}
