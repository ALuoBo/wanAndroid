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

    public MutableLiveData<LoginResult> login(String name, String psw) {

        MutableLiveData<LoginResult> resultMutableLiveData = new MutableLiveData<>();

        service.loginUser(name, psw).enqueue(new Callback<LoggedInUser>() {

            @Override
            public void onResponse(Call<LoggedInUser> call, Response<LoggedInUser> response) {

                if (response.body().getErrorCode() == -1) {
                    Log.e(TAG, "onResponse: " + "error code -1");

                    resultMutableLiveData.setValue(new LoginResult(response.body().getErrorMsg()));

                } else {
                    Log.e(TAG, "onResponse: " + response.body().getErrorCode());

                    resultMutableLiveData.setValue(new LoginResult(response.body()));

                    setLoggedInUser(response.body());
                }

            }

            @Override
            public void onFailure(Call<LoggedInUser> call, Throwable t) {
                Log.e(TAG, "onResponse: " + t);
                resultMutableLiveData.setValue(new LoginResult("网络异常"));
            }
        });

        return resultMutableLiveData;
    }

    private void setLoggedInUser(LoggedInUser user) {
        Log.e(TAG, "setLoggedInUser: " + user.getData().getNickname());
        LoggedInUser.getInstance().setData(user.getData());
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

}
