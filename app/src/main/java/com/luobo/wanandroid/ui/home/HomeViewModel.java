package com.luobo.wanandroid.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class HomeViewModel extends AndroidViewModel {
    private HomePageRepository articleRepository;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        articleRepository = HomePageRepository.getInstance();
    }

    MutableLiveData<ArticleDataBean> getData() {
        return articleRepository.getArticle();
    }

    MutableLiveData<HomeBannerBean> getBanner() {
        return articleRepository.getBanner();
    }

    MutableLiveData<ToppingBean> getTopping() { return articleRepository.getTopping(); }
}
