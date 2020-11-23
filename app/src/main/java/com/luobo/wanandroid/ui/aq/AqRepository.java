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


    private AqRepository() {
    }

    // private constructor : singleton access
    public static AqRepository getInstance() {
        if (instance == null) {
            instance = new AqRepository();
        }
        return instance;
    }

    MutableLiveData<AqResponse> getAq(int page) {

        MutableLiveData<AqResponse> responseLiveData = new MediatorLiveData<>();

        service.getAq(page).enqueue(new Callback<AqResponse>() {
            @Override
            public void onResponse(Call<AqResponse> call, Response<AqResponse> response) {
                responseLiveData.setValue(response.body());
                Log.e("Will", "onResponse: " + response.body().getData().getSize());
            }

            @Override
            public void onFailure(Call<AqResponse> call, Throwable t) {

            }
        });
        return responseLiveData;
    }

}
