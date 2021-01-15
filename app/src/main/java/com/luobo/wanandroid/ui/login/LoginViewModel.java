package com.luobo.wanandroid.ui.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.luobo.wanandroid.api.ResultData;

public class LoginViewModel extends AndroidViewModel {

    private LoginRepository loginRepository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        loginRepository = LoginRepository.getInstance();
    }

    public MutableLiveData<ResultData<LoginBean>> login(String name, String password) {
      return   loginRepository.login(name, password);
    }
}


