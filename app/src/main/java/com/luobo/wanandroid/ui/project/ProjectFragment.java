package com.luobo.wanandroid.ui.project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.tabs.TabLayout;
import com.luobo.wanandroid.R;

public class ProjectFragment extends Fragment {

    private ProjectViewModel mViewModel;

    public static ProjectFragment newInstance() {
        return new ProjectFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(requireActivity()).get(ProjectViewModel.class);
        return inflater.inflate(R.layout.project_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TabLayout tabLayout = view.findViewById(R.id.projectSecondTable);
        mViewModel.getProjectTree().observe(getViewLifecycleOwner(), projectTreeBean -> {
            for (ProjectTreeBean.DataBean dataBean : projectTreeBean.getData()
            ) {
                tabLayout.addTab(tabLayout.newTab().setText(dataBean.getName()));

            }
        });
    }
}