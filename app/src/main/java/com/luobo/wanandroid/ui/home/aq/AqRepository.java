package com.luobo.wanandroid.ui.home.aq;

import androidx.lifecycle.MutableLiveData;

import com.luobo.wanandroid.api.ApiService;
import com.luobo.wanandroid.api.RetrofitFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AqRepository {
    private static volatile AqRepository instance;
    private ApiService service = RetrofitFactory.getInstance();
    private int page = 1;
    private boolean isFirst = true;
    private boolean isLoading = false;
    private AqResponse datas = new AqResponse();

    private AqRepository() {
    }

    // private constructor : singleton access
    public static AqRepository getInstance() {
        if (instance == null) {
            instance = new AqRepository();
        }
        return instance;
    }

    MutableLiveData<AqResponse> responseLiveData = new MutableLiveData<>();

    MutableLiveData<AqResponse> getAq() {
        if (isLoading) {
            return responseLiveData;
        }
        isLoading = true;
        service.getAq(page).enqueue(new Callback<AqResponse>() {
            @Override
            public void onResponse(Call<AqResponse> call, Response<AqResponse> response) {
                if (isFirst) {
                    datas.setData(response.body().getData());
                    isFirst = false;
                } else {
                    datas.getData().getDatas().addAll(response.body().getData().getDatas());
                }
                responseLiveData.setValue(datas);
                isLoading = false;
                page++;
            }

            @Override
            public void onFailure(Call<AqResponse> call, Throwable t) {
                isLoading = false;
            }
        });
        return responseLiveData;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
