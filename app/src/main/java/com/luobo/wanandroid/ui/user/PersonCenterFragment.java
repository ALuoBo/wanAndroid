package com.luobo.wanandroid.ui.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = getNavController(getActivity());
        binding.userPhoto.setOnClickListener(v -> {
            navController.navigate(R.id.action_global_loginFragment);
        });
        binding.goSetting.setOnClickListener(v -> {
            navController.navigate(R.id.action_userFragment_to_settingFragment);

        });


        if (loginState()) {
            viewModel.getIntegral().observe(getViewLifecycleOwner(), integralBean -> {
                binding.integral.setText(String.valueOf(integralBean.getData().getCoinCount()));
                binding.username.setText(integralBean.getData().getUsername());
            });
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(PersonCenterViewModel.class);
        binding = FragmentUserBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

}