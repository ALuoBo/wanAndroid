package com.luobo.wanandroid.ui.official;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OfficialViewModel extends ViewModel {
    public OfficialViewModel() {
        this.repository = OfficialTreeRepository.getInstance();
    }

    private OfficialTreeRepository repository;

    MutableLiveData<OfficialTreeBean> getTabs() {
        return repository.getTabs();
    }
}
