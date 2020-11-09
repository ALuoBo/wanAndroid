package com.luobo.wanandroid.ui.home;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;


import com.luobo.wanandroid.api.ApiService;
import com.luobo.wanandroid.api.RetrofitFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class HomePageRepository {

    private ApiService service = RetrofitFactory.getInstance();
    MutableLiveData<ArticleDataBean> liveData = new MutableLiveData<>();
    MutableLiveData<HomeBannerBean> liveBanner = new MutableLiveData<>();

    public MutableLiveData<ArticleDataBean> getArticle(final int pageNum) {
        Log.e("Will", "onResponse: ...");

        service.getArticlesList(pageNum).enqueue(new Callback<ArticleDataBean>() {
            @Override
            public void onResponse(Call<ArticleDataBean> call, Response<ArticleDataBean> response) {

                liveData.setValue(response.body());

                Log.e("Will", "onResponse: SUCESS");
            }

            @Override
            public void onFailure(Call<ArticleDataBean> call, Throwable t) {
                Log.e("Will", "onFailure: " + t.toString());

            }

        });

        return liveData;
    }

    public MutableLiveData<HomeBannerBean> getBanner() {
        service.getBannerList().enqueue(new Callback<HomeBannerBean>() {
            @Override
            public void onResponse(Call<HomeBannerBean> call, Response<HomeBannerBean> response) {
                liveBanner.setValue(response.body());
                Log.e("Will", "onResponse: SUCESS");
            }

            @Override
            public void onFailure(Call<HomeBannerBean> call, Throwable t) {
                Log.e("Will", "Banner onFailure: " + t.toString());
            }
        });
        return liveBanner;
    }
}
