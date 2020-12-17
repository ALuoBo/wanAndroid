package com.luobo.wanandroid.api;

import com.luobo.wanandroid.ui.aq.AqResponse;
import com.luobo.wanandroid.ui.home.ArticleDataBean;
import com.luobo.wanandroid.ui.home.HomeBannerBean;
import com.luobo.wanandroid.ui.home.ToppingBean;
import com.luobo.wanandroid.ui.login.LoggedInUser;
import com.luobo.wanandroid.ui.other.project.ProjectTreeBean;
import com.luobo.wanandroid.ui.user.IntegralBean;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    /**
     * 首页
     *
     * @param curPage
     * @return
     */
    @GET("/article/list/{pageNum}/json")
    Call<ArticleDataBean> getArticlesList(@Path("pageNum") int curPage);


    /**
     * 首页banner
     *
     * @return
     */
    @GET("banner/json")
    Call<HomeBannerBean> getBannerList();

    /**
     * 置顶文章
     *
     * @return
     */
    @GET("/article/top/json")
    Call<ToppingBean> getToppingArticles();

    /**
     * 登录
     *
     * @param userName
     * @param password
     * @return
     */

    @FormUrlEncoded
    @POST("/user/login")
    Call<LoggedInUser> loginUser(@Field("username") String userName, @Field("password") String password);

    @GET("/user/logout/json")
    Call loginOut();

    /**
     * 问答
     *
     * @param curPage
     * @return
     */
    @GET("/wenda/list/{pageNum}/json")
    Call<AqResponse> getAq(@Path("pageNum") int curPage);


    /**
     * 积分
     *
     * @return
     */
    @GET("/lg/coin/userinfo/json")
    Call<IntegralBean> getIntegral();

    /**
     * 搜索
     *
     * @param page
     * @param key
     * @return
     */
    @FormUrlEncoded
    @POST("/article/query/{page}/json")
    Call<ArticleDataBean> search(@Path("page") int page, @Field("k") String key);


    @GET("/project/tree/json")
    Call<ProjectTreeBean> getProjectTree();
}
