package com.luobo.wanandroid.ui.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import com.luobo.wanandroid.R;
import com.luobo.wanandroid.base.BaseFragment;

public class PersonCenterFragment extends BaseFragment {
    private String TAG = this.getClass().getName();
    TextView userName;
    PersonCenterViewModel viewModel;
    NavController navController;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView userPhoto = view.findViewById(R.id.userPhoto);
        navController = getNavController(getActivity());
        LinearLayout linearLayout = view.findViewById(R.id.goSetting);
        userPhoto.setOnClickListener(v -> {
            navController.navigate(R.id.action_global_loginFragment);
        });
        linearLayout.setOnClickListener(v -> {
            navController.navigate(R.id.action_userFragment_to_settingFragment);

        });
        userName = view.findViewById(R.id.username);

        TextView integral = view.findViewById(R.id.integral);
        TextView userName = view.findViewById(R.id.username);

        if (loginState()) {
            viewModel.getIntegral().observe(getViewLifecycleOwner(), integralBean -> {
                integral.setText(String.valueOf(integralBean.getData().getCoinCount()));
                userName.setText(integralBean.getData().getUsername());
            });
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(PersonCenterViewModel.class);
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

}