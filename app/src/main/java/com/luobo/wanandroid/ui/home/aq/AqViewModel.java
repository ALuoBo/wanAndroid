package com.luobo.wanandroid.ui.home.aq;

import androidx.lifecycle.MutableLiveData;

import com.luobo.wanandroid.base.BaseViewModel;


public class AqViewModel extends BaseViewModel {

    private AqRepository aqRepository;
    private Boolean isLoading, isSuccess, isEmpty, isError;

    public Boolean getLoading() {
        return isLoading;
    }

    public void setLoading(Boolean loading) {
        isLoading = loading;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public Boolean getEmpty() {
        return isEmpty;
    }

    public void setEmpty(Boolean empty) {
        isEmpty = empty;
    }

    public Boolean getError() {
        return isError;
    }

    public void setError(Boolean error) {
        isError = error;
    }

    public AqViewModel() {
        this.aqRepository = AqRepository.getInstance();
    }


    public MutableLiveData<AqResponse> getAq() {
        return aqRepository.getAq();

    }

    public void refresh() {
        aqRepository.setPage(1);
    }


    @Override
    public boolean isLOADING() {

        return isLoading;
    }

    @Override
    public boolean isSUCCESS() {
        return isSuccess;
    }

    @Override
    public boolean isEMPTY() {
        return isEmpty;
    }

    @Override
    public boolean isERROR() {
        return isError;
    }
}
