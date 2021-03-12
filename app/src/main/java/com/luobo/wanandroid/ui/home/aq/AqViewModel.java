package com.luobo.wanandroid.ui.home.aq;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class AqViewModel  extends ViewModel {

    private AqRepository aqRepository;

    public AqViewModel() {
        this.aqRepository = AqRepository.getInstance();
    }

    public MutableLiveData<AqResponse> getAq() {
        return aqRepository.getAq();

    }

    public void refresh() {
        aqRepository.setPage(1);
    }

}
