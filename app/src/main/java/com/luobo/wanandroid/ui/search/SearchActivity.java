package com.luobo.wanandroid.ui.search;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;

import com.luobo.wanandroid.R;
import com.luobo.wanandroid.base.BaseActivity;

public class SearchActivity extends BaseActivity {
    SearchView searchView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serach);
        searchView = findViewById(R.id.searchView);
    }
}