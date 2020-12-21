package com.luobo.wanandroid.ui.home.aq;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class AqViewModel extends AndroidViewModel {
    private AqRepository aqRepository;

    public AqViewModel(@NonNull Application application) {
        super(application);
        aqRepository = AqRepository.getInstance();
    }

    public MutableLiveData<AqResponse> getAq() {
        return aqRepository.getAq();
    }

    public void refresh() {
        aqRepository.setPage(1);
    }
}
