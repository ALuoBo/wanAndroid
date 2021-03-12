package com.luobo.wanandroid.base;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.luobo.wanandroid.R;

public class BaseFragment extends Fragment {

    /**
     * @return navController
     */
    protected NavController getNavController() {
        NavHostFragment navHostFragment = (NavHostFragment) getParentFragment().getParentFragmentManager().findFragmentById(R.id.nav_host_fragment);
        return navHostFragment.getNavController();
    }

    protected boolean loginState() {

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("CookiePersistence", Context.MODE_PRIVATE);

        String loginCookie = sharedPreferences.getString("http://www.wanandroid.com/|token_pass", null);

        return loginCookie != null;
    }


   
}