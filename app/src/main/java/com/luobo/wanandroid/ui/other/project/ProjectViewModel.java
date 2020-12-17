package com.luobo.wanandroid.ui.other.project;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProjectViewModel extends ViewModel {
    ProjectRepository repository = new ProjectRepository();

    public MutableLiveData<ProjectTreeBean> getProjectTree() {
        return repository.getProjectTree();
    }
}
