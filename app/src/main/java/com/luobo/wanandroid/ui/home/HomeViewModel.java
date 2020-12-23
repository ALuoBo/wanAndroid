package com.luobo.wanandroid.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.luobo.wanandroid.ui.home.banner.HomeBannerBean;

public class HomeViewModel extends AndroidViewModel {
    private HomePageRepository articleRepository;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        articleRepository = HomePageRepository.getInstance();
    }

    MutableLiveData<ArticleBean> getData() {
        return articleRepository.getArticle();
    }

    MutableLiveData<HomeBannerBean> getBanner() {
        return articleRepository.getBanner();
    }

    MutableLiveData<ToppingBean> getTopping() { return articleRepository.getTopping(); }
}
