package com.luobo.wanandroid.ui.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class LoginViewModel extends AndroidViewModel {

    private LoginRepository loginRepository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        loginRepository = LoginRepository.getInstance();
    }

    /* public MutableLiveData<Result> login(String name, String psw) {
         Log.e("will", "viewModel");
         return userRepository.login(name, psw);
     }*/
    //登录结果
    LiveData<LoginResult> getLoginResult() {
        return loginRepository.getLoginResult();
    }

    public void login(String name, String password) {
        loginRepository.login(name, password);
    }
}


