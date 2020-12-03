package com.luobo.wanandroid.ui.aq;

import android.util.Log;

import androidx.lifecycle.MediatorLiveData;
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
        Log.e("will", "onResponse: 1");
        if (isLoading) {
            return responseLiveData;
        }
        Log.e("will", "onResponse: 3");
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
                Log.e("will", "onResponse: 4");
                responseLiveData.setValue(datas);
                isLoading = false;
                page++;
            }

            @Override
            public void onFailure(Call<AqResponse> call, Throwable t) {
                Log.e("will", "onResponse: 5");
                isLoading = false;
            }
        });
        return responseLiveData;
    }

}
