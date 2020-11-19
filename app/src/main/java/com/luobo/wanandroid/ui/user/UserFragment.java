package com.luobo.wanandroid.ui.user;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.luobo.wanandroid.R;
import com.luobo.wanandroid.ui.login.LoginActivity;

public class UserFragment extends Fragment {

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user, container, false);
    }
}