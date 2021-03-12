package com.luobo.wanandroid.ui.user;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import com.luobo.wanandroid.R;
import com.luobo.wanandroid.base.BaseFragment;
import com.luobo.wanandroid.databinding.FragmentUserBinding;

public class PersonCenterFragment extends BaseFragment {
    private String TAG = this.getClass().getName();
    PersonCenterViewModel viewModel;
    NavController navController;
    private FragmentUserBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(PersonCenterViewModel.class);
        binding = FragmentUserBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated");
        navController = getNavController(R.id.nav_host_fragment);
        binding.userPhoto.setOnClickListener(v -> {
            navController.navigate(R.id.action_global_loginFragment);
        });
        binding.goSetting.setOnClickListener(v -> {
            navController.navigate(R.id.action_userFragment_to_settingFragment);

        });

        if (loginState()) {
            Log.d(TAG, "onViewCreated: get user message" );
            viewModel.getIntegral().observe(getViewLifecycleOwner(), integralBean -> {
                Log.d(TAG, "onViewCreated: get user message onchange" );
                binding.integral.setText(String.valueOf(integralBean.getData().getCoinCount()));
                binding.username.setText(integralBean.getData().getUsername());
            });
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}