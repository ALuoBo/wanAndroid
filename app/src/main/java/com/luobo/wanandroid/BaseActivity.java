package com.luobo.wanandroid;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        transparentStatusBar();
    }

    //沉浸式状态栏
    private void transparentStatusBar() {
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | SYSTEM_UI_FLAG_LAYOUT_STABLE| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION ;
        decorView.setSystemUiVisibility(uiOptions);
       //getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().setStatusBarColor(getColor(R.color.indigo_200));


    }

}
