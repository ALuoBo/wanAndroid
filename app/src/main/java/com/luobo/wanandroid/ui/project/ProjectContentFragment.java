package com.luobo.wanandroid.ui.project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.luobo.wanandroid.R;

import java.util.ArrayList;
import java.util.List;

public class ProjectContentFragment extends Fragment {

    private ProjectContentViewModel mViewModel;
    private RecyclerView recyclerView;
    private int projectCid;

    public ProjectContentFragment(int projectCid) {
        this.projectCid = projectCid;
    }

    public static ProjectContentFragment newInstance(int projectCid) {
        return new ProjectContentFragment(projectCid);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(ProjectContentViewModel.class);
        return inflater.inflate(R.layout.fragment_project_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.projectContentRecycler);
        ProjectContentAdapter adapter = new ProjectContentAdapter(getContext(), new ProjectContentDiffUtil());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mViewModel.getProjectContent(projectCid).observe(getViewLifecycleOwner(), new Observer<ProjectContentBean>() {
            @Override
            public void onChanged(ProjectContentBean projectContentBean) {

                List<ProjectContentBean.DataBean.DatasBean> data = new ArrayList<>();
                data.addAll(projectContentBean.getData().getDatas());
                adapter.submitList(data);
            }
        });

    }
}