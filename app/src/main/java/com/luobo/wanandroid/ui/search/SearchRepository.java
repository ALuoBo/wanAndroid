package com.luobo.wanandroid.ui.search;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.luobo.wanandroid.api.ApiService;
import com.luobo.wanandroid.api.RetrofitFactory;
import com.luobo.wanandroid.ui.home.ArticleDataBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchRepository {
    private ApiService service = RetrofitFactory.getInstance();

    private SearchRepository() {
    }

    private static SearchRepository instance;

    public static SearchRepository getInstance() {
        if (instance == null) {
            return instance = new SearchRepository();
        } else {
            return instance;
        }
    }

    public MutableLiveData<ArticleDataBean> getSearchResult(String page, String keywords) {
        MutableLiveData<ArticleDataBean> data = new MutableLiveData<>();
        service.search(page, keywords).enqueue(new Callback<ArticleDataBean>() {
            @Override
            public void onResponse(Call<ArticleDataBean> call, Response<ArticleDataBean> response) {
                Log.e("will", "onResponse: 123"+response.body().getData().getDatas() );
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ArticleDataBean> call, Throwable t) {

            }
        });
        return data;
    }
}
