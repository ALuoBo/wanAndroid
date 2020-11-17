package com.luobo.wanandroid.ui.login;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.luobo.wanandroid.api.ApiService;
import com.luobo.wanandroid.api.RetrofitFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {
    static final String TAG = "Will";
    private ApiService service = RetrofitFactory.getInstance();

    private MutableLiveData<LoginResult> resultMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<LoginResult> loginUser(String name, String psw) {

        service.loginUser(name, psw).enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.body().getErrorCode() == -1) {

                    Log.e(TAG, "onResponse: " + response.body().getErrorCode());

                    resultMutableLiveData.setValue(new LoginResult(response.body().getErrorMsg()));

                    Log.e(TAG, "onResponse: " + resultMutableLiveData.getValue().getError());

                } else {

                    Log.e(TAG, "onResponse: " + response.body().getErrorCode());
                    resultMutableLiveData.setValue(new LoginResult(
                            new LoggedInUser(response.body().getData().getUsername(), response.body().getData().getUsername())));
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e(TAG, "onResponse: " + t);
                resultMutableLiveData.setValue(new LoginResult("网络异常"));
            }
        });
        return resultMutableLiveData;
    }


}
