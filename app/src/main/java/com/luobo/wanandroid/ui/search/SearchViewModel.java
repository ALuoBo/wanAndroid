package com.luobo.wanandroid.ui.search;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.luobo.wanandroid.ui.home.article.ArticleBean;

public class SearchViewModel extends ViewModel {
    private SearchRepository searchRepository;

    public SearchViewModel() {
        searchRepository = SearchRepository.getInstance();
    }

    public MutableLiveData<ArticleBean> getSearchResult(String keywords) {
        return searchRepository.getSearchResult(keywords);
    }

    public MutableLiveData<ArticleBean> LoadMoreResult(String keywords) {
        return searchRepository.LoadMoreResult(keywords);
    }
}
