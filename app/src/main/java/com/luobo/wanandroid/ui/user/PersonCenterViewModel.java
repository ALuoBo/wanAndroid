package com.luobo.wanandroid.ui.user;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PersonCenterViewModel extends ViewModel {
    private PersonCenterRepository personCenterRepository;

    public PersonCenterViewModel() {
       personCenterRepository = PersonCenterRepository.getInstance();
    }

    public MutableLiveData<IntegralBean> getIntegral() {
        return personCenterRepository.getIntegral();
    }
}
