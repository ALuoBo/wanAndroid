package com.luobo.wanandroid.api;

import com.luobo.wanandroid.ui.aq.AqFragment;
import com.luobo.wanandroid.ui.aq.AqResponse;
import com.luobo.wanandroid.ui.home.ArticleDataBean;
import com.luobo.wanandroid.ui.home.HomeBannerBean;
import com.luobo.wanandroid.ui.login.LoginResponse;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    /*首页*/
    @GET("/article/list/{pageNum}/json")
    Call<ArticleDataBean> getArticlesList(@Path("pageNum") int curPage);

    /*首页banner*/
    @GET("banner/json")
    Call<HomeBannerBean> getBannerList();

    /*登录*/
    @FormUrlEncoded
    @POST("/user/login")
    Call<LoginResponse> loginUser(@Field("username") String userName, @Field("password") String password);

    /*问答*/
    @GET("/wenda/list/{pageNum}/json")
    Call<AqResponse> getAq(@Path("pageNum") int curPage);
}
