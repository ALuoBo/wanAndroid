package com.luobo.wanandroid.ui.project;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProjectContentViewModel extends ViewModel {
    private ProjectContentRepository repository = new ProjectContentRepository();

    public MutableLiveData<ProjectContentBean> getProjectContent(int cid) {
        return repository.getProjectContent(cid);
    }
}