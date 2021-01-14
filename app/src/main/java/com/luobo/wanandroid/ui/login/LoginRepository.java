package com.luobo.wanandroid.ui.login;

import androidx.lifecycle.MutableLiveData;

import com.luobo.wanandroid.api.ApiService;
import com.luobo.wanandroid.api.ResultData;
import com.luobo.wanandroid.api.RetrofitFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {
    static final String TAG = "Will";
    private static volatile LoginRepository instance;

    private LoginRepository() {
    }

    // private constructor : singleton access
    public static LoginRepository getInstance() {
        if (instance == null) {
            instance = new LoginRepository();
        }
        return instance;
    }

    private ApiService service = RetrofitFactory.getInstance();


    public MutableLiveData<ResultData<LoginBean>> login(String name, String psw) {
        MutableLiveData<ResultData<LoginBean>> data = new MutableLiveData<>();
        service.loginUser(name, psw).enqueue(new Callback<ResultData<LoginBean>>() {
            @Override
            public void onResponse(Call<ResultData<LoginBean>> call, Response<ResultData<LoginBean>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ResultData<LoginBean>> call, Throwable t) {

            }
        });
        return data;
    }
}