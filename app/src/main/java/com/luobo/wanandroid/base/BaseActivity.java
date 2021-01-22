package com.luobo.wanandroid.base;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE;

public abstract class BaseActivity extends AppCompatActivity {
    protected Boolean nightMode;
    private int currentNightMode;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration configuration = getApplication().getResources().getConfiguration();
        currentNightMode = configuration.uiMode & Configuration.UI_MODE_NIGHT_MASK;
        checkNightMode(configuration);
        transparentStatusBar();
    }


    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        checkNightMode(newConfig);
    }


    //是否是夜间模式
    private Boolean checkNightMode(Configuration configuration) {
        switch (currentNightMode) {
            case Configuration.UI_MODE_NIGHT_NO:
                // Night mode is not active, we're using the light theme
                nightMode = false;
                break;
            case Configuration.UI_MODE_NIGHT_YES:
                // Night mode is active, we're using dark theme
                nightMode = true;
                break;
        }
        return nightMode;
    }

    //沉浸式状态栏
    protected void transparentStatusBar() {
        View decorView = getWindow().getDecorView();
        if (nightMode) {
            this.getWindow().getDecorView().setSystemUiVisibility(SYSTEM_UI_FLAG_LAYOUT_STABLE);
        } else {
            //this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

    }

}