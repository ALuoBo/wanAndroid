package com.luobo.wanandroid.ui.home;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.luobo.wanandroid.api.ApiService;
import com.luobo.wanandroid.api.RetrofitFactory;
import com.luobo.wanandroid.ui.home.article.ArticleBean;
import com.luobo.wanandroid.ui.home.banner.HomeBannerBean;
import com.luobo.wanandroid.ui.home.top.ToppingBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class HomePageRepository {
    static String TAG = "HomePageRepository";
    private static volatile HomePageRepository instance;

    private HomePageRepository() {
    }

    // private constructor : singleton access
    public static HomePageRepository getInstance() {
        if (instance == null) {
            instance = new HomePageRepository();
        }
        return instance;
    }

    private ApiService service = RetrofitFactory.getInstance();
    MutableLiveData<ArticleBean> liveData = new MutableLiveData<>();
    private int page = 0;
    private boolean isLoading = false;
    private boolean isFirstRequest = true;
    ArticleBean datasBean = new ArticleBean();
    public MutableLiveData<ArticleBean> getArticle() {
        Log.e(TAG, "getArticle: ");

        if (isLoading) return liveData;
        isLoading = true;
        service.getArticlesList(page).enqueue(new Callback<ArticleBean>() {
            @Override
            public void onResponse(Call<ArticleBean> call, Response<ArticleBean> response) {
                if (isFirstRequest) {
                    Log.e(TAG, "getArticle: onResponse isFirstRequest");
                    datasBean.setData(response.body().getData());
                    isFirstRequest = false;
                } else {
                    Log.e(TAG, "getArticle: getArticle: onResponse NotFirstRequest");
                    datasBean.getData().getDatas().addAll(response.body().getData().getDatas());
                }
                //不管值是否相同，只看version的值,都会执行onchange()
                liveData.setValue(datasBean);
                page++;
                isLoading = false;
                Log.e(TAG, "getArticle: " + page + isLoading);
            }

            @Override
            public void onFailure(Call<ArticleBean> call, Throwable t) {
                isLoading = false;
            }
        });

        return liveData;
    }

    MutableLiveData<HomeBannerBean> liveBanner = new MutableLiveData<>();
    public MutableLiveData<HomeBannerBean> getBanner() {
        service.getBannerList().enqueue(new Callback<HomeBannerBean>() {
            @Override
            public void onResponse(Call<HomeBannerBean> call, Response<HomeBannerBean> response) {
                liveBanner.setValue(response.body());
            }

            @Override
            public void onFailure(Call<HomeBannerBean> call, Throwable t) {

            }
        });

        return liveBanner;
    }

    MutableLiveData<ToppingBean> liveTopData = new MutableLiveData<>();
    public MutableLiveData<ToppingBean> getTopping() {

        service.getToppingArticles().enqueue(new Callback<ToppingBean>() {
            @Override
            public void onResponse(Call<ToppingBean> call, Response<ToppingBean> response) {
                liveTopData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ToppingBean> call, Throwable t) {

            }
        });

        return liveTopData;
    }


}
