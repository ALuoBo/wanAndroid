package com.luobo.wanandroid.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class HomeViewModel extends AndroidViewModel {
    private HomePageRepository articleRepository;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        articleRepository = new HomePageRepository();
    }

    MutableLiveData<ArticleDataBean> getData(int pageNum) {
        return articleRepository.getArticle(pageNum);
    }

    MutableLiveData<HomeBannerBean> getBanner() {
        return articleRepository.getBanner();
    }
}
