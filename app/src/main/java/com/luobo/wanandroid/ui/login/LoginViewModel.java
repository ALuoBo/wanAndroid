package com.luobo.wanandroid.ui.login;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class LoginViewModel extends AndroidViewModel {

    private LoginRepository userRepository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        userRepository = LoginRepository.getInstance();
    }

    public MutableLiveData<LoginResult> login(String name, String psw) {
        Log.e("will", "viewModel");
        return userRepository.login(name, psw);
    }

}
