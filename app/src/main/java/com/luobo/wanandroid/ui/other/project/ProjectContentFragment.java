package com.luobo.wanandroid.ui.other.project;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luobo.wanandroid.R;

import java.util.List;

public class ProjectContentFragment extends Fragment {

    private ProjectContentViewModel mViewModel;
    private RecyclerView recyclerView;

    public static ProjectContentFragment newInstance() {
        return new ProjectContentFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(ProjectContentViewModel.class);
        return inflater.inflate(R.layout.project_content_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.projectContentRecycler);
        ProjectContentAdapter adapter = new ProjectContentAdapter(getContext(), new ProjectContentDiffUtil());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}