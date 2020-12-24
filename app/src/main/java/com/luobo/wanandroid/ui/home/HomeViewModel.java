package com.luobo.wanandroid.ui.home;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.luobo.wanandroid.ui.home.banner.HomeBannerBean;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends AndroidViewModel {
    private String TAG =this.getClass().getName();
    private HomePageRepository articleRepository;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        articleRepository = HomePageRepository.getInstance();
    }

    MutableLiveData<HomeBannerBean> getBanner() {
        return articleRepository.getBanner();
    }

    private MutableLiveData<ToppingBean> getTopping() {
        return articleRepository.getTopping();
    }

     MutableLiveData<ArticleBean> getData() { return articleRepository.getArticle(); }

    /**
     * 合并置顶与普通文章
     */
    private MediatorLiveData<List<HomeBean>> liveDataMerger = new MediatorLiveData();

    public MediatorLiveData<List<HomeBean>> getHomeData() {
        Log.e(TAG, "getHomeData: ==========");
        ArrayList<HomeBean> data = new ArrayList<>();

        liveDataMerger.addSource(getTopping(), toppingBean -> {
            for (HomeBean bean : toppingBean.getData()
            ) {
                bean.setViewType(HomePageAdapter.TOP_VIEW_TYPE);
                data.add(bean);
            }
            Log.e(TAG, "getHomeData: "+ "topping.change----------" );
            liveDataMerger.setValue(data);
        });

        liveDataMerger.addSource(getData(), articleBean -> {
            for (HomeBean bean : articleBean.getData().getDatas()
            ) {
                bean.setViewType(HomePageAdapter.NORMAL_VIEW_TYPE);
                data.add(bean);
            }
            Log.e(TAG, "getHomeData: "+ "articleBean.change++++++++++" );
            liveDataMerger.setValue(data);
        });
        Log.e(TAG, String.valueOf(data.size()));
        return liveDataMerger;

    }
}
