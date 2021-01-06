package com.luobo.wanandroid.ui.project;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProjectContentViewModel extends ViewModel {
    private ProjectContentRepository repository = ProjectContentRepository.getInstance();

    public MutableLiveData<ProjectContentBean> getProjectContent(int cid) {
        return repository.getProjectContent(cid);
    }
}