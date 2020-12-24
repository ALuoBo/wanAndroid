package com.luobo.wanandroid.ui.project;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.luobo.wanandroid.R;

import java.util.ArrayList;

public class ProjectFragment extends Fragment {
    private String TAG = "projectFragment";
    private ProjectViewModel mViewModel;
    private int projectContentFragmentSize;
    private int[] typeId = new int[30];

    public static ProjectFragment newInstance() {
        return new ProjectFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(ProjectViewModel.class);
        return inflater.inflate(R.layout.fragment_project, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TabLayout tabLayout = view.findViewById(R.id.projectSecondTable);
        ViewPager2 viewPager = view.findViewById(R.id.projectViewPager);

        ProjectFragmentAdapter adapter = new ProjectFragmentAdapter(this);
        viewPager.setAdapter(adapter);

        ArrayList<String> titleList = new ArrayList<>();

        mViewModel.getProjectTree().observe(getViewLifecycleOwner(), projectTreeBean -> {

            for (ProjectTreeBean.DataBean dataBean : projectTreeBean.getData()
            ) {
                titleList.add(dataBean.getName());
                typeId[projectContentFragmentSize] = dataBean.getId();
                projectContentFragmentSize++;
                Log.e(TAG, "itemCount---: " + projectContentFragmentSize);
            }
            adapter.notifyItemRangeInserted(0, projectContentFragmentSize);
            Log.e(TAG, "notify---: " + adapter.getItemCount());
        });

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            tab.setText(titleList.get(position));
        }).attach();

    }

    class ProjectFragmentAdapter extends FragmentStateAdapter {
        public ProjectFragmentAdapter(@NonNull Fragment fragment) {
            super(fragment);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            //添加项目内容fragment
            return ProjectContentFragment.newInstance(typeId[position]);
        }

        @Override
        public int getItemCount() {
            Log.e(TAG, "getItemCount---: " + projectContentFragmentSize);
            return projectContentFragmentSize;
        }
    }

}