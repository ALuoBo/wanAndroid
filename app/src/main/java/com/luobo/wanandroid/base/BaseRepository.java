package com.luobo.wanandroid.base;

import com.luobo.wanandroid.api.ApiService;
import com.luobo.wanandroid.api.RetrofitFactory;
import com.luobo.wanandroid.ui.home.aq.AqRepository;

public abstract class BaseRepository {
    protected ApiService service;
    protected BaseRepository() {
        this.service = RetrofitFactory.getInstance();
    }


}
