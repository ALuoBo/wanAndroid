package com.luobo.wanandroid.ui.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;

import com.luobo.wanandroid.R;
import com.luobo.wanandroid.ui.home.banner.HomeBannerBean;
import com.luobo.wanandroid.ui.home.banner.SimpleAdapter;
import com.zhpan.bannerview.BannerViewPager;

/**
 * Banner 控件有提供异步刷新数据方法
 */
class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private String TAG = this.getClass().getName();
    public static final int BANNER_VIEW_TYPE = -2; //Banner
    private Context context;
    private Lifecycle lifecycle;
    public HomeAdapter(Context context, Lifecycle lifecycle) {
        this.context = context;
        this.lifecycle = lifecycle;
    }



    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return BANNER_VIEW_TYPE;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(context).inflate(R.layout.banner, parent, false);
        return new BannerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        BannerViewPager<HomeBannerBean.DataBean> mBannerViewPager;

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            mBannerViewPager = itemView.findViewById(R.id.banner_view);
            mBannerViewPager.setLifecycleRegistry(lifecycle)
                    .setAdapter(new SimpleAdapter())
                    .create();
        }
    }

}
