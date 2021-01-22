package com.luobo.wanandroid.base;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public abstract class BaseViewModel extends ViewModel {

    MutableLiveData<LoadState> loadStateLiveData;

    public void setLoadState() {
        if (isLOADING()) {
            loadStateLiveData.setValue(LoadState.LOADING);
        }
        if (isSUCCESS()) {
            loadStateLiveData.setValue(LoadState.LOADING);
        }
        if (isERROR()) {
            loadStateLiveData.setValue(LoadState.ERROR);
        }
        if (isEMPTY()) {
            loadStateLiveData.setValue(LoadState.EMPTY);
        }
    }

    public abstract boolean isLOADING();

    public abstract boolean isSUCCESS();

    public abstract boolean isEMPTY();

    public abstract boolean isERROR();
}
