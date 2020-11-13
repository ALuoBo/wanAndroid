package com.luobo.wanandroid.ui.login;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.airbnb.lottie.LottieAnimationView;
import com.luobo.wanandroid.R;
import com.luobo.wanandroid.ui.home.HomeViewModel;


public class LoginActivity extends AppCompatActivity {
    LottieAnimationView lottieAnimationView;
    private LoginViewModel loginViewModel;
    Button okButton;
    EditText username, password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        lottieAnimationView = findViewById(R.id.lottieCircle);
        lottieAnimationView.setMinAndMaxProgress(0, 0.775f);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        okButton = findViewById(R.id.done);
        okButton.setOnClickListener(v -> {
            login();
        });
    }

    private void login() {
        String userName = username.getText().toString();
        String psw = password.getText().toString();
        loginViewModel.loginUser(userName, psw);
    }
}