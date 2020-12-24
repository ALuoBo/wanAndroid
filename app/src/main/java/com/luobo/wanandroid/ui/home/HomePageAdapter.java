package com.luobo.wanandroid.ui.home;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.luobo.wanandroid.R;
import com.luobo.wanandroid.WebActivity;
import com.luobo.wanandroid.ui.home.banner.HomeBannerBean;
import com.luobo.wanandroid.ui.home.banner.SimpleAdapter;
import com.zhpan.bannerview.BannerViewPager;

/**
 * 多布局类型Adapter ，但由于Adapter 只能传入一种类型的Bean ，所以需要整合这些不同数据为一个Bean
 * Banner 控件有提供异步刷新数据方法，故不需要将其的Bean合入
 */
class HomePageAdapter extends ListAdapter<HomeBean, RecyclerView.ViewHolder> {
    private String TAG = this.getClass().getName();
    public static final int BANNER_VIEW_TYPE = -2; //Banner
    public static final int TOP_VIEW_TYPE = -1; //Top
    public static final int NORMAL_VIEW_TYPE = 0;
    public static final int FOOTER_VIEW_TYPE = 1;//Foot

    private Context context;
    private Lifecycle lifecycle;

    public HomePageAdapter(Context context, Lifecycle lifecycle, DiffUtil.ItemCallback<HomeBean> dataBeanItemCallback) {
        super(dataBeanItemCallback);
        this.context = context;
        this.lifecycle = lifecycle;
    }

    @Override
    public int getItemCount() {
        //增加banner+1，增加footer+1；
        return super.getItemCount() + 2;
    }

    @Override
    public int getItemViewType(int position) {
        Log.e(TAG, "onCreateViewHolder: itemCount " + getItemCount());

        if (position == 0) {
            return BANNER_VIEW_TYPE;
        }
        if (position == getItemCount() - 1) {
            return FOOTER_VIEW_TYPE;
        }
        return getCurrentList().get(position - 1).getViewType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        switch (viewType) {
            case BANNER_VIEW_TYPE:
                itemView = LayoutInflater.from(context).inflate(R.layout.banner, parent, false);
                return new BannerViewHolder(itemView);
            case TOP_VIEW_TYPE:
                itemView = LayoutInflater.from(context).inflate(R.layout.item_head, parent, false);
                return new TopViewHolder(itemView);
            case FOOTER_VIEW_TYPE:
                itemView = LayoutInflater.from(context).inflate(R.layout.footer, parent, false);
                return new FooterViewHolder(itemView);
            case NORMAL_VIEW_TYPE:
                itemView = LayoutInflater.from(context).inflate(R.layout.item_article, parent, false);
                return new NormalViewHolder(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.e(TAG, "onBindViewHolder: position" + position);

        if (position == 0) {
            //banner
            return;
        }
        if (position == getItemCount() - 1) {
            //footer
            return;
        } else {
            int type = getItemViewType(position);
            Log.e(TAG, "onBindViewHolder: type" + type);

            switch (type) {
                case TOP_VIEW_TYPE:
                    ToppingBean.DataBean toppingBean = (ToppingBean.DataBean) getCurrentList().get(position - 1);
                    TopViewHolder topViewHolder = (TopViewHolder) holder;
                    topViewHolder.textView.setText(toppingBean.getTitle());
                    topViewHolder.classify.setText(toppingBean.getChapterName());
                    topViewHolder.cardView.setOnClickListener(v -> {
                        Intent intent = new Intent(context, WebActivity.class);
                        intent.putExtra("URL", toppingBean.getLink());
                        context.startActivity(intent);
                    });
                    break;

                case NORMAL_VIEW_TYPE:
                    ArticleBean.DataBean.DatasBean articleBean = (ArticleBean.DataBean.DatasBean) getCurrentList().get(position - 1);
                    NormalViewHolder viewHolder = (NormalViewHolder) holder;
                    viewHolder.textView.setText(articleBean.getTitle());
                    viewHolder.classify.setText(articleBean.getChapterName());
                    viewHolder.cardView.setOnClickListener(v -> {
                        Intent intent = new Intent(context, WebActivity.class);
                        intent.putExtra("URL", articleBean.getLink());
                        context.startActivity(intent);
                    });
                    break;
            }

        }

    }

    public class BannerViewHolder extends RecyclerView.ViewHolder {
        BannerViewPager<HomeBannerBean.DataBean> mBannerViewPager;

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            mBannerViewPager = itemView.findViewById(R.id.banner_view);
            mBannerViewPager.setLifecycleRegistry(lifecycle)
                    .setAdapter(new SimpleAdapter())
                    .create();
        }
    }

    public class TopViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView classify;
        CardView cardView;

        public TopViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.topTitle);
            classify = itemView.findViewById(R.id.topClassify);
            cardView = itemView.findViewById(R.id.TopArticle);
        }
    }

    public class NormalViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView classify;
        CardView cardView;

        public NormalViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.title);
            classify = itemView.findViewById(R.id.classify);
            cardView = itemView.findViewById(R.id.articleItem);
        }

    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
