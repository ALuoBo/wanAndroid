package com.luobo.wanandroid.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.luobo.wanandroid.R;

public class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_base, container, false);
    }

    /**
     * 获取导航控制器
     * @param requestActivity
     * @return
     */
    protected NavController getNavController(FragmentActivity requestActivity) {
        NavHostFragment navHostFragment = (NavHostFragment) requestActivity.getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        return navHostFragment.getNavController();
    }

    protected boolean loginState() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("CookiePersistence", Context.MODE_PRIVATE);
        String loginCookie = sharedPreferences.getString("http://www.wanandroid.com/|token_pass", null);
        return loginCookie != null;
    }



}