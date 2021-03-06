package com.luobo.wanandroid.ui.user;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.luobo.wanandroid.api.ApiService;
import com.luobo.wanandroid.api.RetrofitFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class PersonCenterRepository {
    private String TAG = this.getClass().getName();
    private static volatile PersonCenterRepository instance;
    private ApiService service = RetrofitFactory.getInstance();

    private PersonCenterRepository() {
    }

    // private constructor : singleton access
    public static PersonCenterRepository getInstance() {
        if (instance == null) {
            instance = new PersonCenterRepository();
        }
        return instance;
    }

    MutableLiveData<IntegralBean> data = new MutableLiveData<>();

    MutableLiveData<IntegralBean> getIntegral() {
        Log.d(TAG, "getIntegral:");
        service.getIntegral().enqueue(new Callback<IntegralBean>() {
            @Override
            public void onResponse(Call<IntegralBean> call, Response<IntegralBean> response) {
                Log.d(TAG, "getIntegral: on response" + String.valueOf(response.body().getErrorCode()));
                if (response.body().getErrorCode() == 0) {
                    data.setValue(response.body());
                    Log.d(TAG, "onResponse: " + response.body().getErrorCode() + response.body().getData().getCoinCount());
                }

            }

            @Override
            public void onFailure(Call<IntegralBean> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
        return data;
    }
}
