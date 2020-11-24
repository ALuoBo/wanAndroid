package com.luobo.wanandroid.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.airbnb.lottie.LottieAnimationView;
import com.luobo.wanandroid.R;


public class LoginActivity extends AppCompatActivity {
    LottieAnimationView lottieAnimationView;
    private LoginViewModel loginViewModel;
    Button okButton;
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
        okButton = findViewById(R.id.done);

        checkPassword = findViewById(R.id.checkPassword);
        changeLogin = findViewById(R.id.changeLogin);

        okButton.setOnClickListener(v -> {
            String userName = username.getText().toString();
            String psw = password.getText().toString();

            loginViewModel.login(userName, psw).observe(this, new Observer<LoginResult>() {
                @Override
                public void onChanged(LoginResult loginResult) {

                    if (loginResult != null) {
                        if (loginResult.getSuccess() != null) {
                            Toast.makeText(LoginActivity.this, "Welcome " + loginResult.getSuccess().getData().getNickname(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.putExtra("username", loginResult.getSuccess().getData().getNickname());

                            setResult(Activity.RESULT_OK);

                            finish();

                        } else if (loginResult.getError() != null) {
                            Toast.makeText(LoginActivity.this, loginResult.getError(), Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            });
        });


        changeLogin.setOnClickListener(v -> {
            checkPassword.setVisibility(View.VISIBLE);
        });
    }


}