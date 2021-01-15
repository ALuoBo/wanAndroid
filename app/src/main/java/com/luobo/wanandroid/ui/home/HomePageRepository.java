package com.luobo.wanandroid.ui.home;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.luobo.wanandroid.api.ApiService;
import com.luobo.wanandroid.api.ResultData;
import com.luobo.wanandroid.api.RetrofitFactory;
import com.luobo.wanandroid.ui.home.article.ArticleBean;
import com.luobo.wanandroid.ui.home.banner.HomeBannerBean;
import com.luobo.wanandroid.ui.home.top.ToppingBean;

import java.util.List;

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
        service.getArticlesList(page).enqueue(new Callback<ResultData<ArticleBean>>() {
            @Override
            public void onResponse(Call<ResultData<ArticleBean>> call, Response<ResultData<ArticleBean>> response) {
                if (isFirstRequest) {
                    Log.e(TAG, "getArticle: onResponse isFirstRequest");
                    datasBean.setDatas(response.body().getData().getDatas());
                    isFirstRequest = false;

                } else {
                    Log.e(TAG, "getArticle: getArticle: onResponse NotFirstRequest");
                    datasBean.getDatas().addAll(response.body().getData().getDatas());
                }
                //不管值是否相同，只看version的值,都会执行onchange()

                liveData.setValue(datasBean);
                page++;
                isLoading = false;
                Log.e(TAG, "getArticle: " + page + isLoading);
            }

            @Override
            public void onFailure(Call<ResultData<ArticleBean>> call, Throwable t) {
                isLoading = false;
            }
        });
        return liveData;
    }

    MutableLiveData<List<HomeBannerBean>> liveBanner = new MutableLiveData<>();

    public MutableLiveData<List<HomeBannerBean>> getBanner() {
        service.getBannerList().enqueue(new Callback<ResultData<List<HomeBannerBean>>>() {
            @Override
            public void onResponse(Call<ResultData<List<HomeBannerBean>>> call, Response<ResultData<List<HomeBannerBean>>> response) {
                liveBanner.setValue(response.body().getData());
            }

            @Override
            public void onFailure(Call<ResultData<List<HomeBannerBean>>> call, Throwable t) {

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
