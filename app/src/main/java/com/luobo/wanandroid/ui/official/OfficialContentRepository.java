package com.luobo.wanandroid.ui.official;

import androidx.lifecycle.MutableLiveData;

import com.luobo.wanandroid.api.ApiService;
import com.luobo.wanandroid.api.RetrofitFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class OfficialContentRepository {
    int page = 1;

    private ApiService service = RetrofitFactory.getInstance();

    MutableLiveData<OfficialArticleBean> getOfficialArticle(int id) {

        MutableLiveData<OfficialArticleBean> datas = new MutableLiveData<>();

        service.getOfficialArticle(id, page).enqueue(new Callback<OfficialArticleBean>() {
            @Override
            public void onResponse(Call<OfficialArticleBean> call, Response<OfficialArticleBean> response) {
                datas.setValue(response.body());
            }

            @Override
            public void onFailure(Call<OfficialArticleBean> call, Throwable t) {

            }
        });
        return datas;
    }
}