package com.luobo.wanandroid.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import com.luobo.wanandroid.R;
import com.luobo.wanandroid.ui.login.LoggedInUser;
import com.luobo.wanandroid.ui.login.LoginActivity;
import com.luobo.wanandroid.ui.setting.SettingsActivity;

public class PersonCenterFragment extends Fragment {
    TextView userName;
    PersonCenterViewModel viewModel;
    NavController navController;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView userPhoto = view.findViewById(R.id.userPhoto);
        NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
        userPhoto.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), LoginActivity.class));
        });
        LinearLayout linearLayout = view.findViewById(R.id.goSetting);
        linearLayout.setOnClickListener(v -> {
            navController.navigate(R.id.action_userFragment_to_settingsActivity);
            //startActivity(new Intent(getActivity(), SettingsActivity.class));
        });
        userName = view.findViewById(R.id.username);

        TextView integral = view.findViewById(R.id.integral);
        if (LoggedInUser.getInstance().getData() != null) {
            viewModel.getIntegral().observe(requireActivity(), new Observer<IntegralBean>() {
                @Override
                public void onChanged(IntegralBean integralBean) {
                    Log.e("TAG", "onChanged: " + integralBean.getData().getCoinCount());
                    integral.setText(String.valueOf(integralBean.getData().getCoinCount()));
                }
            });
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(PersonCenterViewModel.class);
        return inflater.inflate(R.layout.fragment_user, container, false);
    }
}