package com.luobo.wanandroid.api;

import com.luobo.wanandroid.ui.home.ArticleBean;
import com.luobo.wanandroid.ui.home.banner.HomeBannerBean;
import com.luobo.wanandroid.ui.home.ToppingBean;
import com.luobo.wanandroid.ui.home.aq.AqResponse;
import com.luobo.wanandroid.ui.login.LoggedInUser;
import com.luobo.wanandroid.ui.official.OfficialArticleBean;
import com.luobo.wanandroid.ui.official.OfficialTreeBean;
import com.luobo.wanandroid.ui.project.ProjectContentBean;
import com.luobo.wanandroid.ui.project.ProjectTreeBean;
import com.luobo.wanandroid.ui.user.IntegralBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    /**
     * 首页
     *
     * @param curPage
     * @return
     */
    @GET("/article/list/{pageNum}/json")
    Call<ArticleBean> getArticlesList(@Path("pageNum") int curPage);


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
    Call<ArticleBean> search(@Path("page") int page, @Field("k") String key);


    @GET("/project/tree/json")
    Call<ProjectTreeBean> getProjectTree();

    /**
     * @param page 页码：拼接在链接中，从1开始。
     * @param cid  分类的id，上面项目分类接口
     * @return
     */
    @GET("/project/list/{page}/json")
    Call<ProjectContentBean> getProjectContent(@Path("page") int page, @Query("cid") int cid);

    @GET("/wxarticle/chapters/json ")
    Call<OfficialTreeBean> getOfficialTree();
/**
 * 在某个公众号中搜索历史文章
 */
 /*   @GET("/wxarticle/list/{id}/{page}/json")
    Call<OfficialArticleBean> getOfficialArticle(@Path("id") int id, @Path("page") int page, @Query("k") String str);*/

    /**
     * 查看某个公众号历史数据
     *
     * @param id
     * @param page
     * @return
     */
    @GET("/wxarticle/list/{id}/{page}/json")
    Call<OfficialArticleBean> getOfficialArticle(@Path("id") int id, @Path("page") int page);
}
