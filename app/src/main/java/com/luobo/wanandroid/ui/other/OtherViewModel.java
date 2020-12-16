package com.luobo.wanandroid.ui.other;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class OtherViewModel extends AndroidViewModel {
    String[] tab = {"广场", "公众号", "体系", "项目"};
    private MutableLiveData<String[]> _tabText = new MutableLiveData<>();

    public OtherViewModel(@NonNull Application application) {
        super(application);
    }
    public MutableLiveData<String[]> getTabText() {
        _tabText.setValue(tab);
        return _tabText;
    }
}
