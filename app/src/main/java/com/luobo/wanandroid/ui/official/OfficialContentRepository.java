package com.luobo.wanandroid.ui.official;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.luobo.wanandroid.api.ApiService;
import com.luobo.wanandroid.api.RetrofitFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class OfficialContentRepository {
    private String TAG = this.getClass().getName();

    private OfficialContentRepository() {
    }

    private static volatile OfficialContentRepository instance;

    public static OfficialContentRepository getInstance() {
        if (instance == null) {
            instance = new OfficialContentRepository();
        }
        return instance;
    }

    private ApiService service = RetrofitFactory.getInstance();
    //由于不同的fragment传入的id不同，所以每次观察的liveData 应为新liveData
    //加载更多时为同一id,此时应为同一liveData
    private int officialId;

    MutableLiveData<OfficialArticleBean> data;

    OfficialArticleBean beans;

    private int page;

    private boolean isLoading = false;

    MutableLiveData<OfficialArticleBean> getOfficialArticle(int id) {

        Log.d(TAG, "getOfficialArticle: " + id);
        if (officialId != id) {
            officialId = id;
            page = 1;
            beans = new OfficialArticleBean();
            data = new MutableLiveData<>();
            Log.d(TAG, "getOfficialArticle:  init");
        }
        if (isLoading) return data;
        isLoading = true;
        service.getOfficialArticle(officialId, page).enqueue(new Callback<OfficialArticleBean>() {
            @Override
            public void onResponse(Call<OfficialArticleBean> call, Response<OfficialArticleBean> response) {
                if (page == 1) {
                    beans.setData(response.body().getData());
                } else {
                    beans.getData().getDatas().addAll(response.body().getData().getDatas());
                }
                data.setValue(beans);
                page++;
                isLoading = false;
            }

            @Override
            public void onFailure(Call<OfficialArticleBean> call, Throwable t) {
                isLoading = false;
            }
        });
        return data;
    }
}