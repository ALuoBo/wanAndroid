package com.luobo.wanandroid.ui.home;

import androidx.lifecycle.MutableLiveData;

import com.luobo.wanandroid.api.ApiService;
import com.luobo.wanandroid.api.RetrofitFactory;

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

    private int page = 0;
    private boolean isLoading = false;
    private boolean isFirstRequest = true;
    ArticleDataBean datasBean = new ArticleDataBean();
    MutableLiveData<ArticleDataBean> liveData = new MutableLiveData<>();

    public MutableLiveData<ArticleDataBean> getArticle() {
        if (isLoading) return liveData;
        isLoading = true;
        service.getArticlesList(page).enqueue(new Callback<ArticleDataBean>() {
            @Override
            public void onResponse(Call<ArticleDataBean> call, Response<ArticleDataBean> response) {
                if (isFirstRequest) {
                    datasBean.setData(response.body().getData());
                    isFirstRequest = false;
                } else {
                    datasBean.getData().getDatas().addAll(response.body().getData().getDatas());
                }
                //不管值是否相同，只看version的值,都会执行onchange()
                liveData.setValue(datasBean);
                page++;
                isLoading = false;
            }

            @Override
            public void onFailure(Call<ArticleDataBean> call, Throwable t) {
                isLoading = false;
            }
        });

        return liveData;
    }

    public MutableLiveData<HomeBannerBean> getBanner() {
        MutableLiveData<HomeBannerBean> liveBanner = new MutableLiveData<>();
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

    public MutableLiveData<ToppingBean> getTopping() {

        MutableLiveData<ToppingBean> liveData = new MutableLiveData<>();
        service.getToppingArticles().enqueue(new Callback<ToppingBean>() {
            @Override
            public void onResponse(Call<ToppingBean> call, Response<ToppingBean> response) {
                liveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ToppingBean> call, Throwable t) {

            }
        });

        return liveData;
    }


}
