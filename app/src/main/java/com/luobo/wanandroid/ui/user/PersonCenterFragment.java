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

import com.luobo.wanandroid.R;
import com.luobo.wanandroid.ui.login.LoggedInUser;
import com.luobo.wanandroid.ui.login.LoginActivity;
import com.luobo.wanandroid.ui.setting.SettingsActivity;

public class PersonCenterFragment extends Fragment {
    TextView userName ;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getActivity().findViewById(R.id.appBar).setVisibility(View.GONE);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView userPhoto = view.findViewById(R.id.userPhoto);

        userPhoto.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), LoginActivity.class));
        });
        LinearLayout linearLayout = view.findViewById(R.id.goSetting);
        linearLayout.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), SettingsActivity.class));
        });
        userName = view.findViewById(R.id.username);

    }

    @Override
    public void onResume() {
        if (LoggedInUser.getInstance().getData()!=null){
            userName.setText(LoggedInUser.getInstance().getData().getUsername());
        }
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user, container, false);
    }
}