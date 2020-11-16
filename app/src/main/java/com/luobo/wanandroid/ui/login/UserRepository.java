package com.luobo.wanandroid.ui.login;

import android.util.Log;

import com.luobo.wanandroid.api.ApiService;
import com.luobo.wanandroid.api.RetrofitFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private ApiService service = RetrofitFactory.getInstance();

    public void loginUser(String name, String psw) {
        service.loginUser(name, psw).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
    }
}
