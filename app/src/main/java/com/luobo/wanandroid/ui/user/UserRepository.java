package com.luobo.wanandroid.ui.user;

import android.util.Log;

import com.luobo.wanandroid.api.ApiService;
import com.luobo.wanandroid.api.RetrofitFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private ApiService service = RetrofitFactory.getInstance();

    public void loginUser(String name, String psw) {
        service.loginUser("aluobobo", "456123").enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.e("Will", "onResponse: ok" );
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
}
