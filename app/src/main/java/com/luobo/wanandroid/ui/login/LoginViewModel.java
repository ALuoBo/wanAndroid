package com.luobo.wanandroid.ui.login;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class LoginViewModel extends AndroidViewModel {

    private LoginRepository userRepository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        userRepository = new LoginRepository();
    }

    public MutableLiveData<LoginResult> loginUser(String name, String psw) {
        return userRepository.loginUser(name, psw);
    }

}
