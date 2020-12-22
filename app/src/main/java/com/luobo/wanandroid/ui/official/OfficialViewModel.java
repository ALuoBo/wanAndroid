package com.luobo.wanandroid.ui.official;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OfficialViewModel extends ViewModel {
    private OfficialTreeRepository repository = new OfficialTreeRepository();
    MutableLiveData<OfficialTreeBean> getTabs() {
        return repository.getTabs();
    }
}
