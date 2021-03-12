package com.luobo.wanandroid.ui.login;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import com.airbnb.lottie.LottieAnimationView;
import com.luobo.wanandroid.R;
import com.luobo.wanandroid.base.BaseFragment;
import com.luobo.wanandroid.databinding.FragmentLoginBinding;


public class LoginFragment extends BaseFragment {
    private String TAG = this.getClass().getName();
    private LottieAnimationView lottieAnimationView;
    private LoginViewModel loginViewModel;
    private FragmentLoginBinding binding;
    NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentLoginBinding.inflate(inflater, container, false);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = getNavController();
        lottieAnimationView = view.findViewById(R.id.lottieCircle);
        lottieAnimationView.setMinAndMaxProgress(0, 0.775f);

        binding.done.setOnClickListener(v -> {
            String userName = binding.username.getText().toString();
            String psw = binding.password.getText().toString();
            loginViewModel.login(userName, psw).observe(getViewLifecycleOwner(), loginBeanResultData -> {
                if (loginBeanResultData.getData() != null) {
                    updateUiWithUser(loginBeanResultData.getData().getUsername());
                    // navController.getPreviousBackStackEntry().getSavedStateHandle().set("loginState",true);
                    navController.navigateUp();
                }

            });
        });

        binding.changeLogin.setOnClickListener(v -> {
            binding.checkPassword.setVisibility(View.VISIBLE);
        });
    }

    private void updateUiWithUser(String username) {
        Log.d(TAG, "updateUiWithUser ");
        String welcome = getString(R.string.welcome) + username;
        // TODO : initiate successful logged in experience
        Toast.makeText(getContext(), welcome, Toast.LENGTH_LONG).show();
    }


    private void showLoginFailed(String errorString) {
        Toast.makeText(getContext(), errorString, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}