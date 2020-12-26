package com.luobo.wanandroid.ui.official;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OfficialContentViewModel extends ViewModel {
    private OfficialContentRepository repository ;

    public OfficialContentViewModel() {
        this.repository = OfficialContentRepository.getInstance();
    }

    public MutableLiveData<OfficialArticleBean> getOfficialArticle(int id) {
        return repository.getOfficialArticle(id);
    }
}
