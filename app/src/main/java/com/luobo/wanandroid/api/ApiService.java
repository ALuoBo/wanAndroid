package com.luobo.wanandroid.api;

import com.luobo.wanandroid.ui.home.ArticleDataBean;
import com.luobo.wanandroid.ui.home.HomeBannerBean;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("/article/list/{pageNum}/json")
    Call<ArticleDataBean> getArticlesList(@Path("pageNum") int curPage);

    @GET("banner/json")
    Call<HomeBannerBean> getBannerList();
}
