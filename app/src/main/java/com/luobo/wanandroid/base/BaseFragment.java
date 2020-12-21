package com.luobo.wanandroid.base;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.luobo.wanandroid.R;

public class BaseFragment extends Fragment {

    /**
     * 获取导航控制
     *
     * @param requestActivity
     * @return
     */
    protected NavController getNavController(FragmentActivity requestActivity) {
        NavHostFragment navHostFragment = (NavHostFragment) requestActivity.getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        return navHostFragment.getNavController();
    }
}