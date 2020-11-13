package com.luobo.wanandroid.ui.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class LoginViewModel extends AndroidViewModel {
    private UserRepository userRepository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository();
    }

    public void loginUser(String name, String psw) {
        userRepository.loginUser(name, psw);
    }
}
