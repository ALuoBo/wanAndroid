package com.luobo.wanandroid.ui.home;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.luobo.wanandroid.api.ResultData;
import com.luobo.wanandroid.ui.home.article.ArticleBean;
import com.luobo.wanandroid.ui.home.banner.HomeBannerBean;
import com.luobo.wanandroid.ui.home.top.ToppingBean;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends AndroidViewModel {
    private String TAG = this.getClass().getName();
    private HomePageRepository articleRepository;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        articleRepository = HomePageRepository.getInstance();
    }

    MutableLiveData<List<HomeBannerBean>> getBanner() {
        return articleRepository.getBanner();
    }

    MutableLiveData<ToppingBean> getTopping() {
        return articleRepository.getTopping();
    }

    MutableLiveData<ArticleBean> getData() { return articleRepository.getArticle(); }

}
