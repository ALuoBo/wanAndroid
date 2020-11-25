package com.luobo.wanandroid.ui.search;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.luobo.wanandroid.ui.home.ArticleDataBean;

public class SearchViewModel extends ViewModel {
    private SearchRepository searchRepository;

    public SearchViewModel() {
        searchRepository = SearchRepository.getInstance();
    }

    public MutableLiveData<ArticleDataBean> getSearchResult(String page, String keywords) {
        return searchRepository.getSearchResult(page, keywords);

    }
}
