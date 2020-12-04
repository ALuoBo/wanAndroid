package com.luobo.wanandroid.api;

import com.luobo.wanandroid.ui.aq.AqResponse;
import com.luobo.wanandroid.ui.home.ArticleDataBean;
import com.luobo.wanandroid.ui.home.HomeBannerBean;
import com.luobo.wanandroid.ui.home.ToppingBean;
import com.luobo.wanandroid.ui.login.LoggedInUser;
import com.luobo.wanandroid.ui.user.IntegralBean;


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

    /*置顶文章*/
    @GET("/article/top/json")
    Call<ToppingBean> getToppingArticles();

    /*登录*/
    @FormUrlEncoded
    @POST("/user/login")
    Call<LoggedInUser> loginUser(@Field("username") String userName, @Field("password") String password);

    /*退出登录*/
    @GET("/user/logout/json")
    Call loginOut();

    /*问答*/
    @GET("/wenda/list/{pageNum}/json")
    Call<AqResponse> getAq(@Path("pageNum") int curPage);

    /*积分*/
    @GET("/lg/coin/userinfo/json")
    Call<IntegralBean> getIntegral();

    /*搜索*/
    @FormUrlEncoded
    @POST("/article/query/{page}/json")
    Call<ArticleDataBean> search(@Path("page") int page, @Field("k") String key);
}
