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
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.luobo.wanandroid.R;

import java.util.zip.Inflater;

public class BaseFragment extends Fragment {

    private BaseViewModel viewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            viewModel =new ViewModelProvider(this).get(BaseViewModel.class);

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.loadStateLiveData.observe(getViewLifecycleOwner(),loadState -> {
            switch (loadState){
                case EMPTY:

                    break;
                case ERROR:

                    break;
                case LOADING:

                    break;

                case SUCCESS:
                    break;
            }
        });
    }
   
}