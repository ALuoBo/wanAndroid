package com.luobo.wanandroid.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import com.airbnb.lottie.LottieAnimationView;
import com.luobo.wanandroid.R;
import com.luobo.wanandroid.base.BaseFragment;


public class LoginFragment extends BaseFragment {
    LottieAnimationView lottieAnimationView;
    private LoginViewModel loginViewModel;
    Button loginButton;
    EditText username, password, checkPassword;
    TextView changeLogin;
    NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = getNavController(getActivity());
        lottieAnimationView = view.findViewById(R.id.lottieCircle);
        lottieAnimationView.setMinAndMaxProgress(0, 0.775f);

        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        loginButton = view.findViewById(R.id.done);

        checkPassword = view.findViewById(R.id.checkPassword);
        changeLogin = view.findViewById(R.id.changeLogin);

        loginViewModel.getLoginResult().observe(getViewLifecycleOwner(), loginResult -> {
            if (loginResult == null) {
                return;
            }
            if (loginResult.getError() != null) {
                showLoginFailed(loginResult.getError());
            }
            if (loginResult.getSuccess() != null) {
                updateUiWithUser(loginResult.getSuccess());
                // navController.getPreviousBackStackEntry().getSavedStateHandle().set("loginState",true);
                navController.navigateUp();


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
        Toast.makeText(getContext(), welcome, Toast.LENGTH_LONG).show();
    }


    private void showLoginFailed(String errorString) {
        Toast.makeText(getContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}