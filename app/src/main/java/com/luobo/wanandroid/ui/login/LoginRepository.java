package com.luobo.wanandroid.ui.login;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.luobo.wanandroid.api.ApiService;
import com.luobo.wanandroid.api.RetrofitFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {

    static final String TAG = "Will";
    private MutableLiveData<LoginResult> loginResultData;
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


    public LiveData<LoginResult> getLoginResult() {
        return loginResultData;
    }

    public void login(String name, String psw) {
        loginResultData = new MutableLiveData<>();
        service.loginUser(name, psw).enqueue(new Callback<LoginBean>() {

            @Override
            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {

                if (response.body().getErrorCode() == 0) {
                    Log.e(TAG, "onResponse: " + "login error code 0");
                    loginResultData.setValue(new LoginResult(new LoggedInUserView(response.body().getData().getUsername())));
                } else {
                    loginResultData.setValue(new LoginResult(response.body().getErrorMsg()));
                }

            }

            @Override
            public void onFailure(Call<LoginBean> call, Throwable t) {
                Log.e(TAG, "onResponse: " + t);
                //网络异常处理
                loginResultData.setValue(new LoginResult(t.getMessage()));
            }
        });
    }
}