package com.luobo.wanandroid.ui.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.airbnb.lottie.LottieAnimationView;
import com.luobo.wanandroid.R;


public class LoginActivity extends AppCompatActivity {
    LottieAnimationView lottieAnimationView;
    private LoginViewModel loginViewModel;
    Button loginButton;
    EditText username, password, checkPassword;
    TextView changeLogin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        lottieAnimationView = findViewById(R.id.lottieCircle);
        lottieAnimationView.setMinAndMaxProgress(0, 0.775f);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.done);

        checkPassword = findViewById(R.id.checkPassword);
        changeLogin = findViewById(R.id.changeLogin);

        loginViewModel.getLoginResult().observe(this, loginResult -> {
            if (loginResult == null) {
                return;
            }
            if (loginResult.getError() != null) {
                showLoginFailed(loginResult.getError());
            }
            if (loginResult.getSuccess() != null) {
                updateUiWithUser(loginResult.getSuccess());
                finish();
            }
        });
        loginButton.setOnClickListener(v -> {
            String userName = username.getText().toString();
            String psw = password.getText().toString();
            loginViewModel.login(userName, psw);
        });

        changeLogin.setOnClickListener(v -> {
            checkPassword.setVisibility(View.VISIBLE);
        });
    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }


    private void showLoginFailed(String errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}